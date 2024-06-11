package JFS6WDE.OnlineBusTicketBooking.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.OnlineBusTicketBooking.Dto.ReservationDto;
import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Entities.Reservation;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;
import JFS6WDE.OnlineBusTicketBooking.Repository.BusRepository;
import JFS6WDE.OnlineBusTicketBooking.Repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BusRepository busRepository;
    

    @Override
    public Reservation addReservation(ReservationDto dto) throws ResourceNotFound {
        
        Bus bus = busRepository.findByRouteFromAndRouteTo(dto.getSource(), dto.getDestination());
        
        if(bus == null) throw new ResourceNotFound("Bus not found for the given starting to destination");
        
        int availableSeats = bus.getAvailableSeats();
        
        if(availableSeats < dto.getBookedSeat()) throw new ResourceNotFound("Only " + availableSeats + " seats are available");
        
        availableSeats -= dto.getBookedSeat();
        
        bus.setAvailableSeats(availableSeats);
        
          Reservation reservation = new Reservation();

          if(dto.getJourneyDate().isBefore(LocalDate.now())) throw  new ResourceNotFound("Journey Date should be in Future");
          
          reservation.setDestination(dto.getDestination());
          reservation.setDate(dto.getJourneyDate());
          reservation.setStatus("Successful");
          reservation.setDate(LocalDate.now());
          reservation.setTime(LocalTime.now());
          reservation.setJourneyDate(dto.getJourneyDate());
          reservation.setBus(bus);
          reservation.setFare(bus.getFare() * dto.getBookedSeat());
          reservation.setBookedSeat(dto.getBookedSeat());

        return reservationRepository.save(reservation);
    }
 
    @Override
    public Reservation viewReservation(long rid) throws ResourceNotFound {

        Optional<Reservation> optional = reservationRepository.findById(rid);

        if(optional.isEmpty()) throw new ResourceNotFound("Reservation with given id is not found");

        return optional.get();
    }

    @Override
    public List<Reservation> getAllReservation() throws ResourceNotFound {

        List<Reservation> list = reservationRepository.findAll();

        if(list.isEmpty()) throw new ResourceNotFound("Reservation Not found");

        return list;
    }

    @Override
    public Reservation deleteReservation(long rid) throws ResourceNotFound{
    	Optional<Reservation> optional =  reservationRepository.findById(rid);  
    	
    	if(optional.isEmpty()) throw new ResourceNotFound("Reservation not found with the given id: " + rid);
    	
    	Reservation reservation = optional.get();
    	
    	if(reservation.getJourneyDate().isBefore(LocalDate.now())) throw new ResourceNotFound("Reservation Already Expired");
    	
    	int n = reservation.getBus().getAvailableSeats();
    	
    	reservation.getBus().setAvailableSeats(n + reservation.getBookedSeat());
    	
    	Bus bus = reservation.getBus();
    	
    	busRepository.save(bus);    	
        reservationRepository.delete(reservation);
        
        return reservation;
    }

    @Override
    public Reservation updateReservation(long rid, ReservationDto dto) throws ResourceNotFound {
        
        Bus bus = busRepository.findByRouteFromAndRouteTo(dto.getSource(), dto.getDestination());
        
        if(bus == null) throw new ResourceNotFound("Bus not found for the given starting to destination");
        
        int availableSeats = bus.getAvailableSeats();
        
        if(availableSeats < dto.getBookedSeat()) throw new ResourceNotFound("Only " + availableSeats + " seats are available");
        
        availableSeats -= dto.getBookedSeat();
        
        bus.setAvailableSeats(availableSeats);
        
        Optional<Reservation> optional = reservationRepository.findById(rid);
        
        if(optional.isEmpty()) throw new ResourceNotFound("Reservation not found with the given id: " + rid);
         
        Reservation reservation  = optional.get();
        reservation.setBookedSeat(dto.getBookedSeat());
        reservation.setBus(bus);
        reservation.setDate(dto.getJourneyDate());
        reservation.setDestination(dto.getDestination()) ;
        reservation.setFare(bus.getFare());
        reservation.setJourneyDate(dto.getJourneyDate());
        reservation.setDate(LocalDate.now());
        reservation.setTime(LocalTime.now());        
        
        reservationRepository.save(reservation);
        
        return reservation;
    }


}

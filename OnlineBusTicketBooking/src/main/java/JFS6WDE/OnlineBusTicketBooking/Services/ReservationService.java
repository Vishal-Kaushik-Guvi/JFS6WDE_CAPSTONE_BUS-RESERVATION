package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.List;

import JFS6WDE.OnlineBusTicketBooking.Dto.ReservationDto;
import JFS6WDE.OnlineBusTicketBooking.Entities.Reservation;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;

public interface ReservationService {
    public Reservation addReservation(ReservationDto dto, String key) throws ResourceNotFound;

    public Reservation viewReservation(Integer rid, String key) throws ResourceNotFound;

    public List<Reservation> getAllReservation(String key) throws ResourceNotFound;

    public Reservation deleteReservation(Integer rid, String key) throws ResourceNotFound;

    public Reservation updateReservation(Integer rid, ReservationDto dto, String key) throws ResourceNotFound;

}

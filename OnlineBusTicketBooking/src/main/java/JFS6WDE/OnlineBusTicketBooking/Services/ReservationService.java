package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.List;

import JFS6WDE.OnlineBusTicketBooking.Dto.ReservationDto;
import JFS6WDE.OnlineBusTicketBooking.Entities.Reservation;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;

public interface ReservationService {
    public Reservation addReservation(ReservationDto dto) throws ResourceNotFound;

    public Reservation viewReservation(long rid) throws ResourceNotFound;

    public List<Reservation> getAllReservation() throws ResourceNotFound;

    public Reservation deleteReservation(long rid) throws ResourceNotFound;

    public Reservation updateReservation(long rid, ReservationDto dto) throws ResourceNotFound;

}

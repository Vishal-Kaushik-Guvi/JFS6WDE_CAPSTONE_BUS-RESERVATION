package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.List;

import JFS6WDE.OnlineBusTicketBooking.Entities.BookingHistory;

public interface BookingService {
    
List<BookingHistory> getAllBooking();
void deleteBookingById(long id);
BookingHistory getBookingById(long id);


// main functionality
void bookBus(Long busId, String email);

}

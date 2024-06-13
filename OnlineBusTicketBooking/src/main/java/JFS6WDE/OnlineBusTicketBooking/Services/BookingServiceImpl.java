package JFS6WDE.OnlineBusTicketBooking.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.OnlineBusTicketBooking.Entities.BookingHistory;
import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Entities.User;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;
import JFS6WDE.OnlineBusTicketBooking.Repository.BookingRepository;
import JFS6WDE.OnlineBusTicketBooking.Repository.BusRepository;
import JFS6WDE.OnlineBusTicketBooking.Repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;


    @Override
    public List<BookingHistory> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteBookingById(long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingHistory getBookingById(long id) {
        return bookingRepository.findById(id).get();
    }
    // Main Functionality

    @Override
    @Transactional
    public void bookBus(Long busId, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFound("User not found with email: " + email);
        }
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFound("Invalid bus Id: " + busId));

        // Ensure no duplicate booking
        List<BookingHistory> existingBookings = bookingRepository.findByUserAndBus(user, bus);
        if (!existingBookings.isEmpty()) {
            throw new IllegalArgumentException("Booking already exists for this user and bus.");
        }

        BookingHistory booking = new BookingHistory();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setRouteFrom(bus.getRouteFrom());
        booking.setRouteTo(bus.getRouteTo());
        booking.setDistance(bus.getDistance());
        booking.setFare(bus.getFare());
        booking.setBookingDate(LocalDate.now());
        booking.setBookingTime(LocalTime.now());
        bookingRepository.save(booking);
    }

}

package JFS6WDE.OnlineBusTicketBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import JFS6WDE.OnlineBusTicketBooking.Entities.BookingHistory;
import JFS6WDE.OnlineBusTicketBooking.Services.BookingServiceImpl;

@Controller
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    // Display all booking history entries
    @GetMapping("/viewBookingHistory")
    public String getAllBookings(Model model) {
        List<BookingHistory> bookingHistoryList = bookingService.getAllBooking();
        model.addAttribute("bookingHistoryList", bookingHistoryList);
        return "bookinghistory"; // Thymeleaf template name
    }

    // Delete booking history entry
    @PostMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/viewBookingHistory"; // Redirect to the booking history list page
    }    

    // Find booking by id
    @GetMapping("/viewBooking")
    public String findBookingById(long id, Model model){
        BookingHistory booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "bookinghistory";
    }

    @PostMapping("/book/{busId}")
    public String bookBus(@PathVariable Long busId, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        String email = currentUser.getUsername();
        bookingService.bookBus(busId, email);
        List<BookingHistory> bookingHistoryList = bookingService.getAllBooking();
        model.addAttribute("bookingHistoryList", bookingHistoryList);
        return "redirect:/index"; // Redirect to the booking history page
    }
    
}

package JFS6WDE.OnlineBusTicketBooking.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationID;

    // successfull or unsuccssefull
    private String status;
    private LocalDate date;
    private LocalTime time;

    private String destination;
    private LocalDate journeyDate;
    private Integer bookedSeat;
    private Integer fare;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bus bus;
}

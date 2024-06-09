package JFS6WDE.OnlineBusTicketBooking.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingHistory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String username;


}

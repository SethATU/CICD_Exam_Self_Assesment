package ie.atu.sample.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organiserName;
    private int attendeeCount;
    private Long roomId;
    private Boolean status = true;   // true = ACTIVE

    public Booking() {}

    public Booking(Long id, String organiserName, int attendeeCount, Long roomId, Boolean status) {
        this.id = id;
        this.organiserName = organiserName;
        this.attendeeCount = attendeeCount;
        this.roomId = roomId;
        this.status = status;
    }
}
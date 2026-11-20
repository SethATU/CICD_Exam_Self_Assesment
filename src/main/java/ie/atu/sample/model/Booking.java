package ie.atu.sample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Setter
@Getter
@EnableFeignClients
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Min(2)
    @Max(60)
    private String orgName;

    @Positive
    private Long attendanceCount;

    @NotNull
    private Long RoomId;

    private Boolean status = true;

    public Booking() {}

    public Booking(Long id, String orgName, Long attendanceCount, Long RoomId, Boolean status) {
        this.id = id;
        this.orgName = orgName;
        this.attendanceCount = attendanceCount;
        this.RoomId = RoomId;
        this.status = status;
    }

}

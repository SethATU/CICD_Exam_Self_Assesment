package ie.atu.sample.service;

import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.dto.CreateBookingRequest;
import ie.atu.sample.model.Booking;
import ie.atu.sample.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingResponse createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setOrgName(request.orgName());
        booking.setAttendanceCount(request.attendanceCount());
        booking.setRoomId(request.roomId());
        booking.setStatus(true);
        return toResponse(bookingRepository.save(booking));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
    }

    private BookingResponse toResponse(Booking b) {
        return new BookingResponse(
                b.getId(),
                b.getOrgName(),
                b.getAttendanceCount(),
                b.getRoomId(),
                b.getStatus());
    }
}

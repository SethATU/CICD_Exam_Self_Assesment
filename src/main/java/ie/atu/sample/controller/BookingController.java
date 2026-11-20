package ie.atu.sample.controller;

import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.dto.CreateBookingRequest;
import ie.atu.sample.model.Booking;
import ie.atu.sample.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }
    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @GetMapping("/{id}")
    public Booking getById(@PathVariable("id") Long id) {
        return bookingService.getById(id);
    }
}
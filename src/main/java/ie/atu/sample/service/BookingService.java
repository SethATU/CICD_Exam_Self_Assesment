package ie.atu.sample.service;

import feign.FeignException;
import ie.atu.sample.client.RoomClient;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.dto.CreateBookingRequest;
import ie.atu.sample.dto.RoomDto;
import ie.atu.sample.model.Booking;
import ie.atu.sample.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomClient roomClient;
    public BookingService(BookingRepository bookingRepository, RoomClient roomClient) {
        this.bookingRepository = bookingRepository;
        this.roomClient = roomClient;
    }

    public BookingResponse createBooking(CreateBookingRequest request) {
        RoomDto room = getRoom(request.roomId());

        if (request.attendeeCount() > room.capacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attendee count exceeds the capacity of room");
        }

        Booking booking = new Booking();
        booking.setOrganiserName(request.organiserName());
        booking.setAttendeeCount(request.attendeeCount());
        booking.setRoomId(request.roomId());
        booking.setStatus(true);
        return toResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::toResponse).toList();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
    }

    private RoomDto getRoom(Long roomId) {
        try {
            return roomClient.getRoomById(roomId);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
    }

    private BookingResponse toResponse(Booking b) {
        return new BookingResponse(
            b.getId(),
            b.getOrganiserName(),
            b.getAttendeeCount(),
            b.getRoomId(),
            b.getStatus());
    }
}
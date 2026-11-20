package ie.atu.sample.dto;

public record BookingResponse(
        Long id,
        String organiserName,
        int attendeeCount,
        Long roomId,
        Boolean status
) {}
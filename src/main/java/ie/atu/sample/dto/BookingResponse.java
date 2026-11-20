package ie.atu.sample.dto;

public record BookingResponse(
        Long id,
        String orgName,
        @jakarta.validation.constraints.Positive Long attendanceCount,
        Long roomId,
        Boolean status
) {}
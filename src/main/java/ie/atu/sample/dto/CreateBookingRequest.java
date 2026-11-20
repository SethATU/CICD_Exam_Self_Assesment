package ie.atu.sample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateBookingRequest(

        @NotNull(message = "Room Id required")
        @Positive(message = "room Id must be a positive")
        Long roomId,

        @NotBlank(message = "Org Name must cant blank")
        @Size(min = 2, max = 60, message = "Org Name must be between 2 and 60 characters")
        String orgName,

        @NotNull(message = "Count is required")
        @Positive(message = "Count must be positive")
        Integer attendanceCount
) {}
package champ.fish.Aftas.Models.DTO.Competition;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public final class CompetitionDTO {

    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid pattern")
    private String code;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the present or future")
    private LocalDate date;

//    @FutureOrPresent(message = "Start time must be in the present or future")
    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

//    @FutureOrPresent(message = "End time must be in the present or future")
    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Number of participants cannot be null")
    @Min(value = 0, message = "Number of participants must be greater than or equal to 0")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", message = "Amount must be greater than or equal to 0.0")
    private Double amount;
}

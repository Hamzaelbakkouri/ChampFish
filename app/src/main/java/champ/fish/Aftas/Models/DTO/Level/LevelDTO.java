package champ.fish.Aftas.Models.DTO.Level;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public final class LevelDTO {
    @NotNull(message = "code can't be Null")
    private int code;

    @NotBlank(message = "Description can't be Null")
    private String description;

    @NotNull(message = "points can't be Null")
    private int points;
}

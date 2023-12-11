package champ.fish.Aftas.Models.DTO.Hunting;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class HuntingDTO {
    private Integer id;

    @Min(value = 1, message = "The minimum fish number is 1")
    private Integer numberOfFish;

    @NotNull(message = "Fish id must be Not null")
    private String fish_id;

    @NotNull(message = "Member id must be Not null")
    private Integer member_id;

    @NotNull(message = "Competition id must be Not null")
    private String competition_id;
}

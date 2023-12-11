package champ.fish.Aftas.Models.DTO.Fish;


import champ.fish.Aftas.Models.DTO.Level.LevelDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class FishDTO {
    private String name;

    @NotNull(message = "Description can't be Null")
    @Min(value = 0)
    private Double averageWeight;

    @NotNull(message = "Level can't be Null")
    private int level_id;
}

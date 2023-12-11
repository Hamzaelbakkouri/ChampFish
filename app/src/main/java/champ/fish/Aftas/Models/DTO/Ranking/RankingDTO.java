package champ.fish.Aftas.Models.DTO.Ranking;

import champ.fish.Aftas.Models.Entities.EmbeddedRanking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public final class RankingDTO {
    private RankingEmbeddedDTO id;

    @NotNull(message = "Rank cannot be blank")
    @Min(value = 0, message = "rank cant be less than 0")
    private Integer rank;

    @NotNull(message = "Score cannot be blank")
    @Min(value = 0, message = "score cant be less than 0")
    private Integer score;
}

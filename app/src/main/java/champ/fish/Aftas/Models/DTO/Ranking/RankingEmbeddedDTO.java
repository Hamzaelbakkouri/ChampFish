package champ.fish.Aftas.Models.DTO.Ranking;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RankingEmbeddedDTO {
    @NotNull(message = "competitionCode cannot be Null")
    private String competitionCode;
    @NotNull(message = "memberNum cannot be Null")
    private Integer memberNum;
}

package champ.fish.Aftas.Models.DTO.Ranking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public final class RankingDTOresp {
    private RankingEmbeddedDTOresp id;
    private Integer rank;
    private Integer score;
}

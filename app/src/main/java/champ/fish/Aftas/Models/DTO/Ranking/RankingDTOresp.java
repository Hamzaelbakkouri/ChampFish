package champ.fish.Aftas.Models.DTO.Ranking;


import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.Entities.EmbeddedRanking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public final class RankingDTOresp {
    private RankingEmbeddedDTO id;
    private Integer rank;
    private Integer score;
}

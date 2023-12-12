package champ.fish.Aftas.Models.DTO.Ranking;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RankingEmbeddedDTOresp {
    private CompetitionDTO competition;
    private MemberDTOresp member;
}

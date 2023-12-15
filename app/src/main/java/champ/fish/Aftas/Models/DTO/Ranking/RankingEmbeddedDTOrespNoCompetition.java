package champ.fish.Aftas.Models.DTO.Ranking;

import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOrespNoComp;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RankingEmbeddedDTOrespNoCompetition {
    private MemberDTOrespNoComp member;
}

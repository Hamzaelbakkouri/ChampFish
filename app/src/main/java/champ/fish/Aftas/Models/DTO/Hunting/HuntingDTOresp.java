package champ.fish.Aftas.Models.DTO.Hunting;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTOresp;
import champ.fish.Aftas.Models.DTO.Fish.FishDTO;
import champ.fish.Aftas.Models.DTO.Fish.FishDTOresp;
import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public final class HuntingDTOresp {
    private Integer id;
    private Integer numberOfFish;
    private FishDTOresp fish;
    private MemberDTOresp member;
    private CompetitionDTOresp competition;
}

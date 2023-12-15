package champ.fish.Aftas.Models.DTO.Hunting;

import champ.fish.Aftas.Models.DTO.Fish.FishDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HuntingDTOrespNoComp {
    private Integer id;
    private Integer numberOfFish;
    private FishDTO fish;
//    private MemberDTOrespNoComp member;
}

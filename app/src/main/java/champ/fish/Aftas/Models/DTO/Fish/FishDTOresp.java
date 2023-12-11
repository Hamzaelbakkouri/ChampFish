package champ.fish.Aftas.Models.DTO.Fish;

import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTO;
import champ.fish.Aftas.Models.DTO.Level.LevelDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class FishDTOresp {
    private String name;
    private Double averageWeight;
    private LevelDTO Level;
    private List<HuntingDTO> huntingList;
}
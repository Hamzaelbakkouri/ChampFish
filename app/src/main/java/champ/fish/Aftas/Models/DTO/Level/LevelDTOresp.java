package champ.fish.Aftas.Models.DTO.Level;

import champ.fish.Aftas.Models.DTO.Fish.FishDTO;
import champ.fish.Aftas.Models.Entities.Fish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LevelDTOresp {
    private Integer code;
    private String description;
    private Integer points;
}

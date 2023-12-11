package champ.fish.Aftas.Models.DTO.Competition;


import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTO;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public final class CompetitionDTOresp {
    private String code;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Float amount;
    private List<RankingDTO> rankings;
    private List<HuntingDTO> huntingList;
}

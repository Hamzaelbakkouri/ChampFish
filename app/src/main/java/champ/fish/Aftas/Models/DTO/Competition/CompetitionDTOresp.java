package champ.fish.Aftas.Models.DTO.Competition;


import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTO;
import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTOrespNoComp;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTO;
import champ.fish.Aftas.Models.DTO.Ranking.RankingDTOrespNoComp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public final class CompetitionDTOresp {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Float amount;
    private List<RankingDTOrespNoComp> rankings;
    private List<HuntingDTOrespNoComp> huntingList;
}

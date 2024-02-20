package champ.fish.Aftas.Services.Interfaces;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTOresp;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface Competition_Interface extends G_Types<CompetitionDTO, CompetitionDTOresp, String> {
    Page<CompetitionDTOresp> getAllWithPagination(Pageable pageable) ;

    Page<CompetitionDTOresp> filterDoneStatus(LocalDate date, Pageable pageable);
    Page<CompetitionDTOresp> filterPendingStatus(LocalDate date, Pageable pageable);
    Page<CompetitionDTOresp> filterInProgressStatus(LocalDate date, Pageable pageable);
}

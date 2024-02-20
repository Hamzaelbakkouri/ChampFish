package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Page<Competition> getCompetitionsByDate(LocalDate date, Pageable page);

    Page<Competition> getCompetitionsByDateAfter(LocalDate date, Pageable page);

    Page<Competition> getCompetitionsByDateBefore(LocalDate date, Pageable page);

}

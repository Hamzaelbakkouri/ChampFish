package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTOresp;
import champ.fish.Aftas.Models.Entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
}

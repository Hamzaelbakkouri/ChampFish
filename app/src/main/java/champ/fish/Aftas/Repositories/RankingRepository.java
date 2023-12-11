package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.EmbeddedRanking;
import champ.fish.Aftas.Models.Entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, EmbeddedRanking> {
}

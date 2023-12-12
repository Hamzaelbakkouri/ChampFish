package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Competition;
import champ.fish.Aftas.Models.Entities.Fish;
import champ.fish.Aftas.Models.Entities.Hunting;
import champ.fish.Aftas.Models.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByCompetitionAndAndFishAndMember(Competition competition, Fish fish , Member member);
    List<Hunting> findAllByCompetition(Competition competition);
}

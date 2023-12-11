package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}

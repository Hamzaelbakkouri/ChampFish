package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish , String>{
}

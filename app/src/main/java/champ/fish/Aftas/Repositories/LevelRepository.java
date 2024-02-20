package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    Level  findFirstByCodeBefore(int code);

    Level findFirstByCodeAfter(int code);
}

package champ.fish.Aftas.Repositories;

import champ.fish.Aftas.Models.Entities.Token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query(value = """
            SELECT t FROM Token t INNER JOIN Member m
            ON t.member.num = m.num
            WHERE m.num = :id AND (t.expired = false OR t.revoked = false)
            """)
    List<Token> findAllValidTokenByPerson(UUID id);

    Optional<Token> findByToken(String token);
}

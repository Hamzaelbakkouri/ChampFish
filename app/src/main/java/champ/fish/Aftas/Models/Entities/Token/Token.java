package champ.fish.Aftas.Models.Entities.Token;

import champ.fish.Aftas.Models.Entities.Member;
import champ.fish.Aftas.Models.Enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;


    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

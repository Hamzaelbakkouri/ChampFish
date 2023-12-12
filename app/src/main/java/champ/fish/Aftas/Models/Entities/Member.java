package champ.fish.Aftas.Models.Entities;

import champ.fish.Aftas.Models.Enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public final class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    @ManyToMany
    @JoinTable(
            name = "ranking",
            joinColumns = @JoinColumn(name = "member"),
            inverseJoinColumns = @JoinColumn(name = "competition")
    )
    private Set<Competition> competitions = new HashSet<>();

    @NonNull
    private String name;
    @NonNull
    private String familyName;
    @NonNull
    private LocalDate accessionDate;
    @NonNull
    private String nationality;
    @NonNull
    private IdentityDocumentType identityDocumentType;
    @NonNull
    private String identityNumber;

    @OneToMany(mappedBy = "id.member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntingList;
}

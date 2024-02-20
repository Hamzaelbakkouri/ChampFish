package champ.fish.Aftas.Models.Entities;

import champ.fish.Aftas.Models.Enums.IdentityDocumentType;
import champ.fish.Aftas.Models.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public final class Member implements UserDetails {
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
    private String password;

    @NonNull
    private String familyName;
    @NonNull
    private LocalDate accessionDate;
    @NonNull
    private String nationality;
    @NonNull
    private String email;

    private Role role;

    @NonNull
    private IdentityDocumentType identityDocumentType;
    @NonNull
    private String identityNumber;

    @OneToMany(mappedBy = "id.member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntingList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public @NonNull String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

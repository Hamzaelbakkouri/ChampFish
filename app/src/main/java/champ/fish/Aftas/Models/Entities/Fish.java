package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public final class Fish {
    @Id
    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @NotNull
    @Min(value = 0)
    private Double averageWeight;

    @ManyToOne
    private Level level;

    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntingList;
}

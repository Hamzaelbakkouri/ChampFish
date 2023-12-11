package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public final class Fish {
    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Min(value = 0)
    private Double averageWeight;

    @ManyToOne
    @NotNull
    private Level level;

    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntingList;
}

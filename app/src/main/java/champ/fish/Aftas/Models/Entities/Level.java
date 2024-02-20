package champ.fish.Aftas.Models.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public final class Level {
    @Id
    @Column(unique = true)
    private Integer code;

    @Nonnull
    private String description;

    @Nonnull
    @Min(value = 0)
    private Integer points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fish;
}

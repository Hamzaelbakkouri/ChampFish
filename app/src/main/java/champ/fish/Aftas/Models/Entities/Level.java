package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Level {
    @Id
    @Column(unique = true)
    private String code;

    @NonNull
    private String description;

    @NonNull
    @Min(value = 0)
    private Integer points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fish;
}

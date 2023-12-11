package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ranking")
public final class Ranking {
    @EmbeddedId
    private EmbeddedRanking id;
    private Integer score;
    private Integer rank;

}

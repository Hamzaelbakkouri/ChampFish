package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ranking")
public final class Ranking {
    @EmbeddedId
    private EmbeddedRanking id;

    @MapsId("competitionCode")
    @ManyToOne
    @JoinColumn(name = "competition_code")
    private Competition competition;

    @MapsId("memberNum")
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;

    @NotNull
    @Min(value = 1)
    private Integer rank;

    @NotNull
    @Min(value = 0)
    private Integer score;
}

package champ.fish.Aftas.Models.Entities;

import champ.fish.Aftas.Models.DTO.Ranking.RankingEmbeddedDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ranking")
public final class Ranking {
    @EmbeddedId
    private EmbeddedRanking id;
    private Long score;
    private Integer rank;

    public Ranking(Member member, Competition competition, Long score) {
        this.id = new EmbeddedRanking(competition,member);
        this.score = score;
    }

    public Ranking(Member member, Competition competition, Long score,Integer rank) {
        this.id = new EmbeddedRanking(competition,member);
        this.score = score;
        this.rank = rank;
    }
    public Ranking() {
    }
}

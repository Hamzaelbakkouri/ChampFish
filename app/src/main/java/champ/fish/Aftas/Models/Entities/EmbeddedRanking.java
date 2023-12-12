package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class EmbeddedRanking implements Serializable {
    public EmbeddedRanking(Competition competition, Member member) {
        this.competition = competition;
        this.member = member;
    }
    public EmbeddedRanking()
    {
    }

    @ManyToOne
    @JoinColumn(name = "competition")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

}

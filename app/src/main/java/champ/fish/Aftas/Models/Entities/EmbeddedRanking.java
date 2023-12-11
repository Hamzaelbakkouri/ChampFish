package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EmbeddedRanking implements Serializable {
    @ManyToOne
    @JoinColumn(name = "competition_code")
    private Competition competitionCode;

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member memberNum;

}

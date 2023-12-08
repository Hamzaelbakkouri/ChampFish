package champ.fish.Aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EmbeddedRanking implements Serializable {
    @Column(name = "competition_code")
    private String competitionCode;

    @Column(name = "member_num")
    private Integer memberNum;
}

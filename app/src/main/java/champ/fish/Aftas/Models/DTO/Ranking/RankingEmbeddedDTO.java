package champ.fish.Aftas.Models.DTO.Ranking;

import champ.fish.Aftas.Models.DTO.Competition.CompetitionDTO;
import champ.fish.Aftas.Models.DTO.Member.MemberDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RankingEmbeddedDTO {
    @NotBlank(message = "competition_code cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid pattern")
    private String competition_code;

    @NotNull(message = "member_num number cannot be null")
    private Integer member_num;
}

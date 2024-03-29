package champ.fish.Aftas.Models.DTO.Member;

import champ.fish.Aftas.Models.DTO.Hunting.HuntingDTOrespNoComp;
import champ.fish.Aftas.Models.Enums.IdentityDocumentType;
import champ.fish.Aftas.Models.Enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MemberDTOrespNoComp {
    private Integer num;
    private String name;
    private String email;
    private Role role;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;

//    private List<RankingDTOrespNoComp> rankings;
    private List<HuntingDTOrespNoComp> huntingList;
}

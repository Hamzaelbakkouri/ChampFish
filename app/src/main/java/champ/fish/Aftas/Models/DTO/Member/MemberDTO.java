package champ.fish.Aftas.Models.DTO.Member;


import champ.fish.Aftas.Models.Enums.IdentityDocumentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public final class MemberDTO {

    private Integer num;

    @NotBlank(message = "name can't be Null")
    private String name;

    @NotBlank(message = "familyName can't be Null")
    private String familyName;

    @NotBlank(message = "nationality can't be Null")
    private String nationality;

    @NotNull(message = "identityDocumentType can't be Null")
    private IdentityDocumentType identityDocumentType;

    @NotBlank(message = "identityDocumentType can't be Null")
    private String identityNumber;
}

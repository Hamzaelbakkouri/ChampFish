package champ.fish.Aftas.Models.Auth;

import champ.fish.Aftas.Models.Enums.IdentityDocumentType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    @NotNull(message = "First name cannot be null")
    String name;

    @Size(max = 30, message = "Last name is too long")
    String familyName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email was not provided")
    @Size(max = 80, message = "Email is too long")
    @Column(unique = true)
    String email;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    String password;

    @NotEmpty(message = "nationality cannot be empty")
    @NotNull(message = "nationality cannot be null")
    private String nationality;


    @NotNull(message = "Identity Document cannot be null")
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "identityNumber Number cannot be null")
    @NotEmpty(message = "identityNumber number cannot be empty")
    String identityNumber;
}

package champ.fish.Aftas.Exeptions;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private List<String> errors;
}
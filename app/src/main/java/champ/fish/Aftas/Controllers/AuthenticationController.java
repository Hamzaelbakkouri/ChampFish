package champ.fish.Aftas.Controllers;

import champ.fish.Aftas.Models.Auth.AuthenticationRequestDto;
import champ.fish.Aftas.Models.Auth.AuthenticationResponseDto;
import champ.fish.Aftas.Models.Auth.RegistrationRequestDto;
import champ.fish.Aftas.Services.Implementations.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationServiceImpl service;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody final AuthenticationRequestDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody final RegistrationRequestDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/manager")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<AuthenticationResponseDto> registerManager(@Valid @RequestBody final RegistrationRequestDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());

        return ResponseEntity.ok(service.registerManager(request));
    }

    @PostMapping("/jury")
    public ResponseEntity<AuthenticationResponseDto> registerAdmin(@Valid @RequestBody final RegistrationRequestDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());

        return ResponseEntity.ok(service.registerJURY(request));
    }
}


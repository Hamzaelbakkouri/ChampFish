package champ.fish.Aftas.Services.Implementations;

import champ.fish.Aftas.Exeptions.NotFoundExeption;
import champ.fish.Aftas.Models.Auth.AuthenticationRequestDto;
import champ.fish.Aftas.Models.Auth.AuthenticationResponseDto;
import champ.fish.Aftas.Models.Auth.RegistrationRequestDto;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOresp;
import champ.fish.Aftas.Models.DTO.Member.MemberDTOrespNoComp;
import champ.fish.Aftas.Models.DTO.Member.MemberData;
import champ.fish.Aftas.Models.Entities.Member;
import champ.fish.Aftas.Models.Entities.Token.Token;
import champ.fish.Aftas.Models.Enums.Role;
import champ.fish.Aftas.Models.Enums.TokenType;
import champ.fish.Aftas.Repositories.MemberRepository;
import champ.fish.Aftas.Repositories.TokenRepository;
import champ.fish.Aftas.Security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationResponseDto register(RegistrationRequestDto request) {
        return createUser(request, Role.ADHERENT);
    }

    public AuthenticationResponseDto registerManager(RegistrationRequestDto request) {
        return this.createUser(request, Role.MANAGER);
    }


    public AuthenticationResponseDto registerJURY(RegistrationRequestDto request) {
        return this.createUser(request, Role.JURY);
    }

    private AuthenticationResponseDto createUser(RegistrationRequestDto request, Role role) {
        log.info("Creating a new user with role: {}", role);

        Member user = new Member();
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFamilyName(request.getFamilyName());
        user.setNationality(request.getNationality());
        user.setIdentityDocumentType(request.getIdentityDocumentType());
        user.setIdentityNumber(request.getIdentityNumber());
        user.setAccessionDate(LocalDate.now());
        user.setRole(role);

        var savedUser = memberRepository.save(user);
        log.info("User with ID {} created successfully.", savedUser.getNum());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);
        log.info("Access and refresh tokens generated and saved for user with ID: {}", savedUser.getNum());

        return AuthenticationResponseDto.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }


    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error("Authentication failed for user: {}", request.getEmail(), ex);
            throw new BadCredentialsException("Invalid credentials");
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: {}", request.getEmail(), ex);
            throw new ResourceNotFoundException("Authentication failed");
        }
        var user = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponseDto.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    private void saveUserToken(Member user, String jwtToken) {
        var token = Token.builder().member(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }


    private void revokeAllUserTokens(Member user) {
        var validUserTokens = tokenRepository.findAllValidTokenByMember(user.getNum());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var user = this.memberRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public Boolean checkToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        String jwt = token.substring(7);
        var userEmail = jwtService.extractUserName(jwt);
        if (userEmail != null) {
            var user = this.memberRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return jwtService.isTokenValid(jwt, user);
        }
        return false;
    }

    public MemberData getUser(String name) {
        Member user = memberRepository.findByEmail(name).orElseThrow(() -> new NotFoundExeption("User not found"));
        return modelMapper.map(user, MemberData.class);
    }
}
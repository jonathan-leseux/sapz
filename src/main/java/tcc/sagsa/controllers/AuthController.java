// src/main/java/tcc/sagsa/controllers/AuthController.java
package tcc.sagsa.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tcc.sagsa.models.Usuario;
import tcc.sagsa.models.dtos.LoginRequestDTO;
import tcc.sagsa.models.dtos.LoginResponseDTO;
import tcc.sagsa.models.dtos.UsuarioResponseDTO;
import tcc.sagsa.repositories.UsuarioRepository;
import tcc.sagsa.security.JWTService;
import tcc.sagsa.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService,
                          JWTService jwtService,
                          UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwtToken = jwtService.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();

        LoginResponseDTO response = LoginResponseDTO.builder()
                .token(jwtToken)
                .usuario(new UsuarioResponseDTO(usuario))
                .build();

        return ResponseEntity.ok(response);
    }
}
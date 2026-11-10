package tcc.sagsa.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        private String email;

        @NotBlank(message = "Senha é obrigatória")
        private String senha;
    }

    @Getter
    @Setter
    public static class TokenResponse {
        private String token;
        private String tipo = "Bearer";
        private String email;
        private String perfil;

        public TokenResponse(String token, String email, String perfil) {
            this.token = token;
            this.email = email;
            this.perfil = perfil;
        }
    }
}


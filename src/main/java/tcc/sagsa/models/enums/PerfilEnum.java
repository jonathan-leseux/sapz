package tcc.sagsa.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {
    ADMINISTRADOR("ADMINISTRADOR"),
    COORDENADOR("COORDENADOR"),
    INSTRUTOR("INSTRUTOR");

    private final String descricao;

    public static PerfilEnum toEnum(String descricao) {
        if (descricao == null) {
            return null;
        }
        for (PerfilEnum perfil : PerfilEnum.values()) {
            if (perfil.getDescricao().equalsIgnoreCase(descricao)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Perfil inválido: " + descricao);
    }
}

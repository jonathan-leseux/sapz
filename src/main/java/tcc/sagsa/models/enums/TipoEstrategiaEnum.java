package tcc.sagsa.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEstrategiaEnum {
    SITUACAO_PROBLEMA("SITUACAO_PROBLEMA"),
    ESTUDO_CASO("ESTUDO_CASO"),
    PROJETO("PROJETO"),
    PESQUISA_APLICADA("PESQUISA_APLICADA"),
    PROJETO_INTEGRADOR("PROJETO_INTEGRADOR");

    private final String descricao;
}
package tcc.sagsa.controllers;

import tcc.sagsa.models.*;
import tcc.sagsa.repositories.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil-profissional")
public class PerfilProfissionalController {

    private final FuncaoRepository funcaoRepository;
    private final SubfuncaoRepository subfuncaoRepository;
    private final PadraoDesempenhoRepository padraoRepository;

    public PerfilProfissionalController(FuncaoRepository funcaoRepository,
                                        SubfuncaoRepository subfuncaoRepository,
                                        PadraoDesempenhoRepository padraoRepository) {
        this.funcaoRepository = funcaoRepository;
        this.subfuncaoRepository = subfuncaoRepository;
        this.padraoRepository = padraoRepository;
    }

    @GetMapping("/funcoes")
    public ResponseEntity<List<Funcao>> findFuncoesByPlano(@RequestParam Long planoCursoId) {
        return ResponseEntity.ok(funcaoRepository.findByPlanoCursoIdOrderByNumeroAsc(planoCursoId));
    }

    @PostMapping("/funcoes")
    public ResponseEntity<Funcao> createFuncao(@Valid @RequestBody Funcao funcao) {
        return ResponseEntity.ok(funcaoRepository.save(funcao));
    }

    @GetMapping("/subfuncoes")
    public ResponseEntity<List<Subfuncao>> findSubfuncoesByFuncao(@RequestParam Long funcaoId) {
        return ResponseEntity.ok(subfuncaoRepository.findByFuncaoId(funcaoId));
    }

    @PostMapping("/subfuncoes")
    public ResponseEntity<Subfuncao> createSubfuncao(@Valid @RequestBody Subfuncao subfuncao) {
        return ResponseEntity.ok(subfuncaoRepository.save(subfuncao));
    }

    @GetMapping("/padroes-desempenho")
    public ResponseEntity<List<PadraoDesempenho>> findPadroesBySubfuncao(@RequestParam Long subfuncaoId) {
        return ResponseEntity.ok(padraoRepository.findBySubfuncaoId(subfuncaoId));
    }

    @PostMapping("/padroes-desempenho")
    public ResponseEntity<PadraoDesempenho> createPadrao(@Valid @RequestBody PadraoDesempenho padrao) {
        return ResponseEntity.ok(padraoRepository.save(padrao));
    }
}

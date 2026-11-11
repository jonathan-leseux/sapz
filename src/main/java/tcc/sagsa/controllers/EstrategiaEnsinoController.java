package tcc.sagsa.controllers;

import tcc.sagsa.models.EstrategiaEnsino;
import tcc.sagsa.repositories.EstrategiaEnsinoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-estrategias")
public class EstrategiaEnsinoController {

    private final EstrategiaEnsinoRepository repository;

    public EstrategiaEnsinoController(EstrategiaEnsinoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<EstrategiaEnsino>> findBySaId(@RequestParam Long saId) {
        return ResponseEntity.ok(repository.findBySituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<EstrategiaEnsino> create(@Valid @RequestBody EstrategiaEnsino entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}
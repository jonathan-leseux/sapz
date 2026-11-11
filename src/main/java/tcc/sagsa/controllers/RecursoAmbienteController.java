package tcc.sagsa.controllers;

import tcc.sagsa.models.RecursoAmbiente;
import tcc.sagsa.repositories.RecursoAmbienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-recursos")
public class RecursoAmbienteController {

    private final RecursoAmbienteRepository repository;

    public RecursoAmbienteController(RecursoAmbienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<RecursoAmbiente>> findBySaId(@RequestParam Long saId) {
        return ResponseEntity.ok(repository.findBySituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<RecursoAmbiente> create(@Valid @RequestBody RecursoAmbiente entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}
package tcc.sagsa.controllers;

import tcc.sagsa.models.Compartilhamento;
import tcc.sagsa.repositories.CompartilhamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-compartilhamentos")
public class CompartilhamentoController {

    private final CompartilhamentoRepository repository;

    public CompartilhamentoController(CompartilhamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Compartilhamento>> findBySaId(@RequestParam Long saId) {
        return ResponseEntity.ok(repository.findBySituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<Compartilhamento> create(@Valid @RequestBody Compartilhamento entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}

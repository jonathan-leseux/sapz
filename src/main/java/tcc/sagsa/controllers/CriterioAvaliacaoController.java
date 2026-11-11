// src/main/java/br/com/senai/sapz/controllers/CriterioAvaliacaoController.java
package tcc.sagsa.controllers;

import tcc.sagsa.models.CriterioAvaliacao;
import tcc.sagsa.repositories.CriterioAvaliacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-criterios")
public class CriterioAvaliacaoController {

    private final CriterioAvaliacaoRepository repository;

    public CriterioAvaliacaoController(CriterioAvaliacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CriterioAvaliacao>> findByInstrumentoId(@RequestParam Long instrumentoId) {
        return ResponseEntity.ok(repository.findByInstrumentoId(instrumentoId));
    }

    @PostMapping
    public ResponseEntity<CriterioAvaliacao> create(@Valid @RequestBody CriterioAvaliacao entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}

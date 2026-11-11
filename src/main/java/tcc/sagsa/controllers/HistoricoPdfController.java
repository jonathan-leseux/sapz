package tcc.sagsa.controllers;

import tcc.sagsa.models.HistoricoPdf;
import tcc.sagsa.repositories.HistoricoPdfRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-historico-pdf")
public class HistoricoPdfController {

    private final HistoricoPdfRepository repository;

    public HistoricoPdfController(HistoricoPdfRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoPdf>> findBySaId(@RequestParam Long Id) {
        return ResponseEntity.ok(repository.findBySituacaoAprendizagemId(Id));
    }

    @PostMapping
    public ResponseEntity<HistoricoPdf> create(@Valid @RequestBody HistoricoPdf entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}

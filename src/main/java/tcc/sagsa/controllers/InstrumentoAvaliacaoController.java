package tcc.sagsa.controllers;

import tcc.sagsa.models.InstrumentoAvaliacao;
import tcc.sagsa.repositories.InstrumentoAvaliacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-instrumentos")
public class InstrumentoAvaliacaoController {

    private final InstrumentoAvaliacaoRepository repository;

    public InstrumentoAvaliacaoController(InstrumentoAvaliacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoAvaliacao>> findBySaId(@RequestParam Long saId) {
        return ResponseEntity.ok(repository.findBySituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<InstrumentoAvaliacao> create(@Valid @RequestBody InstrumentoAvaliacao entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}

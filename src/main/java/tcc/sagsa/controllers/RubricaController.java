package tcc.sagsa.controllers;

import tcc.sagsa.models.CriterioRubrica;
import tcc.sagsa.repositories.CriterioRubricaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sapz-rubricas")
public class RubricaController {

    private final CriterioRubricaRepository repository;

    public RubricaController(CriterioRubricaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CriterioRubrica>> findByCriterioId(@RequestParam Long criterioId) {
        return ResponseEntity.ok(repository.findByCriterioIdOrderByNivelAsc(criterioId));
    }

    @PostMapping
    public ResponseEntity<CriterioRubrica> create(@Valid @RequestBody CriterioRubrica entity) {
        return ResponseEntity.ok(repository.save(entity));
    }
}
package tcc.sagsa.controllers;

import tcc.sagsa.models.Turma;
import tcc.sagsa.repositories.TurmaRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;

    public TurmaController(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll() {
        return ResponseEntity.ok(turmaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada. ID: " + id));
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> create(@Valid @RequestBody Turma turma) {
        Turma saved = turmaRepository.save(turma);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

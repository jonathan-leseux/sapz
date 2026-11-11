package tcc.sagsa.controllers;

import tcc.sagsa.models.PlanoCurso;
import tcc.sagsa.repositories.PlanoCursoRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planos-curso")
public class PlanoCursoController {

    private final PlanoCursoRepository repository;

    public PlanoCursoController(PlanoCursoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<PlanoCurso>> findAll(@RequestParam(required = false) Long cursoId) {
        if (cursoId != null) {
            return ResponseEntity.ok(repository.findByCursoId(cursoId));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoCurso> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Plano de Curso não encontrado. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<PlanoCurso> create(@Valid @RequestBody PlanoCurso entity) {
        PlanoCurso saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

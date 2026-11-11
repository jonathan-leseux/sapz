package tcc.sagsa.controllers;

import tcc.sagsa.models.Modulo;
import tcc.sagsa.repositories.ModuloRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloRepository repository;

    public ModuloController(ModuloRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Modulo>> findAll(@RequestParam(required = false) Long planoCursoId) {
        if (planoCursoId != null) {
            return ResponseEntity.ok(repository.findByPlanoCursoIdOrderByOrdemAsc(planoCursoId));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Módulo não encontrado. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<Modulo> create(@Valid @RequestBody Modulo entity) {
        Modulo saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

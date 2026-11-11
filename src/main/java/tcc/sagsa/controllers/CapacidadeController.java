package tcc.sagsa.controllers;

import tcc.sagsa.models.Capacidade;
import tcc.sagsa.repositories.CapacidadeRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/capacidades")
public class CapacidadeController {

    private final CapacidadeRepository repository;

    public CapacidadeController(CapacidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Capacidade>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capacidade> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Capacidade não encontrada. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<Capacidade> create(@Valid @RequestBody Capacidade entity) {
        Capacidade saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
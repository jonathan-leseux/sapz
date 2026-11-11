package tcc.sagsa.controllers;

import tcc.sagsa.models.Conhecimento;
import tcc.sagsa.repositories.ConhecimentoRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

    private final ConhecimentoRepository repository;

    public ConhecimentoController(ConhecimentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Conhecimento>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conhecimento> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conhecimento não encontrado. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<Conhecimento> create(@Valid @RequestBody Conhecimento entity) {
        Conhecimento saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
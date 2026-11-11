package tcc.sagsa.controllers;

import tcc.sagsa.models.Perfil;
import tcc.sagsa.repositories.PerfilRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilRepository repository;

    public PerfilController(PerfilRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Long id) {
        Perfil entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado. ID: " + id));
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<Perfil> create(@Valid @RequestBody Perfil entity) {
        Perfil saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
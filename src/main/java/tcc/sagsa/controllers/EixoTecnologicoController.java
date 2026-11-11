package tcc.sagsa.controllers;

import tcc.sagsa.models.EixoTecnologico;
import tcc.sagsa.repositories.EixoTecnologicoRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/eixos-tecnologicos")
public class EixoTecnologicoController {

    private final EixoTecnologicoRepository repository;

    public EixoTecnologicoController(EixoTecnologicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<EixoTecnologico>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EixoTecnologico> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Eixo Tecnológico não encontrado. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<EixoTecnologico> create(@Valid @RequestBody EixoTecnologico entity) {
        EixoTecnologico saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

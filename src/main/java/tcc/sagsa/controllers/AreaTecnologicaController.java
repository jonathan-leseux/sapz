package tcc.sagsa.controllers;

import tcc.sagsa.models.AreaTecnologica;
import tcc.sagsa.repositories.AreaTecnologicaRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/areas-tecnologicas")
public class AreaTecnologicaController {

    private final AreaTecnologicaRepository repository;

    public AreaTecnologicaController(AreaTecnologicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AreaTecnologica>> findAll(@RequestParam(required = false) Long eixoId) {
        if (eixoId != null) {
            return ResponseEntity.ok(repository.findByEixoTecnologicoId(eixoId));
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaTecnologica> findById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Área Tecnológica não encontrada. ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<AreaTecnologica> create(@Valid @RequestBody AreaTecnologica entity) {
        AreaTecnologica saved = repository.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

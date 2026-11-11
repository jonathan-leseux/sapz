package tcc.sagsa.controllers;

import tcc.sagsa.models.UnidadeCurricular;
import tcc.sagsa.repositories.UnidadeCurricularRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/unidades-curriculares")
public class UnidadeCurricularController {

    private final UnidadeCurricularRepository ucRepository;

    public UnidadeCurricularController(UnidadeCurricularRepository ucRepository) {
        this.ucRepository = ucRepository;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeCurricular>> findAll() {
        return ResponseEntity.ok(ucRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeCurricular> findById(@PathVariable Long id) {
        UnidadeCurricular uc = ucRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Unidade Curricular não encontrada. ID: " + id));
        return ResponseEntity.ok(uc);
    }

    @PostMapping
    public ResponseEntity<UnidadeCurricular> create(@Valid @RequestBody UnidadeCurricular uc) {
        UnidadeCurricular saved = ucRepository.save(uc);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}

package tcc.sagsa.controllers;

import tcc.sagsa.models.Curso;
import tcc.sagsa.repositories.CursoRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado. ID: " + id));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> create(@Valid @RequestBody Curso curso) {
        Curso saved = cursoRepository.save(curso);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
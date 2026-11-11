package tcc.sagsa.controllers;

import tcc.sagsa.models.Escola;
import tcc.sagsa.repositories.EscolaRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

    private final EscolaRepository escolaRepository;

    public EscolaController(EscolaRepository escolaRepository) {
        this.escolaRepository = escolaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Escola>> findAll() {
        return ResponseEntity.ok(escolaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> findById(@PathVariable Long id) {
        Escola escola = escolaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada. ID: " + id));
        return ResponseEntity.ok(escola);
    }

    @PostMapping
    public ResponseEntity<Escola> create(@Valid @RequestBody Escola escola) {
        Escola saved = escolaRepository.save(escola);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escola> update(@PathVariable Long id, @Valid @RequestBody Escola escolaDetails) {
        Escola escola = escolaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada. ID: " + id));

        escola.setNome(escolaDetails.getNome());
        escola.setCodigoUnidade(escolaDetails.getCodigoUnidade());
        escola.setCidade(escolaDetails.getCidade());
        escola.setEstado(escolaDetails.getEstado());
        escola.setAtivo(escolaDetails.getAtivo());

        return ResponseEntity.ok(escolaRepository.save(escola));
    }
}

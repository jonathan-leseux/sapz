package tcc.sagsa.controllers;

import tcc.sagsa.models.SituacaoAprendizagem;
import tcc.sagsa.models.dtos.SituacaoAprendizagemDTO;
import tcc.sagsa.services.SituacaoAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/situacoes-aprendizagem")
public class SituacaoAprendizagemController {

    private final SituacaoAprendizagemService service;

    public SituacaoAprendizagemController(SituacaoAprendizagemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SituacaoAprendizagem>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoAprendizagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SituacaoAprendizagem> create(@Valid @RequestBody SituacaoAprendizagemDTO dto,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        SituacaoAprendizagem created = service.create(dto, userDetails.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }
}
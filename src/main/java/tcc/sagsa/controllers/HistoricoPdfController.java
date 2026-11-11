package tcc.sagsa.controllers;

import tcc.sagsa.models.dtos.HistoricoPdfDTO;
import tcc.sagsa.models.dtos.HistoricoPdfResponseDTO;
import tcc.sagsa.services.SapzComplementosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sapz-historico-pdf")
public class HistoricoPdfController {

    private final SapzComplementosService service;

    public HistoricoPdfController(SapzComplementosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoPdfResponseDTO>> findBySaId(@RequestParam(required = false) Long saId) {
        return ResponseEntity.ok(service.findHistoricoPdfBySa(saId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoPdfResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findHistoricoPdfById(id));
    }

    @PostMapping
    public ResponseEntity<HistoricoPdfResponseDTO> create(@Valid @RequestBody HistoricoPdfDTO dto,
                                                         Authentication authentication) {
        HistoricoPdfResponseDTO created = service.createHistoricoPdf(dto, authentication.getName());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }
}
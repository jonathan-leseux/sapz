package tcc.sagsa.services;

import tcc.sagsa.models.Escola;
import tcc.sagsa.models.dtos.EscolaDTO;
import tcc.sagsa.repositories.EscolaRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscolaService {

    private final EscolaRepository repository;

    public EscolaService(EscolaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EscolaDTO> findAll() {
        return repository.findAll().stream().map(EscolaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EscolaDTO findById(Long id) {
        Escola entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada. ID: " + id));
        return new EscolaDTO(entity);
    }

    @Transactional
    public EscolaDTO create(EscolaDTO dto) {
        Escola entity = Escola.builder()
                .nome(dto.getNome())
                .codigoUnidade(dto.getCodigoUnidade())
                .cidade(dto.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : "RS")
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
        return new EscolaDTO(repository.save(entity));
    }

    @Transactional
    public EscolaDTO update(Long id, EscolaDTO dto) {
        Escola entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada. ID: " + id));
        entity.setNome(dto.getNome());
        entity.setCodigoUnidade(dto.getCodigoUnidade());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setAtivo(dto.getAtivo());
        return new EscolaDTO(repository.save(entity));
    }
}
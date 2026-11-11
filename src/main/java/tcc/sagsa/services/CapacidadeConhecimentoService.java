package tcc.sagsa.services;

import tcc.sagsa.models.Capacidade;
import tcc.sagsa.models.Conhecimento;
import tcc.sagsa.models.UnidadeCurricular;
import tcc.sagsa.models.dtos.CapacidadeDTO;
import tcc.sagsa.models.dtos.ConhecimentoDTO;
import tcc.sagsa.repositories.CapacidadeRepository;
import tcc.sagsa.repositories.ConhecimentoRepository;
import tcc.sagsa.repositories.UnidadeCurricularRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapacidadeConhecimentoService {

    private final CapacidadeRepository capacidadeRepository;
    private final ConhecimentoRepository conhecimentoRepository;
    private final UnidadeCurricularRepository ucRepository;

    public CapacidadeConhecimentoService(CapacidadeRepository capacidadeRepository,
                                         ConhecimentoRepository conhecimentoRepository,
                                         UnidadeCurricularRepository ucRepository) {
        this.capacidadeRepository = capacidadeRepository;
        this.conhecimentoRepository = conhecimentoRepository;
        this.ucRepository = ucRepository;
    }

    // Capacidades
    @Transactional(readOnly = true)
    public List<CapacidadeDTO> findAllCapacidades() {
        return capacidadeRepository.findAll().stream().map(CapacidadeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CapacidadeDTO findCapacidadeById(Long id) {
        return new CapacidadeDTO(capacidadeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Capacidade não encontrada. ID: " + id)));
    }

    @Transactional
    public CapacidadeDTO createCapacidade(CapacidadeDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new ObjectNotFoundException("Unidade Curricular não encontrada. ID: " + dto.getUnidadeCurricularId()));

        Capacidade entity = Capacidade.builder()
                .unidadeCurricular(uc)
                .tipo(dto.getTipo())
                .dominio(dto.getDominio())
                .descricao(dto.getDescricao())
                .build();
        return new CapacidadeDTO(capacidadeRepository.save(entity));
    }

    // Conhecimentos
    @Transactional(readOnly = true)
    public List<ConhecimentoDTO> findAllConhecimentos() {
        return conhecimentoRepository.findAll().stream().map(ConhecimentoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConhecimentoDTO findConhecimentoById(Long id) {
        return new ConhecimentoDTO(conhecimentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conhecimento não encontrado. ID: " + id)));
    }

    @Transactional
    public ConhecimentoDTO createConhecimento(ConhecimentoDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new ObjectNotFoundException("Unidade Curricular não encontrada. ID: " + dto.getUnidadeCurricularId()));

        Conhecimento entity = Conhecimento.builder()
                .unidadeCurricular(uc)
                .topico(dto.getTopico())
                .detalhamento(dto.getDetalhamento())
                .build();
        return new ConhecimentoDTO(conhecimentoRepository.save(entity));
    }
}
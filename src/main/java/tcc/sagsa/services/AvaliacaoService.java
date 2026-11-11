package tcc.sagsa.services;

import tcc.sagsa.models.*;
import tcc.sagsa.models.dtos.*;
import tcc.sagsa.repositories.*;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final InstrumentoAvaliacaoRepository instrumentoRepository;
    private final CriterioAvaliacaoRepository criterioRepository;
    private final CriterioRubricaRepository rubricaRepository;
    private final SituacaoAprendizagemRepository saRepository;
    private final CapacidadeRepository capacidadeRepository;

    public AvaliacaoService(InstrumentoAvaliacaoRepository instrumentoRepository,
                            CriterioAvaliacaoRepository criterioRepository,
                            CriterioRubricaRepository rubricaRepository,
                            SituacaoAprendizagemRepository saRepository,
                            CapacidadeRepository capacidadeRepository) {
        this.instrumentoRepository = instrumentoRepository;
        this.criterioRepository = criterioRepository;
        this.rubricaRepository = rubricaRepository;
        this.saRepository = saRepository;
        this.capacidadeRepository = capacidadeRepository;
    }

    @Transactional(readOnly = true)
    public List<InstrumentoAvaliacaoDTO> findInstrumentosBySa(Long saId) {
        return instrumentoRepository.findBySituacaoAprendizagemId(saId)
                .stream().map(InstrumentoAvaliacaoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public InstrumentoAvaliacaoDTO createInstrumento(InstrumentoAvaliacaoDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + dto.getSituacaoAprendizagemId()));

        InstrumentoAvaliacao entity = InstrumentoAvaliacao.builder()
                .situacaoAprendizagem(sa)
                .nomeInstrumento(dto.getNomeInstrumento())
                .descricao(dto.getDescricao())
                .tipoMetodo(dto.getTipoMetodo() != null ? dto.getTipoMetodo() : "GRADUAL")
                .build();

        return new InstrumentoAvaliacaoDTO(instrumentoRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<CriterioAvaliacaoDTO> findCriteriosByInstrumento(Long instrumentoId) {
        return criterioRepository.findByInstrumentoId(instrumentoId)
                .stream().map(CriterioAvaliacaoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CriterioAvaliacaoDTO createCriterio(CriterioAvaliacaoDTO dto) {
        InstrumentoAvaliacao inst = instrumentoRepository.findById(dto.getInstrumentoId())
                .orElseThrow(() -> new ObjectNotFoundException("Instrumento de avaliação não encontrado. ID: " + dto.getInstrumentoId()));

        Capacidade cap = capacidadeRepository.findById(dto.getCapacidadeId())
                .orElseThrow(() -> new ObjectNotFoundException("Capacidade não encontrada. ID: " + dto.getCapacidadeId()));

        CriterioAvaliacao entity = CriterioAvaliacao.builder()
                .instrumento(inst)
                .capacidade(cap)
                .descricaoCriterio(dto.getDescricaoCriterio())
                .prioridadePeso(dto.getPrioridadePeso())
                .build();

        return new CriterioAvaliacaoDTO(criterioRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<CriterioRubricaDTO> findRubricasByCriterio(Long criterioId) {
        return rubricaRepository.findByCriterioIdOrderByNivelAsc(criterioId)
                .stream().map(CriterioRubricaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CriterioRubricaDTO createRubrica(CriterioRubricaDTO dto) {
        CriterioAvaliacao crit = criterioRepository.findById(dto.getCriterioId())
                .orElseThrow(() -> new ObjectNotFoundException("Critério de avaliação não encontrado. ID: " + dto.getCriterioId()));

        CriterioRubrica entity = CriterioRubrica.builder()
                .criterio(crit)
                .nivel(dto.getNivel())
                .descricaoRubrica(dto.getDescricaoRubrica())
                .pontuacao(dto.getPontuacao())
                .build();

        return new CriterioRubricaDTO(rubricaRepository.save(entity));
    }
}
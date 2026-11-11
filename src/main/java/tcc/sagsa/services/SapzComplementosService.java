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
public class SapzComplementosService {

    private final EstrategiaEnsinoRepository estrategiaRepository;
    private final RecursoAmbienteRepository recursoRepository;
    private final CompartilhamentoRepository compartilhamentoRepository;
    private final HistoricoPdfRepository historicoPdfRepository;
    private final SituacaoAprendizagemRepository saRepository;
    private final EscolaRepository escolaRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public SapzComplementosService(EstrategiaEnsinoRepository estrategiaRepository,
                                   RecursoAmbienteRepository recursoRepository,
                                   CompartilhamentoRepository compartilhamentoRepository,
                                   HistoricoPdfRepository historicoPdfRepository,
                                   SituacaoAprendizagemRepository saRepository,
                                   EscolaRepository escolaRepository,
                                   CursoRepository cursoRepository,
                                   UsuarioRepository usuarioRepository) {
        this.estrategiaRepository = estrategiaRepository;
        this.recursoRepository = recursoRepository;
        this.compartilhamentoRepository = compartilhamentoRepository;
        this.historicoPdfRepository = historicoPdfRepository;
        this.saRepository = saRepository;
        this.escolaRepository = escolaRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Estrategias de Ensino
    @Transactional(readOnly = true)
    public List<EstrategiaEnsinoDTO> findEstrategiasBySa(Long saId) {
        return estrategiaRepository.findBySituacaoAprendizagemId(saId)
                .stream().map(EstrategiaEnsinoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public EstrategiaEnsinoDTO createEstrategia(EstrategiaEnsinoDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + dto.getSituacaoAprendizagemId()));

        EstrategiaEnsino entity = EstrategiaEnsino.builder()
                .situacaoAprendizagem(sa)
                .nomeEstrategia(dto.getNomeEstrategia())
                .descricaoAtividade(dto.getDescricaoAtividade())
                .build();
        return new EstrategiaEnsinoDTO(estrategiaRepository.save(entity));
    }

    // Recursos e Ambientes
    @Transactional(readOnly = true)
    public List<RecursoAmbienteDTO> findRecursosBySa(Long saId) {
        return recursoRepository.findBySituacaoAprendizagemId(saId)
                .stream().map(RecursoAmbienteDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public RecursoAmbienteDTO createRecurso(RecursoAmbienteDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + dto.getSituacaoAprendizagemId()));

        RecursoAmbiente entity = RecursoAmbiente.builder()
                .situacaoAprendizagem(sa)
                .ambientePedagogico(dto.getAmbientePedagogico())
                .recursosDidaticos(dto.getRecursosDidaticos())
                .build();
        return new RecursoAmbienteDTO(recursoRepository.save(entity));
    }

    // Compartilhamento
    @Transactional(readOnly = true)
    public List<CompartilhamentoDTO> findCompartilhamentosBySa(Long saId) {
        return compartilhamentoRepository.findBySituacaoAprendizagemId(saId)
                .stream().map(CompartilhamentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CompartilhamentoDTO createCompartilhamento(CompartilhamentoDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + dto.getSituacaoAprendizagemId()));

        Escola escola = dto.getEscolaId() != null ? escolaRepository.findById(dto.getEscolaId()).orElse(null) : null;
        Curso curso = dto.getCursoId() != null ? cursoRepository.findById(dto.getCursoId()).orElse(null) : null;
        Usuario destinatario = dto.getUsuarioDestinatarioId() != null ? usuarioRepository.findById(dto.getUsuarioDestinatarioId()).orElse(null) : null;

        Compartilhamento entity = Compartilhamento.builder()
                .situacaoAprendizagem(sa)
                .escola(escola)
                .curso(curso)
                .usuarioDestinatario(destinatario)
                .tipoCompartilhamento(dto.getTipoCompartilhamento())
                .build();
        return new CompartilhamentoDTO(compartilhamentoRepository.save(entity));
    }

    // Historico PDF
    @Transactional(readOnly = true)
    public List<HistoricoPdfResponseDTO> findHistoricoPdfBySa(Long saId) {
        List<HistoricoPdf> lista = (saId != null) 
                ? historicoPdfRepository.findBySituacaoAprendizagemId(saId) 
                : historicoPdfRepository.findAll();
        return lista.stream().map(HistoricoPdfResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HistoricoPdfResponseDTO findHistoricoPdfById(Long id) {
        HistoricoPdf entity = historicoPdfRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Histórico PDF não encontrado. ID: " + id));
        return new HistoricoPdfResponseDTO(entity);
    }

    @Transactional
    public HistoricoPdfResponseDTO createHistoricoPdf(HistoricoPdfDTO dto, String userEmail) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário logado não encontrado."));

        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + dto.getSituacaoAprendizagemId()));

        HistoricoPdf entity = HistoricoPdf.builder()
                .situacaoAprendizagem(sa)
                .versao(dto.getVersao() != null ? dto.getVersao() : "1.0")
                .caminhoArquivoPdf(dto.getCaminhoArquivoPdf())
                .hashConteudo(dto.getHashConteudo())
                .criadoPorUsuario(usuarioLogado)
                .build();

        HistoricoPdf saved = historicoPdfRepository.save(entity);
        return new HistoricoPdfResponseDTO(saved);
    }
}
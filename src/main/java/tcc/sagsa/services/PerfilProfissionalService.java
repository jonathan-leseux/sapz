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
public class PerfilProfissionalService {

    private final FuncaoRepository funcaoRepository;
    private final SubfuncaoRepository subfuncaoRepository;
    private final PadraoDesempenhoRepository padraoRepository;
    private final PlanoCursoRepository planoCursoRepository;

    public PerfilProfissionalService(FuncaoRepository funcaoRepository,
                                     SubfuncaoRepository subfuncaoRepository,
                                     PadraoDesempenhoRepository padraoRepository,
                                     PlanoCursoRepository planoCursoRepository) {
        this.funcaoRepository = funcaoRepository;
        this.subfuncaoRepository = subfuncaoRepository;
        this.padraoRepository = padraoRepository;
        this.planoCursoRepository = planoCursoRepository;
    }

    @Transactional(readOnly = true)
    public List<FuncaoDTO> findFuncoesByPlano(Long planoCursoId) {
        return funcaoRepository.findByPlanoCursoIdOrderByNumeroAsc(planoCursoId)
                .stream().map(FuncaoDTO::new).collect(Collectors.toList());
    }

    // Função
    @Transactional
    public FuncaoDTO createFuncao(FuncaoDTO dto) {
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new ObjectNotFoundException("Plano de Curso não encontrado. ID: " + dto.getPlanoCursoId()));

        Funcao funcao = Funcao.builder()
                .planoCurso(plano)
                .numero(dto.getNumero())
                .descricao(dto.getDescricao())
                .build();

        return new FuncaoDTO(funcaoRepository.save(funcao));
    }

    @Transactional(readOnly = true)
    public List<SubfuncaoDTO> findSubfuncoesByFuncao(Long funcaoId) {
        return subfuncaoRepository.findByFuncaoId(funcaoId)
                .stream().map(SubfuncaoDTO::new).collect(Collectors.toList());
    }

    // Subfunção
    @Transactional
    public SubfuncaoDTO createSubfuncao(SubfuncaoDTO dto) {
        Funcao funcao = funcaoRepository.findById(dto.getFuncaoId())
                .orElseThrow(() -> new ObjectNotFoundException("Função não encontrada. ID: " + dto.getFuncaoId()));

        Subfuncao subfuncao = Subfuncao.builder()
                .funcao(funcao)
                .codigoSubfuncao(dto.getCodigoSubfuncao())
                .descricao(dto.getDescricao())
                .build();

        return new SubfuncaoDTO(subfuncaoRepository.save(subfuncao));
    }

    // Padrão de Desempenho
    @Transactional(readOnly = true)
    public List<PadraoDesempenhoDTO> findPadroesBySubfuncao(Long subfuncaoId) {
        return padraoRepository.findBySubfuncaoId(subfuncaoId)
                .stream().map(PadraoDesempenhoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public PadraoDesempenhoDTO createPadrao(PadraoDesempenhoDTO dto) {
        Subfuncao subfuncao = subfuncaoRepository.findById(dto.getSubfuncaoId())
                .orElseThrow(() -> new ObjectNotFoundException("Subfunção não encontrada. ID: " + dto.getSubfuncaoId()));

        PadraoDesempenho padrao = PadraoDesempenho.builder()
                .subfuncao(subfuncao)
                .descricao(dto.getDescricao())
                .build();

        return new PadraoDesempenhoDTO(padraoRepository.save(padrao));
    }
}
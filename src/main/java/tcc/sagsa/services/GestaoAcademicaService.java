package tcc.sagsa.services;

import tcc.sagsa.models.*;
import tcc.sagsa.models.dtos.AlunoDTO;
import tcc.sagsa.models.dtos.TurmaDTO;
import tcc.sagsa.repositories.*;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestaoAcademicaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final EscolaRepository escolaRepository;
    private final PlanoCursoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;

    public GestaoAcademicaService(TurmaRepository turmaRepository,
                                  AlunoRepository alunoRepository,
                                  EscolaRepository escolaRepository,
                                  PlanoCursoRepository planoRepository,
                                  UsuarioRepository usuarioRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
        this.escolaRepository = escolaRepository;
        this.planoRepository = planoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Turmas
    @Transactional(readOnly = true)
    public List<TurmaDTO> findAllTurmas() {
        return turmaRepository.findAll().stream().map(TurmaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TurmaDTO findTurmaById(Long id) {
        return new TurmaDTO(turmaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada. ID: " + id)));
    }

    @Transactional
    public TurmaDTO createTurma(TurmaDTO dto) {
        Escola escola = escolaRepository.findById(dto.getEscolaId())
                .orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada. ID: " + dto.getEscolaId()));

        PlanoCurso plano = planoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new ObjectNotFoundException("Plano de Curso não encontrado. ID: " + dto.getPlanoCursoId()));

        Usuario coordenador = null;
        if (dto.getCoordenadorId() != null) {
            coordenador = usuarioRepository.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new ObjectNotFoundException("Coordenador não encontrado. ID: " + dto.getCoordenadorId()));
        }

        Turma entity = Turma.builder()
                .escola(escola)
                .planoCurso(plano)
                .coordenador(coordenador)
                .codigoTurma(dto.getCodigoTurma())
                .ano(dto.getAno())
                .semestre(dto.getSemestre())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
        return new TurmaDTO(turmaRepository.save(entity));
    }

    // Alunos
    @Transactional(readOnly = true)
    public List<AlunoDTO> findAllAlunos() {
        return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDTO findAlunoById(Long id) {
        return new AlunoDTO(alunoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: " + id)));
    }

    @Transactional
    public AlunoDTO createAluno(AlunoDTO dto) {
        Aluno entity = Aluno.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .matricula(dto.getMatricula())
                .build();
        return new AlunoDTO(alunoRepository.save(entity));
    }
}

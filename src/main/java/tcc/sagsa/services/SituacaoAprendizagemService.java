package tcc.sagsa.services;

import tcc.sagsa.models.*;
import tcc.sagsa.models.dtos.SituacaoAprendizagemDTO;
import tcc.sagsa.repositories.*;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class SituacaoAprendizagemService {

    private final SituacaoAprendizagemRepository repository;
    private final UnidadeCurricularRepository ucRepository;
    private final UsuarioRepository usuarioRepository;
    private final CapacidadeRepository capacidadeRepository;
    private final ConhecimentoRepository conhecimentoRepository;

    public SituacaoAprendizagemService(SituacaoAprendizagemRepository repository,
                                       UnidadeCurricularRepository ucRepository,
                                       UsuarioRepository usuarioRepository,
                                       CapacidadeRepository capacidadeRepository,
                                       ConhecimentoRepository conhecimentoRepository) {
        this.repository = repository;
        this.ucRepository = ucRepository;
        this.usuarioRepository = usuarioRepository;
        this.capacidadeRepository = capacidadeRepository;
        this.conhecimentoRepository = conhecimentoRepository;
    }

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagem> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public SituacaoAprendizagem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Situação de Aprendizagem não encontrada. ID: " + id));
    }

    @Transactional
    public SituacaoAprendizagem create(SituacaoAprendizagemDTO dto, String userEmail) {
        Usuario instrutor = usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("Instrutor logado não encontrado."));

        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new ObjectNotFoundException("Unidade Curricular não encontrada. ID: " + dto.getUnidadeCurricularId()));

        SituacaoAprendizagem sa = SituacaoAprendizagem.builder()
                .titulo(dto.getTitulo())
                .unidadeCurricular(uc)
                .instrutor(instrutor)
                .tipoEstrategia(dto.getTipoEstrategia())
                .contextualizacao(dto.getContextualizacao())
                .desafio(dto.getDesafio())
                .resultadosEsperados(dto.getResultadosEsperados())
                .cargaHorariaSa(dto.getCargaHorariaSa())
                .compartilhada(dto.getCompartilhada() != null ? dto.getCompartilhada() : false)
                .build();

        if (dto.getCapacidadesIds() != null && !dto.getCapacidadesIds().isEmpty()) {
            sa.setCapacidades(new HashSet<>(capacidadeRepository.findAllById(dto.getCapacidadesIds())));
        }

        if (dto.getConhecimentosIds() != null && !dto.getConhecimentosIds().isEmpty()) {
            sa.setConhecimentos(new HashSet<>(conhecimentoRepository.findAllById(dto.getConhecimentosIds())));
        }

        return repository.save(sa);
    }
}
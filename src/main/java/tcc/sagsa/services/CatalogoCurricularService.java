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
public class CatalogoCurricularService {

    private final EixoTecnologicoRepository eixoRepository;
    private final AreaTecnologicaRepository areaRepository;
    private final CursoRepository cursoRepository;
    private final PlanoCursoRepository planoRepository;
    private final ModuloRepository moduloRepository;
    private final UnidadeCurricularRepository ucRepository;

    public CatalogoCurricularService(EixoTecnologicoRepository eixoRepository,
                                    AreaTecnologicaRepository areaRepository,
                                    CursoRepository cursoRepository,
                                    PlanoCursoRepository planoRepository,
                                    ModuloRepository moduloRepository,
                                    UnidadeCurricularRepository ucRepository) {
        this.eixoRepository = eixoRepository;
        this.areaRepository = areaRepository;
        this.cursoRepository = cursoRepository;
        this.planoRepository = planoRepository;
        this.moduloRepository = moduloRepository;
        this.ucRepository = ucRepository;
    }

    // Eixos Tecnologicos
    @Transactional(readOnly = true)
    public List<EixoTecnologicoDTO> findAllEixos() {
        return eixoRepository.findAll().stream().map(EixoTecnologicoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EixoTecnologicoDTO findEixoById(Long id) {
        return new EixoTecnologicoDTO(eixoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Eixo Tecnológico não encontrado. ID: " + id)));
    }

    @Transactional
    public EixoTecnologicoDTO createEixo(EixoTecnologicoDTO dto) {
        EixoTecnologico entity = EixoTecnologico.builder().nome(dto.getNome()).build();
        return new EixoTecnologicoDTO(eixoRepository.save(entity));
    }

    // Areas Tecnologicas
    @Transactional(readOnly = true)
    public List<AreaTecnologicaDTO> findAreas(Long eixoId) {
        List<AreaTecnologica> list = (eixoId != null) ? areaRepository.findByEixoTecnologicoId(eixoId) : areaRepository.findAll();
        return list.stream().map(AreaTecnologicaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AreaTecnologicaDTO findAreaById(Long id) {
        return new AreaTecnologicaDTO(areaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Área Tecnológica não encontrada. ID: " + id)));
    }

    @Transactional
    public AreaTecnologicaDTO createArea(AreaTecnologicaDTO dto) {
        EixoTecnologico eixo = eixoRepository.findById(dto.getEixoTecnologicoId())
                .orElseThrow(() -> new ObjectNotFoundException("Eixo Tecnológico não encontrado. ID: " + dto.getEixoTecnologicoId()));

        AreaTecnologica entity = AreaTecnologica.builder()
                .eixoTecnologico(eixo)
                .nome(dto.getNome())
                .build();
        return new AreaTecnologicaDTO(areaRepository.save(entity));
    }

    // Cursos
    @Transactional(readOnly = true)
    public List<CursoDTO> findAllCursos() {
        return cursoRepository.findAll().stream().map(CursoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CursoDTO findCursoById(Long id) {
        return new CursoDTO(cursoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado. ID: " + id)));
    }

    @Transactional
    public CursoDTO createCurso(CursoDTO dto) {
        AreaTecnologica area = null;
        if (dto.getAreaTecnologicaId() != null) {
            area = areaRepository.findById(dto.getAreaTecnologicaId())
                    .orElseThrow(() -> new ObjectNotFoundException("Área Tecnológica não encontrada. ID: " + dto.getAreaTecnologicaId()));
        }

        Curso entity = Curso.builder()
                .areaTecnologica(area)
                .nome(dto.getNome())
                .tipoCurso(dto.getTipoCurso())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
        return new CursoDTO(cursoRepository.save(entity));
    }

    // Plano de Curso
    @Transactional(readOnly = true)
    public List<PlanoCursoDTO> findPlanos(Long cursoId) {
        List<PlanoCurso> list = (cursoId != null) ? planoRepository.findByCursoId(cursoId) : planoRepository.findAll();
        return list.stream().map(PlanoCursoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlanoCursoDTO findPlanoById(Long id) {
        return new PlanoCursoDTO(planoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Plano de Curso não encontrado. ID: " + id)));
    }

    @Transactional
    public PlanoCursoDTO createPlano(PlanoCursoDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado. ID: " + dto.getCursoId()));

        PlanoCurso entity = PlanoCurso.builder()
                .curso(curso)
                .codigoPlano(dto.getCodigoPlano())
                .versao(dto.getVersao() != null ? dto.getVersao() : "1.0")
                .cargaHorariaTotal(dto.getCargaHorariaTotal())
                .dataAprovacao(dto.getDataAprovacao())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
        return new PlanoCursoDTO(planoRepository.save(entity));
    }

    // Modulos
    @Transactional(readOnly = true)
    public List<ModuloDTO> findModulos(Long planoCursoId) {
        List<Modulo> list = (planoCursoId != null) ? moduloRepository.findByPlanoCursoIdOrderByOrdemAsc(planoCursoId) : moduloRepository.findAll();
        return list.stream().map(ModuloDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ModuloDTO findModuloById(Long id) {
        return new ModuloDTO(moduloRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Módulo não encontrado. ID: " + id)));
    }

    @Transactional
    public ModuloDTO createModulo(ModuloDTO dto) {
        PlanoCurso plano = planoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new ObjectNotFoundException("Plano de Curso não encontrado. ID: " + dto.getPlanoCursoId()));

        Modulo entity = Modulo.builder()
                .planoCurso(plano)
                .nome(dto.getNome())
                .tipoModulo(dto.getTipoModulo())
                .ordem(dto.getOrdem() != null ? dto.getOrdem() : 1)
                .cargaHoraria(dto.getCargaHoraria())
                .build();
        return new ModuloDTO(moduloRepository.save(entity));
    }

    // Unidades Curriculares
    @Transactional(readOnly = true)
    public List<UnidadeCurricularDTO> findAllUcs() {
        return ucRepository.findAll().stream().map(UnidadeCurricularDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UnidadeCurricularDTO findUcById(Long id) {
        return new UnidadeCurricularDTO(ucRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Unidade Curricular não encontrada. ID: " + id)));
    }

    @Transactional
    public UnidadeCurricularDTO createUc(UnidadeCurricularDTO dto) {
        Modulo modulo = moduloRepository.findById(dto.getModuloId())
                .orElseThrow(() -> new ObjectNotFoundException("Módulo não encontrado. ID: " + dto.getModuloId()));

        UnidadeCurricular entity = UnidadeCurricular.builder()
                .modulo(modulo)
                .nome(dto.getNome())
                .cargaHoraria(dto.getCargaHoraria())
                .objetivoGeral(dto.getObjetivoGeral())
                .ordem(dto.getOrdem() != null ? dto.getOrdem() : 1)
                .build();
        return new UnidadeCurricularDTO(ucRepository.save(entity));
    }
}

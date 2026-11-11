package tcc.sagsa.services;

import tcc.sagsa.models.Perfil;
import tcc.sagsa.repositories.PerfilRepository;
import tcc.sagsa.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilService {

    private final PerfilRepository repository;

    public PerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Perfil> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Perfil findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado. ID: " + id));
    }

    @Transactional
    public Perfil create(Perfil perfil) {
        return repository.save(perfil);
    }
}
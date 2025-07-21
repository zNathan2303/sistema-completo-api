package br.dev.nathan.projeto.service;

import java.util.List;
import br.dev.nathan.projeto.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.nathan.projeto.dto.PerfilDTO;
import br.dev.nathan.projeto.entity.PerfilEntity;
import br.dev.nathan.projeto.repository.PerfilRepository;

@Service
public class PerfilService {

    private final RecursoRepository recursoRepository;

	@Autowired
	private PerfilRepository perfilRepository;

    PerfilService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }
	
	public List<PerfilDTO> listarTodos() {
		List<PerfilEntity> perfis = perfilRepository.findAll();
		return perfis.stream().map(PerfilDTO::new).toList();
	}
	
	public void inserir(PerfilDTO perfil) {
		perfilRepository.save(new PerfilEntity(perfil));
	}
	
	public PerfilDTO alterar(PerfilDTO perfil) {
		PerfilEntity perfilEntity = new PerfilEntity(perfil);
		return new PerfilDTO(perfilRepository.save(perfilEntity));
	}
	
	public void excluir(Long id) {
		perfilRepository.delete(perfilRepository.findById(id).get());
	}
	
	public PerfilDTO buscarPorId(Long id) {
		return new PerfilDTO(perfilRepository.findById(id).get());
	}
}

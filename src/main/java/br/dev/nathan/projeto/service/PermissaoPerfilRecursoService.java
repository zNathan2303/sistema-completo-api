package br.dev.nathan.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.nathan.projeto.dto.PermissaoPerfilRecursoDTO;
import br.dev.nathan.projeto.entity.PermissaoPerfilRecursoEntity;
import br.dev.nathan.projeto.repository.PermissaoPerfilRecursoRepository;

@Service
public class PermissaoPerfilRecursoService {
	
	@Autowired
	private PermissaoPerfilRecursoRepository permissaoPerfilRecursoRepository;
	
	public List<PermissaoPerfilRecursoDTO> listarTodos() {
		List<PermissaoPerfilRecursoEntity> permissoes = permissaoPerfilRecursoRepository.findAll();
		return permissoes.stream().map(PermissaoPerfilRecursoDTO::new).toList();
	}
	
	public void inserir(PermissaoPerfilRecursoDTO permissaoPerfilRecurso) {
		PermissaoPerfilRecursoEntity permissaoPerfilRecursoEntity = new PermissaoPerfilRecursoEntity(permissaoPerfilRecurso);
		permissaoPerfilRecursoRepository.save(permissaoPerfilRecursoEntity);
	}
	
	public PermissaoPerfilRecursoDTO alterar(PermissaoPerfilRecursoDTO permissaoPerfilRecurso) {
		PermissaoPerfilRecursoEntity permissaoPerfilRecursoEntity = new PermissaoPerfilRecursoEntity(permissaoPerfilRecurso);
		return new PermissaoPerfilRecursoDTO(permissaoPerfilRecursoRepository.save(permissaoPerfilRecursoEntity));
	}
	
	public void excluir(Long id) {
		permissaoPerfilRecursoRepository.delete(permissaoPerfilRecursoRepository.findById(id).get());
	}
	
	public PermissaoPerfilRecursoDTO buscarPorId(Long id) {
		return new PermissaoPerfilRecursoDTO(permissaoPerfilRecursoRepository.findById(id).get());
	}

}

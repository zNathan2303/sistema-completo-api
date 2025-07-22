package br.dev.nathan.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.nathan.projeto.dto.PerfilUsuarioDTO;
import br.dev.nathan.projeto.entity.PerfilUsuarioEntity;
import br.dev.nathan.projeto.repository.PerfilUsuarioRepository;

@Service
public class PerfilUsuarioService {
	
	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;
	
	public List<PerfilUsuarioDTO> listarTodos() {
		List<PerfilUsuarioEntity> perfis = perfilUsuarioRepository.findAll();
		return perfis.stream().map(PerfilUsuarioDTO::new).toList();
	}
	
	public void inserir(PerfilUsuarioDTO perfilUsuario) {
		PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
		perfilUsuarioRepository.save(perfilUsuarioEntity);
	}
	
	public PerfilUsuarioDTO alterar(PerfilUsuarioDTO perfilUsuario) {
		PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
		return new PerfilUsuarioDTO(perfilUsuarioRepository.save(perfilUsuarioEntity));
	}
	
	public void excluir(Long id) {
		perfilUsuarioRepository.delete(perfilUsuarioRepository.findById(id).get());
	}
	
	public PerfilUsuarioDTO buscarPorId(Long id) {
		return new PerfilUsuarioDTO(perfilUsuarioRepository.findById(id).get());
	}
}

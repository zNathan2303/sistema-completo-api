package br.dev.nathan.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.dev.nathan.projeto.ProjetoApplication;
import br.dev.nathan.projeto.dto.UsuarioDTO;
import br.dev.nathan.projeto.entity.UsuarioEntity;
import br.dev.nathan.projeto.repository.UsuarioRepository;

// Vai falar para o Spring que essa é a camada Service, então ele pode continuar o que está fazendo
@Service
public class UsuarioService {

    private final ProjetoApplication projetoApplication;
	
	// Instanciar de forma automatica essa classe
	@Autowired
	private UsuarioRepository usuarioRepository;

    UsuarioService(ProjetoApplication projetoApplication) {
        this.projetoApplication = projetoApplication;
    }
	
	public List<UsuarioDTO> listarTodos() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}
	
	public void inserir(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioRepository.save(usuarioEntity);
	}
	
	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}
	
	public void excluir(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	
	public UsuarioDTO buscarPorId(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
	}

}

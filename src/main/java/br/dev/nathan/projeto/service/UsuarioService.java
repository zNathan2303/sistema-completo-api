package br.dev.nathan.projeto.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.nathan.projeto.ProjetoApplication;
import br.dev.nathan.projeto.dto.UsuarioDTO;
import br.dev.nathan.projeto.entity.UsuarioEntity;
import br.dev.nathan.projeto.entity.UsuarioVerificadorEntity;
import br.dev.nathan.projeto.entity.enums.TipoSituacaoUsuario;
import br.dev.nathan.projeto.repository.UsuarioRepository;
import br.dev.nathan.projeto.repository.UsuarioVerificadorRepository;

// Vai falar para o Spring que essa é a camada Service, então ele pode continuar o que está fazendo
@Service
public class UsuarioService {

    private final ProjetoApplication projetoApplication;
	
	// Instanciar de forma automatica essa classe
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioVerificadorRepository usuarioVerificadorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

    UsuarioService(ProjetoApplication projetoApplication) {
        this.projetoApplication = projetoApplication;
    }
	
	public List<UsuarioDTO> listarTodos() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}
	
	public void inserir(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuarioEntity);
	}
	
	public void inserirNovoUsuario(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
		usuarioEntity.setId(null);
		usuarioRepository.save(usuarioEntity);
		
		UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
		verificador.setUsuario(usuarioEntity);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusMillis(900000));
		usuarioVerificadorRepository.save(verificador);
		
		// TODO - Enviar um email para verificar a conta
		emailService.enviarEmailTexto(
				usuario.getEmail(), 
				"Novo usuário cadastrado", 
				"Você está recebendo um email de cadastro, o número de validação é " + verificador.getUuid());
		
	}
	
	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
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

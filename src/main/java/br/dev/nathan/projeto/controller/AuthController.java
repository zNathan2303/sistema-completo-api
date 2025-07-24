package br.dev.nathan.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.nathan.projeto.dto.AuthenticationDTO;
import br.dev.nathan.projeto.dto.UsuarioDTO;
import br.dev.nathan.projeto.service.AuthService;
import br.dev.nathan.projeto.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDTO) {
		return ResponseEntity.ok(authService.login(authDTO));
	}
	
	@PostMapping(value = "/novoUsuario")
	public void inserirNovoUsuario(@RequestBody UsuarioDTO novoUsuario) {
		usuarioService.inserirNovoUsuario(novoUsuario);
	}
}

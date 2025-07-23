package br.dev.nathan.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.nathan.projeto.dto.AuthenticationDTO;
import br.dev.nathan.projeto.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDTO) {
		return ResponseEntity.ok(authService.login(authDTO));
	}
	
}

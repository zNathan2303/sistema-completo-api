package br.dev.nathan.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.dev.nathan.projeto.dto.AcessDTO;
import br.dev.nathan.projeto.dto.AuthenticationDTO;
import br.dev.nathan.projeto.security.jwt.JwtUtils;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public AcessDTO login(AuthenticationDTO authDTO) {
		
		try {
			
			// cria mecanismo de credencial para o spring
			UsernamePasswordAuthenticationToken userAuth = 
					new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
			
			// prepara o mecanismo para autenticacao
			Authentication authetication = authenticationManager.authenticate(userAuth);
		
			// buscca usuario logado
			UserDetailsImpl userAuthenticate = (UserDetailsImpl)authetication.getPrincipal();
		
			String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
			
			AcessDTO acessDTO = new AcessDTO(token);
			
			return acessDTO;
			
		} catch(BadCredentialsException e) {
			// todo login ou senha invalido
		}
		return new AcessDTO("Acesso negado");
	}
}

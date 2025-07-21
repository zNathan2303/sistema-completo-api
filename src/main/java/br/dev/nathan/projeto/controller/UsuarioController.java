package br.dev.nathan.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.nathan.projeto.dto.UsuarioDTO;
import br.dev.nathan.projeto.repository.UsuarioRepository;
import br.dev.nathan.projeto.service.UsuarioService;

// A partir daqui o Spring vai entender que essa classe tem a responsibilidade de disponibilizar uma API REST para o front
@RestController
// qual url vai acessar esse controle
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
	
	// se o front fazer uma requisição do tipo GET no endereço /usuario, vai executar esse método
	@GetMapping
	public List<UsuarioDTO> listarTodos() {
		return usuarioService.listarTodos();
	}
	
	@PostMapping
	// uma requisição Post tem um cabeçalho e um corpo, e este deve ser passado como parametro
	public void inserir(@RequestBody UsuarioDTO usuario) {
		usuarioService.inserir(usuario);
	}
	
	@PutMapping
	public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario) {
		return usuarioService.alterar(usuario);
	}
	
	//http://endereco/usuario/5 (que é o id)
	@DeleteMapping("/{id}")
	//Interface de saida para mostrar a informação que eu quero
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		usuarioService.excluir(id);
		return ResponseEntity.ok().build();
	}

}

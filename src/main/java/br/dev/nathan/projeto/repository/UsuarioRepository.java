package br.dev.nathan.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.UsuarioEntity;

// O CRUD já vai estar nessa interface porque ela já vai estar herdando
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByLogin(String login);
	
}

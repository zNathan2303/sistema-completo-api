package br.dev.nathan.projeto.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.UsuarioEntity;
import br.dev.nathan.projeto.entity.UsuarioVerificadorEntity;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificadorEntity, Long> {

	public Optional<UsuarioVerificadorEntity> findByUuid(UUID uuid);
	
}

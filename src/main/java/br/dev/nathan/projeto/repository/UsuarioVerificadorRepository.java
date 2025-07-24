package br.dev.nathan.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.UsuarioVerificadorEntity;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificadorEntity, Long> {

}

package br.dev.nathan.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.PerfilUsuarioEntity;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuarioEntity, Long> {

}

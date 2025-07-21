package br.dev.nathan.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.PerfilEntity;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {

}

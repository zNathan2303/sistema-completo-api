package br.dev.nathan.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.nathan.projeto.entity.RecursoEntity;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Long> {

}

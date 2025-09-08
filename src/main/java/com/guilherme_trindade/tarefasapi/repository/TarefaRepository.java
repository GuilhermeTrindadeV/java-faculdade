package com.guilherme_trindade.tarefasapi.repository;

import com.guilherme_trindade.tarefasapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

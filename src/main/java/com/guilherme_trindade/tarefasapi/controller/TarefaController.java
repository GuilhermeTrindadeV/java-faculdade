package com.guilherme_trindade.tarefasapi.controller;

import com.guilherme_trindade.tarefasapi.model.Tarefa;
import com.guilherme_trindade.tarefasapi.repository.TarefaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Criar uma nova tarefa
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Listar todas as tarefas
    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Buscar tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Atualizar tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setNome(tarefaAtualizada.getNome());
            tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
            tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
            tarefa.setRu(tarefaAtualizada.getRu());
            tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefa);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Remover tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            tarefaRepository.delete(tarefa.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
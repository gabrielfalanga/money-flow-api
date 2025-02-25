package br.com.fiap.money_flow_api.controller;


import br.com.fiap.money_flow_api.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    // GET - listar todas as categorias
    @GetMapping()
    public List<Category> index() {
        return repository;
    }

    // GET - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        System.out.println("Buscando categoria: " + id);

        var category = repository
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());
    }

    // POST - criar categoria
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        System.out.println("Cadastrando..." + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    // DELETE - apagar categoria

    // EDITAR - editar categoria
}

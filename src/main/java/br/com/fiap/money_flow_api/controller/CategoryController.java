package br.com.fiap.money_flow_api.controller;


import br.com.fiap.money_flow_api.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Category> repository = new ArrayList<>();

    @GetMapping()
    public List<Category> index() {
        return repository;
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return getCategory(id); // 200
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        log.info("Cadastrando..." + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);
        repository.remove(getCategory(id));
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria " + id + " " + category);

        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);

        return category;
    }

    private Category getCategory(Long id) {
        return repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }
}

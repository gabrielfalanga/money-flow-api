package br.com.fiap.money_flow_api.controller;


import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired  // injeção de dependência
    private CategoryRepository repository;

    @GetMapping
    public List<Category> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable Long id) {
        return getCategory(id); // 200
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        log.info("Cadastrando..." + category.getName());
        repository.save(category);
        return ResponseEntity.status(201).body(category);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);
        repository.delete(getCategory(id));
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria " + id + " " + category);

        getCategory(id);
        category.setId(id);
        repository.save(category);

        return category;
    }

    private Category getCategory(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }
}

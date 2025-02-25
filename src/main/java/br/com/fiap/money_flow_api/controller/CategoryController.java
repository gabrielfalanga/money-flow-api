package br.com.fiap.money_flow_api.controller;


import br.com.fiap.money_flow_api.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    // GET - listar todas as categorias
    @GetMapping("/categories")
    public List<Category> index() {
        return List.of(
            new Category(1L, "Educação", "book.png"),
            new Category(1L, "Lazer", "little-tiger.png"),
            new Category(1L, "Salário", "payments.png")
        );
    }
}

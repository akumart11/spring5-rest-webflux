package avinash.springframework.spring5webfluxrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import avinash.springframework.spring5webfluxrest.domain.Category;
import avinash.springframework.spring5webfluxrest.repositories.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {
	
	private final CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("api/v1/categories")
	Flux<Category> list(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("api/v1/categories/{id}")
	Mono<Category> getByid(@PathVariable String id){
		return categoryRepository.findById(id);
	}
}

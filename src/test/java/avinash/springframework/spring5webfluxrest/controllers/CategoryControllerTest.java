package avinash.springframework.spring5webfluxrest.controllers;


import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;

import avinash.springframework.spring5webfluxrest.domain.Category;
import avinash.springframework.spring5webfluxrest.repositories.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoryControllerTest {
	
	WebTestClient webTestClient;
	
	CategoryRepository categoryRepository;
	
	CategoryController categoryController;
	
	@Before
	public void setUp() throws Exception {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryController = new CategoryController(categoryRepository);
		webTestClient = WebTestClient.bindToController(categoryController).build();
	}

	@Test
	public void testList() {
		BDDMockito.given(categoryRepository.findAll())
		.willReturn(Flux.just(Category.builder().description("Cat1").build(),
				Category.builder().description("Cat1").build()));
		
		webTestClient.get().uri("/api/v1/categories/")
		.exchange()
		.expectBodyList(Category.class)
		.hasSize(2);
	}

	@Test
	public void testGetByid() {
		BDDMockito.given(categoryRepository.findById("someid"))
		.willReturn(Mono.just(Category.builder().description("Cat1").build()));
		
		webTestClient.get()
		.uri("/api/v1/categories/someid")
		.exchange()
		.expectBody(Category.class);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void create() {
		BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
		.willReturn(Flux.just(Category.builder().description("Some").build()));
		
		Mono<Category> monoToSave = Mono.just(Category.builder().description("Some").build());
		
		webTestClient.post()
		.uri("/api/v1/categories/")
		.body(monoToSave,Category.class)
		.exchange()
		.expectStatus()
		.isCreated();
		
	}
	
	@Test
	public void update() {
		BDDMockito.given(categoryRepository.save(any(Category.class)))
		.willReturn(Mono.just(Category.builder().build()));
		
		Mono<Category> monoToUpdate = Mono.just(Category.builder().description("Some").build());
		
		webTestClient.put()
		.uri("/api/v1/categories/someid")
		.body(monoToUpdate,Category.class)
		.exchange()
		.expectStatus()
		.isOk();
	}
}

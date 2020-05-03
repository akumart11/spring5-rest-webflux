package avinash.springframework.spring5webfluxrest.controllers;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
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
		
		webTestClient.get().uri("/api/v1/catgeories/someid")
		.exchange()
		.expectBody(Category.class);
	}

}

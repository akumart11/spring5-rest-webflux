package avinash.springframework.spring5webfluxrest.controllers;

import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;

import avinash.springframework.spring5webfluxrest.domain.Category;
import avinash.springframework.spring5webfluxrest.domain.Customer;
import avinash.springframework.spring5webfluxrest.repositories.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerControllerTest {
	
	WebTestClient webTestClient;
	
	CustomerRepository customerRepository;
	
	CustomerController customerController;
	
	@Before
	public void setUp() throws Exception{
		customerRepository = Mockito.mock(CustomerRepository.class);
		customerController = new CustomerController(customerRepository);
		webTestClient = WebTestClient.bindToController(customerController).build();
	}
	@Test
	public void testList() {
		BDDMockito.given(customerRepository.findAll())
		.willReturn(Flux.just(Customer.builder().firstName("Ram").lastName("Sharma").build(),
				Customer.builder().firstName("Ram").lastName("Sharma").build()));
		
		webTestClient.get().uri("/api/v1/customers/")
		.exchange()
		.expectBodyList(Customer.class)
		.hasSize(2);
	}

	@Test
	public void testGetByid() {
		BDDMockito.given(customerRepository.findById("someid"))
		.willReturn(Mono.just(Customer.builder().firstName("Ram").lastName("Sharma").build()));
		
		webTestClient.get().uri("/api/v1/customers/someid")
		.exchange()
		.expectBody(Customer.class);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void create() {
		BDDMockito.given(customerRepository.saveAll(any(Publisher.class)))
		.willReturn(Flux.just(Customer.builder().firstName("Martin").lastName("Joel").build()));
		
		Mono<Customer> monoToSave = Mono.just(Customer.builder().firstName("Martin").lastName("Martin").build());
		
		webTestClient.post()
		.uri("/api/v1/customers/")
		.body(monoToSave,Customer.class)
		.exchange()
		.expectStatus()
		.isCreated();
		
	}
	
	@Test
	public void update() {
		BDDMockito.given(customerRepository.save(any(Customer.class)))
		.willReturn(Mono.just(Customer.builder().build()));
		
		Mono<Customer> monoToUpdate = Mono.just(Customer.builder().firstName("Martin").lastName("Martin").build());
		
		webTestClient.put()
		.uri("/api/v1/customers/someid")
		.body(monoToUpdate,Customer.class)
		.exchange()
		.expectStatus()
		.isOk();
	}
}

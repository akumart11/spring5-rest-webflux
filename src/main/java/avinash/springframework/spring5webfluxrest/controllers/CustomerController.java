package avinash.springframework.spring5webfluxrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import avinash.springframework.spring5webfluxrest.domain.Customer;
import avinash.springframework.spring5webfluxrest.repositories.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {
	
	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("api/v1/customers")
	Flux<Customer> list(){
		return customerRepository.findAll();
	}
	
	@GetMapping("api/v1/customers/{id}")
	Mono<Customer> getByid(@PathVariable String id){
		return customerRepository.findById(id);
	}
}

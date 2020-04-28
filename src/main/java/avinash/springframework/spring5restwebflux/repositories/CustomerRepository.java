package avinash.springframework.spring5restwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import avinash.springframework.spring5restwebflux.domain.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{

}

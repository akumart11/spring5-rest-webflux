package avinash.springframework.spring5webfluxrest.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import avinash.springframework.spring5webfluxrest.domain.Customer;

/**
 * Created by jt on 12/23/17.
 */
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}

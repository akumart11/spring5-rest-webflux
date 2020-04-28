package avinash.springframework.spring5restwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import avinash.springframework.spring5restwebflux.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String>{

}

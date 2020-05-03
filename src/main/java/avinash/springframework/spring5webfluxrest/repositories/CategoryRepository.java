package avinash.springframework.spring5webfluxrest.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import avinash.springframework.spring5webfluxrest.domain.Category;

/**
 * Created by jt on 12/23/17.
 */
public interface CategoryRepository extends ReactiveMongoRepository <Category, String>{
}

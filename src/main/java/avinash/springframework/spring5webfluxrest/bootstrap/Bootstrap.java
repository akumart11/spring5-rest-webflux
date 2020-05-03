package avinash.springframework.spring5webfluxrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import avinash.springframework.spring5webfluxrest.domain.Category;
import avinash.springframework.spring5webfluxrest.domain.Customer;
import avinash.springframework.spring5webfluxrest.repositories.CategoryRepository;
import avinash.springframework.spring5webfluxrest.repositories.CustomerRepository;

/**
 * Created by jt on 12/23/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0){
            //load data
            System.out.println("#### LOADING DATA ON BOOTSTRAP #####");

            categoryRepository.save(Category.builder()
                        .description("Fruits").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Breads").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Meats").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).block();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());

            vendorRepository.save(Customer.builder()
                        .firstName("Joe")
                        .lastName("Buck").build()).block();

            vendorRepository.save(Customer.builder()
                    .firstName("Micheal")
                    .lastName("Weston").build()).block();

            vendorRepository.save(Customer.builder()
                    .firstName("Jessie")
                    .lastName("Waters").build()).block();

            vendorRepository.save(Customer.builder()
                    .firstName("Bill")
                    .lastName("Nershi").build()).block();

            vendorRepository.save(Customer.builder()
                    .firstName("Jimmy")
                    .lastName("Buffett").build()).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());

        }



    }
}

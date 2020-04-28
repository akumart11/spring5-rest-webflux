package avinash.springframework.spring5restwebflux.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import avinash.springframework.spring5restwebflux.domain.Category;
import avinash.springframework.spring5restwebflux.domain.Customer;
import avinash.springframework.spring5restwebflux.repositories.CategoryRepository;
import avinash.springframework.spring5restwebflux.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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

            customerRepository.save(Customer.builder()
                        .firstName("Joe")
                        .lastName("Buck").build()).block();

            customerRepository.save(Customer.builder()
                    .firstName("Micheal")
                    .lastName("Weston").build()).block();

            customerRepository.save(Customer.builder()
                    .firstName("Jessie")
                    .lastName("Waters").build()).block();

            customerRepository.save(Customer.builder()
                    .firstName("Bill")
                    .lastName("Nershi").build()).block();

            customerRepository.save(Customer.builder()
                    .firstName("Jimmy")
                    .lastName("Buffett").build()).block();

            System.out.println("Loaded Vendors: " + customerRepository.count().block());

        }

    }
}
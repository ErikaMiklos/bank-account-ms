package fr.erika.customerservice;

import fr.erika.customerservice.config.GlobalConfig;
import fr.erika.customerservice.entities.Customer;
import fr.erika.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstName("Erika")
                            .lastName("Miklos")
                            .email("erika@mail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Jakab")
                            .lastName("Gipsz")
                            .email("jakab@mail.com")
                            .build()
            );
            customerRepository.saveAll(customerList);
        };
    }

}

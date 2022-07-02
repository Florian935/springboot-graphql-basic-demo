package com.florian935.basic.demo.graphql.controller.graphql;

import com.florian935.basic.demo.graphql.domain.Customer;
import com.florian935.basic.demo.graphql.domain.CustomerInput;
import com.florian935.basic.demo.graphql.domain.Order;
import com.florian935.basic.demo.graphql.domain.Product;
import com.florian935.basic.demo.graphql.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@Controller
public class CustomerGraphqlController {

    private final CustomerRepository customerRepository;

    public CustomerGraphqlController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    Mono<Customer> customerById(@Argument Integer customerId) {
        return this.customerRepository.findById(customerId).switchIfEmpty(Mono.just(new Customer(100, "NULL")));
    }

    @QueryMapping
    Flux<Customer> customers() {
        return this.customerRepository.findAll();
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer) {
        final List<Order> orders = Stream.iterate(1, n -> n + 1)
                .limit(3)
                .map(index -> new Order(index, customer.customerId()))
                .toList();

        return Flux.fromIterable(orders);
    }

    @SchemaMapping(typeName = "Order")
    Flux<Product> products(Order order) {
        final List<Product> products = Stream.iterate(1, n -> n + 1)
                .limit(3)
                .map(index -> new Product(index, order.orderId()))
                .toList();

        return Flux.fromIterable(products);
    }

    @MutationMapping
    Mono<Customer> addCustomer(@Argument String name) {
        return this.customerRepository.save(new Customer(null, name));
    }

    @MutationMapping
    Mono<Customer> addCustomerInput(@Argument CustomerInput customerInput) {
        return this.customerRepository.save(new Customer(null, customerInput.name()));
    }

    @MutationMapping
    Mono<Boolean> deleteCustomerById(@Argument Integer customerId) {
        return this.customerRepository.findById(customerId)
                .map(Customer::customerId)
                .flatMap(id -> {
                    this.customerRepository.deleteById(id);

                    return Mono.just(true);
                })
                .defaultIfEmpty(false);
    }
}

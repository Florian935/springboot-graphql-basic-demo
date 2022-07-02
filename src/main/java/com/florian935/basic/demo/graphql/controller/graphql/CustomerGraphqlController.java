package com.florian935.basic.demo.graphql.controller.graphql;

import com.florian935.basic.demo.graphql.domain.Customer;
import com.florian935.basic.demo.graphql.domain.Order;
import com.florian935.basic.demo.graphql.domain.Product;
import com.florian935.basic.demo.graphql.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class CustomerGraphqlController {

    private final CustomerRepository customerRepository;

    public CustomerGraphqlController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
}

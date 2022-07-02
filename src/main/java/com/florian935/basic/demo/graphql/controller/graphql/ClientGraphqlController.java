package com.florian935.basic.demo.graphql.controller.graphql;

import com.florian935.basic.demo.graphql.domain.Car;
import com.florian935.basic.demo.graphql.domain.Client;
import com.florian935.basic.demo.graphql.domain.Pet;
import com.florian935.basic.demo.graphql.repository.ClientRepository;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClientGraphqlController {

    private final ClientRepository clientRepository;

    public ClientGraphqlController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @QueryMapping
    Flux<Client> clients() {
        return this.clientRepository.findAll();
    }

    @BatchMapping
    Mono<Map<Client, Pet>> pet(List<Client> clients) {
        final Map<Client, Pet> clientsMap = clients.stream()
                .collect(Collectors.toMap(client -> client, client ->new Pet(1, "Pet ", client.clientId())));

        return Mono.just(clientsMap);
    }

    @BatchMapping
    Mono<Map<Client, List<Car>>> cars(List<Client> clients) {
        final Map<Client, List<Car>> clientsMap = clients.stream()
                .collect(Collectors.toMap(
                        client -> client,
                        client -> Stream.iterate(1, n -> n + 1)
                                .limit(2)
                                .map(index -> new Car(index, "Model " + index, client.clientId()))
                                .toList()
                ));

        return Mono.just(clientsMap);
    }
}

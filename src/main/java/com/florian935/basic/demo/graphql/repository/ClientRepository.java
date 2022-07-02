package com.florian935.basic.demo.graphql.repository;

import com.florian935.basic.demo.graphql.domain.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Integer> {
}

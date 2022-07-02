package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.List;

public record Client(@JsonProperty("clientId") @Id @Column(value = "client_id") Integer clientId, String userName,
                     Pet pet, List<Car> cars) {
}

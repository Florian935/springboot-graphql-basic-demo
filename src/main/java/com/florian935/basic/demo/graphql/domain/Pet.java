package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record Pet(@JsonProperty("petId") @Id Integer petId, String name, Integer clientId) {
}

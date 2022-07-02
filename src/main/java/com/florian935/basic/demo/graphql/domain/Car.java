package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record Car(@JsonProperty("carId") @Id Integer carId, String model, Integer clientId) {
}

package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Customer(@JsonProperty("id") @Id @Column(value = "customer_id") Integer customerId, @JsonProperty("name") String name) {
}

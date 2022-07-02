package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record Product(@JsonProperty("productId") @Id Integer productId, @JsonProperty("orderId") Integer orderId) {
}

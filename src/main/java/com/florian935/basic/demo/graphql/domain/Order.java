package com.florian935.basic.demo.graphql.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record Order(@JsonProperty ("orderId") @Id Integer orderId, @JsonProperty("customerId") Integer customerId) {
}

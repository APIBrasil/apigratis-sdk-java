package com.apibrasil.sdk.dto.base.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authorization {
    private String token;

    @JsonProperty("expires_in")
    private long expiresIn;

    private String type;
}


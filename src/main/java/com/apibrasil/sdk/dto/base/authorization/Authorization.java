package com.apibrasil.sdk.dto.base.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Authorization {
    private String token;

    @JsonProperty("expires_in")
    private long expiresIn;

    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authorization that)) return false;
        return expiresIn == that.expiresIn && Objects.equals(token, that.token) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiresIn, type);
    }
}


package com.apibrasil.sdk.dto.login;

import com.apibrasil.sdk.dto.base.authorization.Authorization;
import com.apibrasil.sdk.dto.base.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;


public class LoginRes {
    private boolean error;
    private String message;
    @JsonProperty("user")
    private User user;
    @JsonProperty("invoices")
    private List<Map<String, String>> invoices;
    @JsonProperty("authorization")
    private Authorization authorization;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Map<String, String>> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Map<String, String>> invoices) {
        this.invoices = invoices;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }
}
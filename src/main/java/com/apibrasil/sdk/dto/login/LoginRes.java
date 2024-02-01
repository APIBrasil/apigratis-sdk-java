package com.apibrasil.sdk.dto.login;

import com.apibrasil.sdk.dto.base.authorization.Authorization;
import com.apibrasil.sdk.dto.base.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class LoginRes {
    private boolean error;
    private String message;
    @JsonProperty("user")
    private User user;
    @JsonProperty("invoices")
    private List<Map<String, String>> invoices;
    @JsonProperty("authorization")
    private Authorization authorization;

}
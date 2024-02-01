package com.apibrasil.sdk.dto.login;

import com.apibrasil.sdk.dto.base.authorization.Authorization;
import com.apibrasil.sdk.dto.base.user.User;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class LoginRes {
    private boolean error;
    private String message;
    private User user;
    private List<Map<String, String>> invoices;
    private Authorization authorization;

}
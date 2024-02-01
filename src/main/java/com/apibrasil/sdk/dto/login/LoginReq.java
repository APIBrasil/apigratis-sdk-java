package com.apibrasil.sdk.dto.login;

import lombok.Data;

@Data
public class LoginReq {
    private String email;
    private String password;
}

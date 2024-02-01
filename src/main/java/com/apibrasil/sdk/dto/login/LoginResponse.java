package com.apibrasil.sdk.dto.login;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data

public class LoginResponse {
    private boolean error;
    private String message;
    private User user;
    private List<Map<String, String>> invoices;
    private Authorization authorization;

    @Data
    public static class User {
        private String search;
        private String cellphone;
        private String email;
        private String first_name;
        private String last_name;
        private String status;
        private String status_finance;
        private int devices_count;
        private int invoices_open_count;
        private String last_login_ip;
        private String updated_at;
        private String last_login_at;
        private List<Device> devices;
    }

    @Data
    public static class Device {
        private String number_device;
        private String search;
        private String server_search;
        private String status;
        private String status_situation;
        private String created_at;
        private String device_ip;
        private String device_key;
        private String device_name;
        private String device_token;
        private String last_activity;
        private ApiInfo apiInfo;
    }

    @Data
    public static class ApiInfo {
        private String description;
        private String name;
        private String search;
    }

    @Data
    public static class Authorization {
        private String token;
        private long expires_in;
        private String type;
    }
}
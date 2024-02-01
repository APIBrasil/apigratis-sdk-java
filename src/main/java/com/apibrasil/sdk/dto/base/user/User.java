package com.apibrasil.sdk.dto.base.user;

import com.apibrasil.sdk.dto.base.device.Device;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private String search;
    private String cellphone;
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String status;

    @JsonProperty("status_finance")
    private String statusFinance;

    @JsonProperty("devices_count")
    private int devicesCount;

    @JsonProperty("invoices_open_count")
    private int invoicesOpenCount;

    @JsonProperty("last_login_ip")
    private String lastLoginIp;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("last_login_at")
    private String lastLoginAt;

    private List<Device> devices;
}

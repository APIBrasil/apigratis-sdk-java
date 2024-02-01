package com.apibrasil.sdk.dto.base.device;

import com.apibrasil.sdk.dto.base.info.ApiInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Device {
    @JsonProperty("number_device")
    private String numberDevice;

    private String search;

    @JsonProperty("server_search")
    private String serverSearch;

    private String status;

    @JsonProperty("status_situation")
    private String statusSituation;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("device_ip")
    private String deviceIp;

    @JsonProperty("device_key")
    private String deviceKey;

    @JsonProperty("device_name")
    private String deviceName;

    @JsonProperty("device_token")
    private String deviceToken;

    @JsonProperty("last_activity")
    private String lastActivity;

    @JsonProperty("api_info")
    private ApiInfo apiInfo;
}

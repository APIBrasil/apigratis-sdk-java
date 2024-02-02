package com.apibrasil.sdk.dto.base.user;

import com.apibrasil.sdk.dto.base.device.Device;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;


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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusFinance() {
        return statusFinance;
    }

    public void setStatusFinance(String statusFinance) {
        this.statusFinance = statusFinance;
    }

    public int getDevicesCount() {
        return devicesCount;
    }

    public void setDevicesCount(int devicesCount) {
        this.devicesCount = devicesCount;
    }

    public int getInvoicesOpenCount() {
        return invoicesOpenCount;
    }

    public void setInvoicesOpenCount(int invoicesOpenCount) {
        this.invoicesOpenCount = invoicesOpenCount;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return devicesCount == user.devicesCount && invoicesOpenCount == user.invoicesOpenCount && Objects.equals(search, user.search) && Objects.equals(cellphone, user.cellphone) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(status, user.status) && Objects.equals(statusFinance, user.statusFinance) && Objects.equals(lastLoginIp, user.lastLoginIp) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(lastLoginAt, user.lastLoginAt) && Objects.equals(devices, user.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(search, cellphone, email, firstName, lastName, status, statusFinance, devicesCount, invoicesOpenCount, lastLoginIp, updatedAt, lastLoginAt, devices);
    }
}

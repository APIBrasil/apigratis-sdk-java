package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.List;
import java.util.Map;

/** IP whitelist da conta ({@code /ip-whitelist/*}). */
public class IpWhitelistService extends BaseService {

    public IpWhitelistService(ApiHttpClient http) {
        super(http);
    }

    /** Lista a whitelist atual: {@code GET /ip-whitelist}. */
    public Map<String, Object> list() {
        return get("ip-whitelist");
    }

    /** Alias de {@link #list()} — mesmo nome usado nas SDKs Node/PHP. */
    public Map<String, Object> get() {
        return list();
    }

    /** Substitui a whitelist: {@code PUT /ip-whitelist}. */
    public Map<String, Object> set(List<String> ipWhitelist) {
        return put("ip-whitelist", Json.of("ip_whitelist", ipWhitelist));
    }

    /** Substitui a whitelist (string separada por vírgulas): {@code PUT /ip-whitelist}. */
    public Map<String, Object> set(String ipWhitelist) {
        return put("ip-whitelist", Json.of("ip_whitelist", ipWhitelist));
    }

    /** Adiciona um IP/CIDR: {@code POST /ip-whitelist/add}. */
    public Map<String, Object> add(String entry) {
        return post("ip-whitelist/add", Json.of("entry", entry));
    }

    /** Remove um IP/CIDR: {@code DELETE /ip-whitelist/remove}. */
    public Map<String, Object> remove(String entry) {
        return delete("ip-whitelist/remove", Json.of("entry", entry), RequestOptions.NONE);
    }

    /** Adiciona o IP atual: {@code POST /ip-whitelist/add-current}. */
    public Map<String, Object> addCurrent() {
        return post("ip-whitelist/add-current", null);
    }

    /** Limpa a whitelist: {@code POST /ip-whitelist/reset}. */
    public Map<String, Object> reset() {
        return post("ip-whitelist/reset", null);
    }

    /** Valida um IP/CIDR: {@code POST /ip-whitelist/validate}. */
    public Map<String, Object> validate(String entry) {
        return post("ip-whitelist/validate", Json.of("entry", entry));
    }

    /** IP de origem visto pelo gateway: {@code GET /ip-whitelist/current-ip}. */
    public Map<String, Object> currentIp() {
        return get("ip-whitelist/current-ip");
    }
}

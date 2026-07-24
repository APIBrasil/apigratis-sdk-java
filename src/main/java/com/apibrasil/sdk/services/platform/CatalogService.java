package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/** Catálogo de APIs, planos, documentações e servidores. */
public class CatalogService extends BaseService {

    public CatalogService(ApiHttpClient http) {
        super(http);
    }

    /** Lista as APIs do catálogo: {@code GET /apis}. */
    public Map<String, Object> apis() {
        return apis(null, RequestOptions.NONE);
    }

    /** Lista as APIs do catálogo filtrando por texto: {@code GET /apis?search=...}. */
    public Map<String, Object> apis(String search) {
        return apis(search, RequestOptions.NONE);
    }

    public Map<String, Object> apis(String search, RequestOptions options) {
        RequestOptions resolved = search == null ? options : options.withQuery(Json.of("search", search));
        return get("apis", resolved);
    }

    /** Detalha uma API: {@code GET /apis/{identifier}}. */
    public Map<String, Object> api(String identifier) {
        return get("apis/" + identifier);
    }

    /** Detalha uma API pelo nome: {@code GET /apis/name/{name}}. */
    public Map<String, Object> apiByName(String name) {
        return get("apis/name/" + URLEncoder.encode(name, StandardCharsets.UTF_8));
    }

    /** Categorias das APIs: {@code GET /apis/categories}. */
    public Map<String, Object> apiCategories() {
        return get("apis/categories");
    }

    /** APIs contratadas na conta: {@code GET /apis/list}. */
    public Map<String, Object> myApis() {
        return get("apis/list");
    }

    /** APIs de um device: {@code GET /apis/device/{deviceToken}}. */
    public Map<String, Object> apisByDevice(String deviceToken) {
        return get("apis/device/" + deviceToken);
    }

    /** Planos disponíveis: {@code GET /plans}. */
    public Map<String, Object> plans() {
        return get("plans");
    }

    /** Documentações públicas: {@code GET /documentations}. */
    public Map<String, Object> documentations() {
        return get("documentations");
    }

    /** Documentações de um servidor: {@code GET /documentations/server/{search}}. */
    public Map<String, Object> documentationsByServer(String serverSearch) {
        return get("documentations/server/" + serverSearch);
    }

    /** Servidores disponíveis: {@code GET /servers}. */
    public Map<String, Object> servers() {
        return get("servers");
    }

    /** Resolve a URL de um endpoint do catálogo: {@code POST /endpoint/url}. */
    public Map<String, Object> endpointUrl(Map<String, Object> body) {
        return post("endpoint/url", body);
    }

    /** Resolve o body de exemplo de um endpoint: {@code POST /endpoint/body}. */
    public Map<String, Object> endpointBody(Map<String, Object> body) {
        return post("endpoint/body", body);
    }

    /** Status da plataforma: {@code GET /status}. */
    public Map<String, Object> status() {
        return get("status");
    }
}

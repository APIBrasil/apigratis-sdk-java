package com.apibrasil.sdk.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Utilitários de JSON da SDK. As respostas do gateway são devolvidas como
 * {@code Map<String,Object>}; use {@link #of(Object...)} para montar os
 * corpos de requisição sem cerimônia:
 *
 * <pre>{@code
 * api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
 * }</pre>
 */
public final class Json {

    /** {@link ObjectMapper} compartilhado pela SDK. */
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {
    };

    private Json() {
    }

    /** O {@link ObjectMapper} usado pela SDK (útil para mapear respostas em POJOs). */
    public static ObjectMapper mapper() {
        return MAPPER;
    }

    /**
     * Monta um objeto JSON a partir de pares chave/valor, preservando a ordem
     * de inserção. Valores {@code null} são mantidos — o gateway distingue
     * campo ausente de campo nulo em algumas rotas.
     *
     * @throws IllegalArgumentException se o número de argumentos for ímpar ou
     *                                  alguma chave não for {@link String}
     */
    public static Map<String, Object> of(Object... keyValues) {
        if (keyValues == null || keyValues.length == 0) {
            return new LinkedHashMap<>();
        }
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException("Json.of espera pares chave/valor.");
        }

        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            Object key = keyValues[i];
            if (!(key instanceof String name)) {
                throw new IllegalArgumentException("Chave JSON deve ser String: " + key);
            }
            map.put(name, keyValues[i + 1]);
        }
        return map;
    }

    /** Serializa um valor em JSON. Objetos vazios viram {@code {}}, nunca {@code []}. */
    public static String encode(Object value) {
        try {
            if (value instanceof Map<?, ?> map && map.isEmpty()) {
                return "{}";
            }
            return MAPPER.writeValueAsString(value);
        } catch (Exception error) {
            throw new IllegalArgumentException("Falha ao serializar o corpo da requisição.", error);
        }
    }

    /**
     * Decodifica um texto JSON. Devolve {@code null} para corpo vazio e o
     * próprio texto quando ele não é JSON válido.
     */
    public static Object decode(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        try {
            return MAPPER.readValue(text, Object.class);
        } catch (Exception error) {
            return text;
        }
    }

    /**
     * Normaliza um corpo decodificado em objeto JSON. Respostas vazias viram
     * {@code {}}; listas e textos são embrulhados em {@code {"data": ...}}.
     */
    public static Map<String, Object> asMap(Object data) {
        if (data == null) {
            return new LinkedHashMap<>();
        }
        if (data instanceof Map<?, ?> map) {
            Map<String, Object> result = new LinkedHashMap<>();
            map.forEach((key, value) -> result.put(String.valueOf(key), value));
            return result;
        }
        Map<String, Object> wrapper = new LinkedHashMap<>();
        wrapper.put("data", data);
        return wrapper;
    }

    /** Converte um objeto qualquer (POJO, record) em {@code Map<String,Object>}. */
    public static Map<String, Object> toMap(Object value) {
        if (value == null) {
            return new LinkedHashMap<>();
        }
        if (value instanceof Map<?, ?>) {
            return asMap(value);
        }
        return MAPPER.convertValue(value, MAP_TYPE);
    }

    /** Converte uma resposta em um POJO tipado. */
    public static <T> T to(Object value, Class<T> type) {
        return MAPPER.convertValue(value, type);
    }

    /** Lê uma chave da resposta como {@link String} ({@code null} se ausente). */
    public static String string(Map<String, Object> json, String key) {
        Object value = json == null ? null : json.get(key);
        return value == null ? null : String.valueOf(value);
    }

    /** Lê uma chave da resposta como {@code boolean} ({@code false} se ausente). */
    public static boolean bool(Map<String, Object> json, String key) {
        Object value = json == null ? null : json.get(key);
        if (value instanceof Boolean flag) {
            return flag;
        }
        return "true".equalsIgnoreCase(String.valueOf(value));
    }

    /** Lê uma chave da resposta como objeto JSON aninhado ({@code null} se ausente). */
    public static Map<String, Object> object(Map<String, Object> json, String key) {
        Object value = json == null ? null : json.get(key);
        return value instanceof Map<?, ?> map ? asMap(map) : null;
    }

    /** Lê uma chave da resposta como lista ({@code List.of()} se ausente). */
    @SuppressWarnings("unchecked")
    public static List<Object> list(Map<String, Object> json, String key) {
        Object value = json == null ? null : json.get(key);
        return value instanceof List<?> list ? (List<Object>) list : Collections.emptyList();
    }
}

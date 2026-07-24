package com.apibrasil.sdk.tools;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gera {@code src/main/java/com/apibrasil/sdk/generated/Catalog.java} a partir
 * do catálogo público do gateway APIBrasil ({@code GET /api/v2/documentations}).
 *
 * <pre>{@code
 * mvn -Pcodegen exec:java
 * APIBRASIL_BASE_URL=... mvn -Pcodegen exec:java   # outra base
 * }</pre>
 */
public final class Codegen {

    private static final String OUTPUT_PATH = "src/main/java/com/apibrasil/sdk/generated/Catalog.java";

    private static final Pattern API_V2 = Pattern.compile("/api/v2/(.+)$");
    private static final Pattern CONSULTA = Pattern.compile("^consulta/([^/]+)/credits$");
    private static final Pattern NON_ALNUM = Pattern.compile("[^A-Za-z0-9]+");

    private Codegen() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String baseUrl = System.getenv("APIBRASIL_BASE_URL");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = ApiHttpClient.DEFAULT_BASE_URL;
        }

        String url = baseUrl.replaceAll("/+$", "") + "/documentations";
        System.out.println("Baixando catálogo de " + url + " ...");

        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(15)).build();
        HttpResponse<byte[]> response = client.send(
                HttpRequest.newBuilder(URI.create(url))
                        .header("Accept", "application/json")
                        .header("User-Agent", "APIBRASIL/SDK-JAVA codegen")
                        .timeout(Duration.ofSeconds(60))
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            System.err.println("Falha ao baixar o catálogo: HTTP " + response.statusCode());
            System.exit(1);
            return;
        }

        Object payload = Json.decode(new String(response.body(), StandardCharsets.UTF_8));
        List<?> documentations = extractDocumentations(payload);
        if (documentations == null) {
            System.err.println("Resposta inesperada: \"documentations\" não é uma lista.");
            System.exit(1);
            return;
        }

        Map<String, Set<String>> serviceActions = new TreeMap<>();
        Map<String, ConsultaTipo> consultaTipos = new TreeMap<>();
        Set<String> consultaServicos = new TreeSet<>();
        int endpointCount = 0;

        for (Object doc : documentations) {
            if (!(doc instanceof Map<?, ?> documentation)) {
                continue;
            }
            if (!(documentation.get("endpoints") instanceof List<?> endpoints)) {
                continue;
            }

            for (Object item : endpoints) {
                if (!(item instanceof Map<?, ?> endpoint) || !(endpoint.get("url") instanceof String rawUrl)) {
                    continue;
                }
                Matcher matcher = API_V2.matcher(rawUrl);
                if (!matcher.find()) {
                    continue;
                }
                endpointCount++;

                String fullPath = matcher.group(1).replaceAll("^/+|/+$", "");
                int slash = fullPath.indexOf('/');
                String service = slash < 0 ? fullPath : fullPath.substring(0, slash);
                String action = slash < 0 ? "" : fullPath.substring(slash + 1);
                if (service.isEmpty()) {
                    continue;
                }

                Set<String> actions = serviceActions.computeIfAbsent(service, key -> new TreeSet<>());
                if (!action.isEmpty()) {
                    actions.add(action);
                }

                Matcher consulta = CONSULTA.matcher(fullPath);
                if (consulta.matches()) {
                    String servico = consulta.group(1);
                    consultaServicos.add(servico);
                    if (endpoint.get("body") instanceof Map<?, ?> body
                            && body.get("tipo") instanceof String tipo && !tipo.isBlank()) {
                        List<String> fields = new ArrayList<>();
                        body.keySet().forEach(key -> {
                            String name = String.valueOf(key);
                            if (!name.equals("tipo") && !name.equals("homolog")) {
                                fields.add(name);
                            }
                        });
                        fields.sort(String::compareTo);
                        consultaTipos.put(tipo, new ConsultaTipo(servico, fields));
                    }
                }
            }
        }

        String content = render(url, documentations.size(), endpointCount,
                serviceActions, consultaServicos, consultaTipos);

        Path output = outputPath();
        Files.createDirectories(output.getParent());
        Files.writeString(output, content, StandardCharsets.UTF_8);

        System.out.printf("OK: %s (%d docs, %d endpoints, %d tipos)%n",
                output, documentations.size(), endpointCount, consultaTipos.size());
    }

    /** Caminho do arquivo gerado, sempre relativo à raiz do projeto. */
    private static Path outputPath() {
        String basedir = System.getProperty("apibrasil.codegen.basedir");
        return basedir == null || basedir.isBlank()
                ? Path.of(OUTPUT_PATH)
                : Path.of(basedir).resolve(OUTPUT_PATH);
    }

    private static List<?> extractDocumentations(Object payload) {
        if (payload instanceof Map<?, ?> map && map.get("documentations") instanceof List<?> list) {
            return list;
        }
        return payload instanceof List<?> list ? list : null;
    }

    private static String render(String source, int docCount, int endpointCount,
                                 Map<String, Set<String>> serviceActions,
                                 Set<String> consultaServicos,
                                 Map<String, ConsultaTipo> consultaTipos) {
        StringBuilder out = new StringBuilder();
        out.append("package com.apibrasil.sdk.generated;\n\n")
                .append("import java.util.List;\n")
                .append("import java.util.Map;\n\n")
                .append("/**\n")
                .append(" * ARQUIVO GERADO AUTOMATICAMENTE — não edite manualmente.\n")
                .append(" *\n")
                .append(" * <p>Fonte: ").append(source).append("\n")
                .append(" * <br>Regenerar: {@code mvn -Pcodegen exec:java}\n")
                .append(" *\n")
                .append(" * <p>").append(docCount).append(" documentações, ")
                .append(endpointCount).append(" endpoints, ")
                .append(consultaTipos.size()).append(" tipos de consulta conhecidos.\n")
                .append(" */\n")
                .append("public final class Catalog {\n\n")
                .append("    private Catalog() {\n    }\n\n");

        out.append(renderConstants("WhatsAppActions",
                "Actions conhecidas da API de WhatsApp ({@code POST /whatsapp/{action}}).",
                serviceActions.getOrDefault("whatsapp", Set.of())));
        out.append(renderConstants("EvolutionPaths",
                "Caminhos conhecidos da Evolution API ({@code POST /evolution/{controller}/{action}}).",
                serviceActions.getOrDefault("evolution", Set.of())));
        out.append(renderConstants("WhatsMeowActions",
                "Actions conhecidas do WhatsMeow ({@code POST /whatsmeow/{action}}).",
                serviceActions.getOrDefault("whatsmeow", Set.of())));
        out.append(renderConstants("ConsultaServicos",
                "Serviços de consulta por crédito ({@code POST /consulta/{servico}/credits}).",
                consultaServicos));
        out.append(renderConstants("ConsultaTipos",
                "Tipos de consulta conhecidos (campo {@code tipo} do body).",
                consultaTipos.keySet()));

        out.append("    /**\n")
                .append("     * Metadados de um tipo de consulta: serviço da rota e campos do body\n")
                .append("     * de exemplo documentado.\n")
                .append("     *\n")
                .append("     * @param service serviço da rota ({@code /consulta/{service}/credits})\n")
                .append("     * @param fields  campos do body de exemplo, fora {@code tipo} e {@code homolog}\n")
                .append("     */\n")
                .append("    public record ConsultaTipoInfo(String service, List<String> fields) {\n")
                .append("    }\n\n");

        out.append("    /** Actions documentadas por serviço do gateway. */\n")
                .append("    public static final Map<String, List<String>> SERVICE_ACTIONS = Map.ofEntries(\n")
                .append(String.join(",\n", buildServiceEntries(serviceActions)))
                .append(");\n\n");

        out.append("    /** Metadados por tipo de consulta por crédito. */\n")
                .append("    public static final Map<String, ConsultaTipoInfo> CONSULTA_TIPOS_INFO = Map.ofEntries(\n")
                .append(String.join(",\n", buildTipoEntries(consultaTipos)))
                .append(");\n\n");

        out.append("    /** Actions documentadas de um serviço (vazio se desconhecido). */\n")
                .append("    public static List<String> actionsOf(String service) {\n")
                .append("        return SERVICE_ACTIONS.getOrDefault(service, List.of());\n")
                .append("    }\n\n")
                .append("    /** Metadados de um tipo de consulta ({@code null} se desconhecido). */\n")
                .append("    public static ConsultaTipoInfo consultaTipo(String tipo) {\n")
                .append("        return CONSULTA_TIPOS_INFO.get(tipo);\n")
                .append("    }\n")
                .append("}\n");

        return out.toString();
    }

    private static List<String> buildServiceEntries(Map<String, Set<String>> serviceActions) {
        List<String> entries = new ArrayList<>();
        serviceActions.forEach((service, actions) -> {
            List<String> quoted = new ArrayList<>();
            actions.forEach(action -> quoted.add(quote(action)));
            entries.add("            Map.entry(" + quote(service) + ", List.of("
                    + String.join(", ", quoted) + "))");
        });
        return entries;
    }

    private static List<String> buildTipoEntries(Map<String, ConsultaTipo> consultaTipos) {
        List<String> entries = new ArrayList<>();
        consultaTipos.forEach((tipo, info) -> {
            List<String> quoted = new ArrayList<>();
            info.fields().forEach(field -> quoted.add(quote(field)));
            entries.add("            Map.entry(" + quote(tipo) + ", new ConsultaTipoInfo("
                    + quote(info.service()) + ", List.of(" + String.join(", ", quoted) + ")))");
        });
        return entries;
    }

    /** Emite uma classe aninhada de constantes com autocomplete para os valores dados. */
    private static String renderConstants(String className, String doc, Set<String> values) {
        StringBuilder out = new StringBuilder();
        out.append("    /** ").append(doc).append(" */\n")
                .append("    public static final class ").append(className).append(" {\n\n")
                .append("        private ").append(className).append("() {\n        }\n\n");

        Map<String, String> used = new LinkedHashMap<>();
        for (String value : values) {
            String identifier = toIdentifier(value);
            if (identifier == null) {
                continue;
            }
            String candidate = identifier;
            int suffix = 2;
            while (used.containsKey(candidate)) {
                candidate = identifier + "_" + suffix;
                suffix++;
            }
            used.put(candidate, value);
            out.append("        public static final String ").append(candidate)
                    .append(" = ").append(quote(value)).append(";\n");
        }

        List<String> quoted = new ArrayList<>();
        values.forEach(value -> quoted.add(quote(value)));

        out.append("\n        /** Todos os valores conhecidos, em ordem alfabética. */\n")
                .append("        public static final List<String> ALL = List.of(\n                ")
                .append(String.join(",\n                ", quoted))
                .append(");\n    }\n\n");

        return out.toString();
    }

    /**
     * Converte um valor do catálogo em uma constante Java {@code SCREAMING_SNAKE}.
     * Devolve {@code null} quando o valor não vira um nome limpo (query strings,
     * placeholders {@code <...>} etc.) — nesses casos ele fica apenas na lista.
     */
    static String toIdentifier(String value) {
        if (value.contains("?") || value.contains("<") || value.contains("=")) {
            return null;
        }

        List<String> words = new ArrayList<>();
        for (String chunk : NON_ALNUM.split(value)) {
            if (chunk.isEmpty()) {
                continue;
            }
            // Preserva as fronteiras do camelCase usado pelo gateway (sendText).
            for (String word : chunk.split("(?<=[a-z0-9])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])")) {
                if (!word.isEmpty()) {
                    words.add(word.toUpperCase(Locale.ROOT));
                }
            }
        }
        if (words.isEmpty()) {
            return null;
        }

        String identifier = String.join("_", words);
        return Character.isDigit(identifier.charAt(0)) ? "V_" + identifier : identifier;
    }

    private static String quote(String value) {
        return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }

    private record ConsultaTipo(String service, List<String> fields) {
    }
}

package com.apibrasil.sdk;

import com.apibrasil.sdk.core.ApiBrasilConfig;
import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.HttpMethod;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.core.errors.ApiBrasilException;
import com.apibrasil.sdk.services.data.BulkService;
import com.apibrasil.sdk.services.data.CepService;
import com.apibrasil.sdk.services.data.ChipVirtualService;
import com.apibrasil.sdk.services.data.ConsultaService;
import com.apibrasil.sdk.services.data.CorreiosService;
import com.apibrasil.sdk.services.data.DadosService;
import com.apibrasil.sdk.services.data.DatabaseIpService;
import com.apibrasil.sdk.services.data.DddService;
import com.apibrasil.sdk.services.data.FipeService;
import com.apibrasil.sdk.services.data.GeolocationService;
import com.apibrasil.sdk.services.data.GeomatrixService;
import com.apibrasil.sdk.services.data.HolidaysService;
import com.apibrasil.sdk.services.data.LoteriasService;
import com.apibrasil.sdk.services.data.RecognizeService;
import com.apibrasil.sdk.services.data.TranslateService;
import com.apibrasil.sdk.services.data.UraService;
import com.apibrasil.sdk.services.data.VehiclesService;
import com.apibrasil.sdk.services.data.WeatherService;
import com.apibrasil.sdk.services.messaging.EvolutionService;
import com.apibrasil.sdk.services.messaging.SmsService;
import com.apibrasil.sdk.services.messaging.WhatsAppService;
import com.apibrasil.sdk.services.messaging.WhatsMeowService;
import com.apibrasil.sdk.services.platform.AccountService;
import com.apibrasil.sdk.services.platform.AuthService;
import com.apibrasil.sdk.services.platform.BearerRateLimitService;
import com.apibrasil.sdk.services.platform.CatalogService;
import com.apibrasil.sdk.services.platform.DevicesService;
import com.apibrasil.sdk.services.platform.IpWhitelistService;
import com.apibrasil.sdk.services.platform.PaymentsService;
import com.apibrasil.sdk.services.platform.ReportsService;

import java.util.Map;

/**
 * Cliente oficial da plataforma APIBrasil.
 *
 * <pre>{@code
 * ApiBrasil api = ApiBrasil.builder()
 *     .bearerToken(System.getenv("APIBRASIL_BEARER_TOKEN"))
 *     .deviceToken(System.getenv("APIBRASIL_DEVICE_TOKEN"))
 *     .build();
 *
 * api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
 *
 * Map<String, Object> empresa = api.consulta.cnpj(Json.of("cnpj", "00000000000000"));
 * }</pre>
 *
 * <p>Credenciais não informadas são lidas das variáveis de ambiente
 * {@code APIBRASIL_BEARER_TOKEN}, {@code APIBRASIL_DEVICE_TOKEN},
 * {@code APIBRASIL_SECRET_KEY} e {@code APIBRASIL_BASE_URL}.
 *
 * <p>A instância é segura para uso concorrente: todos os serviços compartilham
 * o mesmo {@link ApiHttpClient}, e trocar o token vale para todos.
 */
public final class ApiBrasil implements AutoCloseable {

    /** Cliente HTTP interno (headers, base URL, retry, hooks, erros). */
    public final ApiHttpClient http;

    /** Login, 2FA, cadastro, senha e perfil. */
    public final AuthService auth;

    /** Gestão de devices (criar, listar, atualizar, remover). */
    public final DevicesService devices;

    /** WhatsApp device-based ({@code /whatsapp/{action}}). */
    public final WhatsAppService whatsapp;

    /** Evolution API ({@code /evolution/{controller}/{action}}). */
    public final EvolutionService evolution;

    /** WhatsMeow ({@code /whatsmeow/{action}}). */
    public final WhatsMeowService whatsmeow;

    /** SMS ({@code /sms/{action}} e {@code /sms/send/credits}). */
    public final SmsService sms;

    /** Dados cadastrais device-based ({@code /dados/cpf}, {@code /dados/cnpj}...). */
    public final DadosService dados;

    /** Veículos por placa ({@code /vehicles/dados}, {@code /vehicles/fipe}). */
    public final VehiclesService vehicles;

    /** Tabela FIPE ({@code /fipe/{action}}). */
    public final FipeService fipe;

    /** Correios ({@code /correios/{action}}). */
    public final CorreiosService correios;

    /** CEP + geolocalização ({@code /cep/{action}}). */
    public final CepService cep;

    /** Geolocalização ({@code /geolocation/{action}}). */
    public final GeolocationService geolocation;

    /** Matriz de distâncias ({@code /geomatrix/{action}}). */
    public final GeomatrixService geomatrix;

    /** OCR / Google Vision ({@code /recognize/{action}}). */
    public final RecognizeService recognize;

    /** DDD ({@code /ddd/{action}}). */
    public final DddService ddd;

    /** Feriados ({@code /holidays/{action}}). */
    public final HolidaysService holidays;

    /** Tradução ({@code /translate/{action}}). */
    public final TranslateService translate;

    /** Clima ({@code /weather/{action}}). */
    public final WeatherService weather;

    /** Loterias ({@code /loterias/{sorteio}/{concurso}}). */
    public final LoteriasService loterias;

    /** GeoIP ({@code /database/ip}). */
    public final DatabaseIpService databaseIp;

    /** Consultas por crédito ({@code /consulta/{servico}/credits}). */
    public final ConsultaService consulta;

    /** URA reversa / ligações ({@code /ura/call/*}). */
    public final UraService ura;

    /** Chip virtual ({@code /chip/virtual/*}). */
    public final ChipVirtualService chipVirtual;

    /** Execução em lote ({@code /bulk/*}). */
    public final BulkService bulk;

    /** Catálogo de APIs, planos, docs e servidores. */
    public final CatalogService catalog;

    /** Saldo, faturas, notificações, tickets. */
    public final AccountService account;

    /** Recargas e pagamentos (PIX, boleto, cartão). */
    public final PaymentsService payments;

    /** IP whitelist da conta. */
    public final IpWhitelistService ipWhitelist;

    /** Rate limit por Bearer Token. */
    public final BearerRateLimitService bearerRateLimit;

    /** Relatórios e dashboard de consumo. */
    public final ReportsService reports;

    /** Cria o cliente lendo as credenciais do ambiente. */
    public ApiBrasil() {
        this(ApiBrasilConfig.empty());
    }

    /** Cria o cliente com Bearer Token e DeviceToken explícitos. */
    public ApiBrasil(String bearerToken, String deviceToken) {
        this(ApiBrasilConfig.builder()
                .bearerToken(bearerToken)
                .deviceToken(deviceToken)
                .build());
    }

    /** Cria o cliente a partir de uma configuração. */
    public ApiBrasil(ApiBrasilConfig config) {
        this(new ApiHttpClient(config));
    }

    /**
     * Cria o cliente sobre um {@link ApiHttpClient} já configurado.
     *
     * <p>Todos os serviços compartilham esse mesmo cliente — logo, um
     * {@link #setBearerToken(String)} vale para todos, e {@link #close()}
     * libera um único transporte.
     */
    public ApiBrasil(ApiHttpClient client) {
        this.http = client;

        this.auth = new AuthService(client);
        this.devices = new DevicesService(client);

        this.whatsapp = new WhatsAppService(client);
        this.evolution = new EvolutionService(client);
        this.whatsmeow = new WhatsMeowService(client);
        this.sms = new SmsService(client);

        this.dados = new DadosService(client);
        this.vehicles = new VehiclesService(client);
        this.fipe = new FipeService(client);
        this.correios = new CorreiosService(client);
        this.cep = new CepService(client);
        this.geolocation = new GeolocationService(client);
        this.geomatrix = new GeomatrixService(client);
        this.recognize = new RecognizeService(client);
        this.ddd = new DddService(client);
        this.holidays = new HolidaysService(client);
        this.translate = new TranslateService(client);
        this.weather = new WeatherService(client);
        this.loterias = new LoteriasService(client);
        this.databaseIp = new DatabaseIpService(client);

        this.consulta = new ConsultaService(client);
        this.ura = new UraService(client);
        this.chipVirtual = new ChipVirtualService(client);
        this.bulk = new BulkService(client);

        this.catalog = new CatalogService(client);
        this.account = new AccountService(client);
        this.payments = new PaymentsService(client);
        this.ipWhitelist = new IpWhitelistService(client);
        this.bearerRateLimit = new BearerRateLimitService(client);
        this.reports = new ReportsService(client);
    }

    /** Builder do cliente — atalho para {@link ApiBrasilConfig#builder()}. */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Faz login e devolve um cliente já autenticado.
     *
     * @throws ApiBrasilException se a conta exigir 2FA — nesse caso crie o
     *                            cliente manualmente e use
     *                            {@code auth.login()} + {@code auth.verify2fa()}
     */
    public static LoginResult login(Map<String, Object> credentials) {
        return login(credentials, ApiBrasilConfig.empty());
    }

    /** Faz login com uma configuração customizada e devolve um cliente autenticado. */
    public static LoginResult login(Map<String, Object> credentials, ApiBrasilConfig config) {
        ApiBrasil client = new ApiBrasil(config);
        Map<String, Object> session = client.auth.login(credentials);

        if (Json.bool(session, "requires_2fa")) {
            throw new ApiBrasilException(
                    "Esta conta exige autenticação em dois fatores. Use auth.login() + auth.verify2fa().",
                    null, null, session, null);
        }

        return new LoginResult(client, session);
    }

    /** Define/atualiza o Bearer Token do cliente. */
    public ApiBrasil setBearerToken(String token) {
        http.setBearerToken(token);
        return this;
    }

    /** Define/atualiza o DeviceToken do cliente. */
    public ApiBrasil setDeviceToken(String token) {
        http.setDeviceToken(token);
        return this;
    }

    /**
     * Devolve um novo cliente com as mesmas credenciais, mas apontando para
     * outro device — útil para gerenciar vários números/instâncias.
     *
     * <p>O transporte é compartilhado com este cliente; feche apenas o
     * original.
     */
    public ApiBrasil withDevice(String deviceToken) {
        return new ApiBrasil(http.config().toBuilder()
                .deviceToken(deviceToken)
                .build());
    }

    /**
     * Porta de saída genérica: chama qualquer endpoint do gateway com os
     * headers de autenticação já configurados. Use para rotas que ainda não
     * têm método dedicado na SDK.
     *
     * <pre>{@code
     * api.request("POST", "/consulta/cpf/credits", Json.of("cpf", "..."));
     * }</pre>
     */
    public Object request(String method, String path, Object body) {
        return request(method, path, body, RequestOptions.NONE);
    }

    /** Porta de saída genérica, com opções por requisição. */
    public Object request(String method, String path, Object body, RequestOptions options) {
        return http.request(HttpMethod.of(method), path, body, options);
    }

    /** Porta de saída genérica devolvendo a resposta como objeto JSON. */
    public Map<String, Object> requestJson(String method, String path, Object body) {
        return http.requestJson(HttpMethod.of(method), path, body, RequestOptions.NONE);
    }

    /** Fecha o transporte e libera as conexões. */
    @Override
    public void close() {
        http.close();
    }

    /**
     * Resultado de {@link ApiBrasil#login(Map)}.
     *
     * @param client  cliente já autenticado
     * @param session corpo da resposta de login (token, plano, usuário...)
     */
    public record LoginResult(ApiBrasil client, Map<String, Object> session) {
    }

    /** Builder de {@link ApiBrasil}. */
    public static final class Builder {
        private final ApiBrasilConfig.Builder config = ApiBrasilConfig.builder();

        public Builder bearerToken(String bearerToken) {
            config.bearerToken(bearerToken);
            return this;
        }

        public Builder deviceToken(String deviceToken) {
            config.deviceToken(deviceToken);
            return this;
        }

        public Builder secretKey(String secretKey) {
            config.secretKey(secretKey);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            config.baseUrl(baseUrl);
            return this;
        }

        public Builder timeout(java.time.Duration timeout) {
            config.timeout(timeout);
            return this;
        }

        public Builder timeoutMillis(long timeoutMillis) {
            config.timeoutMillis(timeoutMillis);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            config.headers(headers);
            return this;
        }

        public Builder header(String name, String value) {
            config.header(name, value);
            return this;
        }

        public Builder transport(com.apibrasil.sdk.core.transport.Transport transport) {
            config.transport(transport);
            return this;
        }

        public Builder retry(com.apibrasil.sdk.core.RetryConfig retry) {
            config.retry(retry);
            return this;
        }

        public Builder hooks(com.apibrasil.sdk.core.Hooks hooks) {
            config.hooks(hooks);
            return this;
        }

        /** Configuração montada até aqui. */
        public ApiBrasilConfig config() {
            return config.build();
        }

        public ApiBrasil build() {
            return new ApiBrasil(config.build());
        }
    }
}

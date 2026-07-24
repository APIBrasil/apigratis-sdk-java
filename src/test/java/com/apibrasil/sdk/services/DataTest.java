package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.CreditResponse;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Serviços de dados")
class DataTest extends ApiTestCase {

    @Test
    @DisplayName("CEP")
    void cep() {
        api.cep.cep(Json.of("cep", "01001000"));
        assertCall("POST", "/cep/cep");
        assertEquals("01001000", transport.lastBody().get("cep"));

        api.cep.bairros(Json.of("uf", "SP"));
        assertCall("POST", "/cep/bairros");

        api.cep.cidades(Json.of("uf", "SP"));
        assertCall("POST", "/cep/cidades");

        api.cep.cidadesPorDdd(Json.of("ddd", 11));
        assertCall("POST", "/cep/cidadesPorDDD");

        api.cep.estados();
        assertCall("POST", "/cep/estados");

        api.cep.calcularDistancia(Json.of("origem", "01001000", "destino", "20040002"));
        assertCall("POST", "/cep/distancia/calcular");
    }

    @Test
    @DisplayName("Dados cadastrais")
    void dados() {
        api.dados.cpf(Json.of("cpf", "00000000000"));
        assertCall("POST", "/dados/cpf");

        api.dados.cnpj(Json.of("cnpj", "00000000000000"));
        assertCall("POST", "/dados/cnpj");

        api.dados.listaSocios(Json.of("cnpj", "00000000000000"));
        assertCall("POST", "/dados/lista-socios");

        api.dados.listaCnaes();
        assertCall("POST", "/dados/lista-cnaes");

        api.dados.capitalSocial(Json.of("cnpj", "00000000000000"));
        assertCall("POST", "/dados/capital-social");

        api.dados.cpfCredits();
        assertCall("GET", "/dados/cpf/credits");

        api.dados.cnpjCredits();
        assertCall("GET", "/dados/cnpj/credits");
    }

    @Test
    @DisplayName("Veículos e FIPE")
    void vehiclesAndFipe() {
        api.vehicles.dados(Json.of("placa", "ABC1234"));
        assertCall("POST", "/vehicles/dados");

        api.vehicles.fipe(Json.of("placa", "ABC1234"));
        assertCall("POST", "/vehicles/fipe");

        api.vehicles.baseDados(Json.of("placa", "ABC1234"));
        assertCall("POST", "/vehicles/base/000/dados");

        api.fipe.consultarMarcas(Json.of("codigoTabelaReferencia", 300));
        assertCall("POST", "/fipe/ConsultarMarcas");

        api.fipe.consultarTabelaDeReferencia();
        assertCall("POST", "/fipe/ConsultarTabelaDeReferencia");

        api.fipe.request("ConsultarValorComTodosParametros", Json.of("codigoMarca", 1));
        assertCall("POST", "/fipe/ConsultarValorComTodosParametros");
    }

    @Test
    @DisplayName("Correios e GeoIP")
    void correiosAndGeoip() {
        api.correios.rastreio(Json.of("code", "AA123456789BR"));
        assertCall("POST", "/correios/rastreio");

        api.databaseIp.ip(Json.of("ip", "8.8.8.8"));
        assertCall("POST", "/database/ip");
    }

    @Test
    @DisplayName("Consultas por crédito")
    void consulta() {
        api.consulta.cpf(Json.of("cpf", "00000000000"));
        assertCall("POST", "/consulta/cpf/credits");

        api.consulta.cnpj(Json.of("cnpj", "00000000000000"));
        assertCall("POST", "/consulta/cnpj/credits");

        api.consulta.veiculos(Json.of("placa", "ABC1234"));
        assertCall("POST", "/consulta/veiculos/credits");

        api.consulta.ddd(Json.of("ddd", 11));
        assertCall("POST", "/consulta/ddd-anatel/credits");

        api.consulta.weather(Json.of("city", "São Paulo"));
        assertCall("POST", "/consulta/weather-api/credits");

        api.consulta.generic("cpf", Json.of("cpf", "00000000000", "tipo", "serasa-score-pf"));
        assertCall("POST", "/consulta/cpf/credits");
        assertEquals("serasa-score-pf", transport.lastBody().get("tipo"));

        api.consulta.credits("cpf");
        assertCall("GET", "/consulta/cpf/credits");
    }

    @Test
    @DisplayName("Consulta por crédito não exige DeviceToken, mas envia se houver")
    void consultaEnvelope() {
        transport.respondWith(FakeTransport.ok(Map.of(
                "error", false,
                "balance", 12.5,
                "valor_consulta", 0.35,
                "homolog", true,
                "data", Map.of("nome", "FULANO"))));

        CreditResponse response = CreditResponse.of(api.consulta.cpf(Json.of("cpf", "00000000000")));

        assertFalse(response.isError());
        assertEquals(12.5, response.balance());
        assertEquals(0.35, response.valorConsulta());
        assertTrue(response.homolog());
        assertEquals("FULANO", response.dataObject().get("nome"));
    }

    @Test
    @DisplayName("Serviços device-proxy simples")
    void deviceProxies() {
        api.geolocation.geocode(Json.of("address", "Av Paulista"));
        assertCall("POST", "/geolocation/geocode");

        api.geomatrix.distance(Json.of("origins", "A", "destinations", "B"));
        assertCall("POST", "/geomatrix/distance");

        api.recognize.base64(Json.of("image", "data:image/png;base64,..."));
        assertCall("POST", "/recognize/base64");

        api.holidays.feriados(Json.of("ano", 2026));
        assertCall("POST", "/holidays/feriados");

        api.translate.identify(Json.of("text", "olá"));
        assertCall("POST", "/translate/identify");

        api.weather.city(Json.of("city", "São Paulo"));
        assertCall("POST", "/weather/city");

        api.ddd.request("consulta", Json.of("ddd", 11));
        assertCall("POST", "/ddd/consulta");
    }

    @Test
    @DisplayName("Loterias, URA, chip virtual e bulk")
    void others() {
        api.loterias.latest("megasena");
        assertCall("POST", "/loterias/megasena/latest");

        api.loterias.resultado("megasena", 2700);
        assertCall("POST", "/loterias/megasena/2700");

        api.ura.dialler(Json.of("number", "5511999999999"));
        assertCall("POST", "/ura/call/dialler");

        api.ura.status("call-1");
        assertCall("POST", "/ura/call/status?callId=call-1");

        api.chipVirtual.buy(Json.of("operator", "vivo"));
        assertCall("POST", "/chip/virtual/buy");

        api.chipVirtual.operators();
        assertCall("GET", "/chip/virtual/operators");

        api.bulk.create(Json.of("items", java.util.List.of()));
        assertCall("POST", "/bulk");

        api.bulk.status("job-1");
        assertCall("GET", "/bulk/job-1");

        api.bulk.list();
        assertCall("GET", "/bulk");
    }
}

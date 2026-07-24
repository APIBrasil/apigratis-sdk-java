package com.apibrasil.sdk.generated;

import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.helpers.ApiTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Catálogo gerado")
class CatalogTest extends ApiTestCase {

    @Test
    @DisplayName("traz as actions conhecidas de cada serviço")
    void knownActions() {
        assertFalse(Catalog.WhatsAppActions.ALL.isEmpty());
        assertFalse(Catalog.EvolutionPaths.ALL.isEmpty());
        assertFalse(Catalog.WhatsMeowActions.ALL.isEmpty());
        assertFalse(Catalog.ConsultaServicos.ALL.isEmpty());
        assertFalse(Catalog.ConsultaTipos.ALL.isEmpty());

        assertEquals("sendText", Catalog.WhatsAppActions.SEND_TEXT);
        assertTrue(Catalog.WhatsAppActions.ALL.contains("qrcode"));
        assertTrue(Catalog.EvolutionPaths.ALL.contains("message/sendText"));
        assertTrue(Catalog.ConsultaServicos.ALL.contains("cpf"));
    }

    @Test
    @DisplayName("SERVICE_ACTIONS cobre os serviços expostos pela SDK")
    void serviceActions() {
        for (String service : List.of("whatsapp", "cep", "dados", "vehicles", "fipe", "consulta")) {
            assertFalse(Catalog.actionsOf(service).isEmpty(), "sem actions para " + service);
        }
        assertTrue(Catalog.actionsOf("servico-que-nao-existe").isEmpty());
    }

    @Test
    @DisplayName("consultaTipo devolve serviço e campos do tipo")
    void consultaTipos() {
        String tipo = Catalog.ConsultaTipos.ALL.get(0);
        Catalog.ConsultaTipoInfo info = Catalog.consultaTipo(tipo);

        assertNotNull(info, "sem metadados para " + tipo);
        assertFalse(info.service().isBlank());
        assertNotNull(info.fields());
    }

    @Test
    @DisplayName("toda action do catálogo é chamável pela SDK")
    void everyActionIsCallable() {
        for (String action : Catalog.WhatsAppActions.ALL) {
            if (action.contains("?") || action.contains("<")) {
                continue;
            }
            api.whatsapp.request(action, Json.of("teste", true));
            assertEquals(BASE_URL + "/whatsapp/" + action, transport.lastUrl());
        }
    }

    @Test
    @DisplayName("todo tipo de consulta é chamável pela SDK")
    void everyConsultaTipoIsCallable() {
        for (String tipo : Catalog.ConsultaTipos.ALL) {
            Catalog.ConsultaTipoInfo info = Catalog.consultaTipo(tipo);
            api.consulta.generic(info.service(), Json.of("tipo", tipo));
            assertEquals(BASE_URL + "/consulta/" + info.service() + "/credits", transport.lastUrl());
            assertEquals(tipo, transport.lastBody().get("tipo"));
        }
    }

    @Test
    @DisplayName("o cliente principal continua exposto no pacote raiz")
    void clientIsExposed() {
        assertNotNull(ApiBrasil.builder());
    }
}

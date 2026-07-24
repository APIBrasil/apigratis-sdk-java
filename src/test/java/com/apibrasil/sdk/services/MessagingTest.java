package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.DeviceResponse;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Serviços de mensageria")
class MessagingTest extends ApiTestCase {

    // ---------------------------------------------------------------- WhatsApp

    @Test
    @DisplayName("WhatsApp: sessão")
    void whatsappSession() {
        api.whatsapp.start();
        assertCall("POST", "/whatsapp/start");

        api.whatsapp.qrcode();
        assertCall("POST", "/whatsapp/qrcode");

        api.whatsapp.logout();
        assertCall("POST", "/whatsapp/logout");

        api.whatsapp.close();
        assertCall("POST", "/whatsapp/close");

        api.whatsapp.deleteSession();
        assertCall("POST", "/whatsapp/deleteSession");

        api.whatsapp.restartSession();
        assertCall("POST", "/whatsapp/restartSession");

        api.whatsapp.whatsappVersions();
        assertCall("POST", "/whatsapp/whatsapp-versions");
    }

    @Test
    @DisplayName("WhatsApp: envios")
    void whatsappSend() {
        api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
        assertCall("POST", "/whatsapp/sendText");
        assertEquals("Olá!", transport.lastBody().get("text"));

        api.whatsapp.sendFile(Json.of("number", "5511999999999", "path", "https://x.test/nota.pdf"));
        assertCall("POST", "/whatsapp/sendFile");

        api.whatsapp.sendAudio(Json.of("number", "5511999999999", "path", "https://x.test/a.mp3"));
        assertCall("POST", "/whatsapp/sendAudio");

        api.whatsapp.sendLocation(Json.of("number", "5511999999999", "lat", -23.5, "lng", -46.6));
        assertCall("POST", "/whatsapp/sendLocation");

        api.whatsapp.sendPixKey(Json.of("number", "5511999999999", "key", "a@b.com"));
        assertCall("POST", "/whatsapp/sendPixKey");
    }

    @Test
    @DisplayName("WhatsApp: grupos e comunidades")
    void whatsappGroups() {
        api.whatsapp.createGroup(Json.of("name", "meu grupo"));
        assertCall("POST", "/whatsapp/createGroup");

        api.whatsapp.getAllGroups();
        assertCall("POST", "/whatsapp/getAllGroups");

        api.whatsapp.addParticipant(Json.of("groupId", "1", "phone", "5511999999999"));
        assertCall("POST", "/whatsapp/addParticipant");

        api.whatsapp.createCommunity(Json.of("name", "comunidade"));
        assertCall("POST", "/whatsapp/createCommunity");
    }

    @Test
    @DisplayName("WhatsApp: fila e action genérica")
    void whatsappQueueAndGeneric() {
        api.whatsapp.queue("sendText", Json.of("number", "5511999999999", "text", "assíncrono"));
        assertCall("POST", "/whatsapp/sendText/queue");

        api.whatsapp.request("qualquerActionNova", Json.of("x", 1));
        assertCall("POST", "/whatsapp/qualquerActionNova");
    }

    @Test
    @DisplayName("WhatsApp: envelope device-based")
    void whatsappEnvelope() {
        transport.respondWith(FakeTransport.ok(Map.of(
                "error", false,
                "message", "enviado",
                "response", Map.of("id", "abc"),
                "api_limit", 1000)));

        DeviceResponse response = DeviceResponse.of(
                api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "oi")));

        assertFalse(response.isError());
        assertEquals("enviado", response.message());
        assertEquals("abc", response.responseObject().get("id"));
        assertEquals(1000, response.apiLimit());
    }

    // --------------------------------------------------------------- Evolution

    @Test
    @DisplayName("Evolution: instância, mensagens e grupos")
    void evolution() {
        api.evolution.createInstance(Json.of("instanceName", "bot"));
        assertCall("POST", "/evolution/instance/create");

        api.evolution.sendText(Json.of("number", "5511999999999", "text", "oi"));
        assertCall("POST", "/evolution/message/sendText");

        api.evolution.sendTextQueue(Json.of("number", "5511999999999", "text", "oi"));
        assertCall("POST", "/evolution/message/sendText/queue");

        api.evolution.findChats(Json.of("where", Map.of()));
        assertCall("POST", "/evolution/chat/findChats");

        api.evolution.createGroup(Json.of("subject", "grupo"));
        assertCall("POST", "/evolution/group/create");

        api.evolution.request("settings", "find", null);
        assertCall("POST", "/evolution/settings/find");
    }

    // ---------------------------------------------------------------- WhatsMeow

    @Test
    @DisplayName("WhatsMeow: instância, envio e usuário")
    void whatsmeow() {
        api.whatsmeow.createInstance(Json.of("name", "bot"));
        assertCall("POST", "/whatsmeow/instance/create");

        api.whatsmeow.sendText(Json.of("phone", "5511999999999", "message", "oi"));
        assertCall("POST", "/whatsmeow/send/text");

        api.whatsmeow.checkUser(Json.of("phone", "5511999999999"));
        assertCall("POST", "/whatsmeow/user/check");

        api.whatsmeow.instanceInfo("dev-123");
        assertCall("POST", "/whatsmeow/instance/info/dev-123");

        api.whatsmeow.listGroups();
        assertCall("POST", "/whatsmeow/group/list");
    }

    // --------------------------------------------------------------------- SMS

    @Test
    @DisplayName("SMS: device, créditos e fila")
    void sms() {
        api.sms.send(Json.of("number", "5511999999999", "message", "oi"));
        assertCall("POST", "/sms/send");
        assertTrue(transport.lastHeaders().containsKey("DeviceToken"));

        api.sms.sendWithCredits(Json.of("number", "5511999999999", "message", "oi"));
        assertCall("POST", "/sms/send/credits");

        api.sms.sendQueue(Json.of("number", "5511999999999", "message", "oi"));
        assertCall("POST", "/sms/send/queue");
    }
}

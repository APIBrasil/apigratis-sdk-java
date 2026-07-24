package com.apibrasil.sdk.services.messaging;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/**
 * WhatsMeow ({@code POST /whatsmeow/{action}}).
 * Usa {@code DeviceToken} para autenticação.
 */
public class WhatsMeowService extends DeviceProxyService {

    public WhatsMeowService(ApiHttpClient http) {
        super(http, "whatsmeow");
    }

    // -------------------------------------------------------------- instância

    /** Cria a instância: {@code POST /whatsmeow/instance/create}. */
    public Map<String, Object> createInstance(Map<String, Object> body) {
        return request("instance/create", body);
    }

    /** Conecta a instância. */
    public Map<String, Object> connectInstance(Map<String, Object> body) {
        return request("instance/connect", body);
    }

    /** Desconecta a instância. */
    public Map<String, Object> disconnectInstance(Map<String, Object> body) {
        return request("instance/disconnect", body);
    }

    /** Desloga a instância. */
    public Map<String, Object> logoutInstance(Map<String, Object> body) {
        return request("instance/logout", body);
    }

    /** Apaga a instância de um device. */
    public Map<String, Object> deleteInstance(String deviceKey) {
        return request("instance/delete/" + deviceKey, null);
    }

    /** Informações da instância de um device. */
    public Map<String, Object> instanceInfo(String deviceKey) {
        return request("instance/info/" + deviceKey, null);
    }

    /** QR Code de pareamento. */
    public Map<String, Object> qrcode(Map<String, Object> body) {
        return request("instance/qr", body);
    }

    // -------------------------------------------------------------------- chat

    /** Arquiva chat. */
    public Map<String, Object> archiveChat(Map<String, Object> body) {
        return request("chat/archive", body);
    }

    /** Silencia chat. */
    public Map<String, Object> muteChat(Map<String, Object> body) {
        return request("chat/mute", body);
    }

    /** Fixa chat. */
    public Map<String, Object> pinChat(Map<String, Object> body) {
        return request("chat/pin", body);
    }

    /** Desafixa chat. */
    public Map<String, Object> unpinChat(Map<String, Object> body) {
        return request("chat/unpin", body);
    }

    // ------------------------------------------------------------------ grupos

    /** Cria grupo. */
    public Map<String, Object> createGroup(Map<String, Object> body) {
        return request("group/create", body);
    }

    /** Informações do grupo. */
    public Map<String, Object> groupInfo(Map<String, Object> body) {
        return request("group/info", body);
    }

    /** Link de convite do grupo. */
    public Map<String, Object> groupInviteLink(Map<String, Object> body) {
        return request("group/invitelink", body);
    }

    /** Entra em um grupo. */
    public Map<String, Object> joinGroup(Map<String, Object> body) {
        return request("group/join", body);
    }

    /** Lista grupos. */
    public Map<String, Object> listGroups() {
        return request("group/list");
    }

    /** Lista todos os meus grupos. */
    public Map<String, Object> myAllGroups() {
        return request("group/myall");
    }

    /** Renomeia o grupo. */
    public Map<String, Object> groupName(Map<String, Object> body) {
        return request("group/name", body);
    }

    /** Gerencia participantes do grupo. */
    public Map<String, Object> groupParticipant(Map<String, Object> body) {
        return request("group/participant", body);
    }

    /** Define a foto do grupo. */
    public Map<String, Object> groupPhoto(Map<String, Object> body) {
        return request("group/photo", body);
    }

    // ------------------------------------------------------------------- envio

    /** Envia texto. */
    public Map<String, Object> sendText(Map<String, Object> body) {
        return request("send/text", body);
    }

    /** Envia mídia. */
    public Map<String, Object> sendMedia(Map<String, Object> body) {
        return request("send/media", body);
    }

    /** Envia figurinha. */
    public Map<String, Object> sendSticker(Map<String, Object> body) {
        return request("send/sticker", body);
    }

    /** Envia contato. */
    public Map<String, Object> sendContact(Map<String, Object> body) {
        return request("send/contact", body);
    }

    /** Envia localização. */
    public Map<String, Object> sendLocation(Map<String, Object> body) {
        return request("send/location", body);
    }

    /** Envia link. */
    public Map<String, Object> sendLink(Map<String, Object> body) {
        return request("send/link", body);
    }

    /** Envia enquete. */
    public Map<String, Object> sendPoll(Map<String, Object> body) {
        return request("send/poll", body);
    }

    // ------------------------------------------------------------------ perfil

    /** Foto de perfil de um contato. */
    public Map<String, Object> avatar(Map<String, Object> body) {
        return request("user/avatar", body);
    }

    /** Bloqueia usuário. */
    public Map<String, Object> blockUser(Map<String, Object> body) {
        return request("user/block", body);
    }

    /** Desbloqueia usuário. */
    public Map<String, Object> unblockUser(Map<String, Object> body) {
        return request("user/unblock", body);
    }

    /** Lista de bloqueados. */
    public Map<String, Object> blockList() {
        return request("user/blocklist");
    }

    /** Verifica se números existem no WhatsApp. */
    public Map<String, Object> checkUser(Map<String, Object> body) {
        return request("user/check", body);
    }

    /** Lista contatos. */
    public Map<String, Object> contacts() {
        return request("user/contacts");
    }

    /** Informações de um usuário. */
    public Map<String, Object> userInfo(Map<String, Object> body) {
        return request("user/info", body);
    }

    /** Configurações de privacidade. */
    public Map<String, Object> privacy() {
        return request("user/privacy");
    }

    /** Perfil da conta conectada. */
    public Map<String, Object> profile() {
        return request("user/profile");
    }
}

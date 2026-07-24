package com.apibrasil.sdk.services.messaging;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/**
 * Evolution API ({@code POST /evolution/{controller}/{action}}).
 * Usa {@code DeviceToken} para autenticação.
 *
 * <pre>{@code
 * api.evolution.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
 * api.evolution.request("message", "sendMedia", body);
 * }</pre>
 */
public class EvolutionService extends BaseService {

    private static final String SERVICE = "evolution";

    public EvolutionService(ApiHttpClient http) {
        super(http);
    }

    /** Executa qualquer caminho da Evolution: {@code POST /evolution/{controller}/{action}}. */
    public Map<String, Object> request(String controller, String action, Object body) {
        return request(controller, action, body, RequestOptions.NONE);
    }

    /** Executa qualquer caminho da Evolution: {@code POST /evolution/{controller}/{action}}. */
    public Map<String, Object> request(String controller, String action, Object body, RequestOptions options) {
        return post(SERVICE + "/" + controller + "/" + action, body, options);
    }

    private Map<String, Object> call(String path, Object body) {
        return post(SERVICE + "/" + path, body);
    }

    private Map<String, Object> call(String path, Object body, RequestOptions options) {
        return post(SERVICE + "/" + path, body, options);
    }

    // -------------------------------------------------------------- instância

    /** Cria a instância: {@code POST /evolution/instance/create}. */
    public Map<String, Object> createInstance(Map<String, Object> body) {
        return call("instance/create", body);
    }

    /** Conecta a instância. */
    public Map<String, Object> connectInstance(Map<String, Object> body) {
        return call("instance/connect", body);
    }

    /** Estado da conexão. */
    public Map<String, Object> connectionState(Map<String, Object> body) {
        return call("instance/connectionState", body);
    }

    /** Reinicia a instância. */
    public Map<String, Object> restartInstance(Map<String, Object> body) {
        return call("instance/restart", body);
    }

    /** Desloga a instância. */
    public Map<String, Object> logoutInstance(Map<String, Object> body) {
        return call("instance/logout", body);
    }

    /** Apaga a instância. */
    public Map<String, Object> deleteInstance(Map<String, Object> body) {
        return call("instance/delete", body);
    }

    // --------------------------------------------------------------- mensagens

    /** Envia texto. */
    public Map<String, Object> sendText(Map<String, Object> body) {
        return call("message/sendText", body);
    }

    public Map<String, Object> sendText(Map<String, Object> body, RequestOptions options) {
        return call("message/sendText", body, options);
    }

    /** Envia texto pela fila (assíncrono). */
    public Map<String, Object> sendTextQueue(Map<String, Object> body) {
        return call("message/sendText/queue", body);
    }

    /** Envia mídia. */
    public Map<String, Object> sendMedia(Map<String, Object> body) {
        return call("message/sendMedia", body);
    }

    /** Envia figurinha. */
    public Map<String, Object> sendSticker(Map<String, Object> body) {
        return call("message/sendSticker", body);
    }

    /** Envia contato. */
    public Map<String, Object> sendContact(Map<String, Object> body) {
        return call("message/sendContact", body);
    }

    /** Envia localização. */
    public Map<String, Object> sendLocation(Map<String, Object> body) {
        return call("message/sendLocation", body);
    }

    /** Envia enquete. */
    public Map<String, Object> sendPoll(Map<String, Object> body) {
        return call("message/sendPoll", body);
    }

    /** Envia reação. */
    public Map<String, Object> sendReaction(Map<String, Object> body) {
        return call("message/sendReaction", body);
    }

    /** Publica no status. */
    public Map<String, Object> sendStatus(Map<String, Object> body) {
        return call("message/sendStatus", body);
    }

    /** Envia botões. */
    public Map<String, Object> sendButtons(Map<String, Object> body) {
        return call("message/sendButtons", body);
    }

    /** Envia áudio do WhatsApp. */
    public Map<String, Object> sendWhatsAppAudio(Map<String, Object> body) {
        return call("message/sendWhatsAppAudio", body);
    }

    /** Apaga mensagem para todos. */
    public Map<String, Object> deleteMessageForEveryone(Map<String, Object> body) {
        return call("chat/deleteMessageForEveryone", body);
    }

    // -------------------------------------------------------------------- chat

    /** Lista chats. */
    public Map<String, Object> findChats(Map<String, Object> body) {
        return call("chat/findChats", body);
    }

    /** Lista contatos. */
    public Map<String, Object> findContacts(Map<String, Object> body) {
        return call("chat/findContacts", body);
    }

    /** Lista mensagens. */
    public Map<String, Object> findMessages(Map<String, Object> body) {
        return call("chat/findMessages", body);
    }

    /** Configurações de privacidade. */
    public Map<String, Object> fetchPrivacySettings(Map<String, Object> body) {
        return call("chat/fetchPrivacySettings", body);
    }

    /** Perfil. */
    public Map<String, Object> fetchProfile(Map<String, Object> body) {
        return call("chat/fetchProfile", body);
    }

    /** URL da foto de perfil. */
    public Map<String, Object> fetchProfilePictureUrl(Map<String, Object> body) {
        return call("chat/fetchProfilePictureUrl", body);
    }

    /** Atualiza configurações de privacidade. */
    public Map<String, Object> updatePrivacySettings(Map<String, Object> body) {
        return call("chat/updatePrivacySettings", body);
    }

    /** Atualiza o nome do perfil. */
    public Map<String, Object> updateProfileName(Map<String, Object> body) {
        return call("chat/updateProfileName", body);
    }

    /** Atualiza a foto do perfil. */
    public Map<String, Object> updateProfilePicture(Map<String, Object> body) {
        return call("chat/updateProfilePicture", body);
    }

    /** Atualiza o status do perfil. */
    public Map<String, Object> updateProfileStatus(Map<String, Object> body) {
        return call("chat/updateProfileStatus", body);
    }

    /** Remove a foto do perfil. */
    public Map<String, Object> removeProfilePicture(Map<String, Object> body) {
        return call("chat/removeProfilePicture", body);
    }

    /** Base64 da mídia de uma mensagem. */
    public Map<String, Object> getBase64FromMediaMessage(Map<String, Object> body) {
        return call("chat/getBase64FromMediaMessage", body);
    }

    /** Verifica números no WhatsApp. */
    public Map<String, Object> whatsappNumbers(Map<String, Object> body) {
        return call("chat/whatsappNumbers", body);
    }

    // ------------------------------------------------------------------ grupos

    /** Cria grupo. */
    public Map<String, Object> createGroup(Map<String, Object> body) {
        return call("group/create", body);
    }

    /** Lista todos os grupos (com participantes). */
    public Map<String, Object> fetchAllGroups(Map<String, Object> body) {
        return call("group/fetchAllGroups?getParticipants=true", body);
    }

    /** Informações de um grupo. */
    public Map<String, Object> findGroupInfos(Map<String, Object> body) {
        return call("group/findGroupInfos", body);
    }

    /** Código de convite. */
    public Map<String, Object> inviteCode(Map<String, Object> body) {
        return call("group/inviteCode", body);
    }

    /** Informações do convite. */
    public Map<String, Object> inviteInfo(Map<String, Object> body) {
        return call("group/inviteInfo", body);
    }

    /** Participantes do grupo. */
    public Map<String, Object> participants(Map<String, Object> body) {
        return call("group/participants", body);
    }

    /** Revoga o código de convite. */
    public Map<String, Object> revokeInviteCode(Map<String, Object> body) {
        return call("group/revokeInviteCode", body);
    }

    /** Envia convite. */
    public Map<String, Object> sendInvite(Map<String, Object> body) {
        return call("group/sendInvite", body);
    }

    /** Atualiza a descrição do grupo. */
    public Map<String, Object> updateGroupDescription(Map<String, Object> body) {
        return call("group/updateGroupDescription", body);
    }

    /** Atualiza a foto do grupo. */
    public Map<String, Object> updateGroupPicture(Map<String, Object> body) {
        return call("group/updateGroupPicture", body);
    }

    /** Atualiza o assunto do grupo. */
    public Map<String, Object> updateGroupSubject(Map<String, Object> body) {
        return call("group/updateGroupSubject", body);
    }

    /** Atualiza um participante (promover, remover...). */
    public Map<String, Object> updateParticipant(Map<String, Object> body) {
        return call("group/updateParticipant", body);
    }

    /** Atualiza uma configuração do grupo. */
    public Map<String, Object> updateSetting(Map<String, Object> body) {
        return call("group/updateSetting", body);
    }

    // --------------------------------------------------------- labels e chamadas

    /** Lista etiquetas. */
    public Map<String, Object> findLabels() {
        return call("label/findLabels", null);
    }

    public Map<String, Object> findLabels(Map<String, Object> body) {
        return call("label/findLabels", body);
    }

    /** Aplica/remove etiqueta. */
    public Map<String, Object> handleLabel(Map<String, Object> body) {
        return call("label/handleLabel", body);
    }

    /** Oferece chamada. */
    public Map<String, Object> offerCall(Map<String, Object> body) {
        return call("call/offer", body);
    }

    // ------------------------------------------------------------ configurações

    /** Lê as configurações da instância. */
    public Map<String, Object> findSettings() {
        return call("settings/find", null);
    }

    public Map<String, Object> findSettings(Map<String, Object> body) {
        return call("settings/find", body);
    }

    /** Grava as configurações da instância. */
    public Map<String, Object> setSettings(Map<String, Object> body) {
        return call("settings/set", body);
    }
}

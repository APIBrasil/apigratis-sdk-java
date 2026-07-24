package com.apibrasil.sdk.services.messaging;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.DeviceProxyService;

import java.util.Map;

/**
 * WhatsApp device-based ({@code POST /whatsapp/{action}}).
 * Exige {@code Authorization: Bearer} + header {@code DeviceToken}.
 *
 * <pre>{@code
 * api.whatsapp.start();
 * String qrcode = (String) api.whatsapp.qrcode().get("response");
 * api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá!"));
 * api.whatsapp.queue("sendText", Json.of("number", "5511999999999", "text", "assíncrono"));
 * }</pre>
 *
 * <p>Actions não cobertas por um método dedicado continuam acessíveis por
 * {@link #request(String, Object)} — a lista completa está em
 * {@code Catalog.WHATSAPP_ACTIONS}.
 */
public class WhatsAppService extends DeviceProxyService {

    public WhatsAppService(ApiHttpClient http) {
        super(http, "whatsapp");
    }

    // ---------------------------------------------------------------- sessão

    /** Inicia a sessão do device (aceita webhooks opcionais). */
    public Map<String, Object> start() {
        return request("start");
    }

    public Map<String, Object> start(Map<String, Object> body) {
        return request("start", body);
    }

    public Map<String, Object> start(Map<String, Object> body, RequestOptions options) {
        return request("start", body, options);
    }

    /** Devolve o QR Code de pareamento ({@code response.qrcode} em base64). */
    public Map<String, Object> qrcode() {
        return request("qrcode");
    }

    public Map<String, Object> qrcode(Map<String, Object> body) {
        return request("qrcode", body);
    }

    public Map<String, Object> qrcode(Map<String, Object> body, RequestOptions options) {
        return request("qrcode", body, options);
    }

    /** Encerra a sessão. */
    public Map<String, Object> logout() {
        return request("logout");
    }

    public Map<String, Object> logout(Map<String, Object> body) {
        return request("logout", body);
    }

    /** Fecha o browser/sessão no servidor. */
    public Map<String, Object> close() {
        return request("close");
    }

    public Map<String, Object> close(Map<String, Object> body) {
        return request("close", body);
    }

    /** Apaga a sessão no servidor. */
    public Map<String, Object> deleteSession() {
        return request("deleteSession");
    }

    public Map<String, Object> deleteSession(Map<String, Object> body) {
        return request("deleteSession", body);
    }

    /** Reinicia a sessão. */
    public Map<String, Object> restartSession() {
        return request("restartSession");
    }

    public Map<String, Object> restartSession(Map<String, Object> body) {
        return request("restartSession", body);
    }

    /** Versões do WhatsApp suportadas. */
    public Map<String, Object> whatsappVersions() {
        return request("whatsapp-versions");
    }

    /** Fila de mensagens do device. */
    public Map<String, Object> fila() {
        return request("fila");
    }

    public Map<String, Object> fila(Map<String, Object> body) {
        return request("fila", body);
    }

    // ------------------------------------------------------------------ envio

    /** Envia texto: {@code {"number": "5511999999999", "text": "Olá!"}}. */
    public Map<String, Object> sendText(Map<String, Object> body) {
        return request("sendText", body);
    }

    public Map<String, Object> sendText(Map<String, Object> body, RequestOptions options) {
        return request("sendText", body, options);
    }

    /** Envia arquivo por URL: {@code {"number": ..., "path": ...}}. */
    public Map<String, Object> sendFile(Map<String, Object> body) {
        return request("sendFile", body);
    }

    public Map<String, Object> sendFile(Map<String, Object> body, RequestOptions options) {
        return request("sendFile", body, options);
    }

    /** Envia arquivo em base64. */
    public Map<String, Object> sendFile64(Map<String, Object> body) {
        return request("sendFile64", body);
    }

    /** Envia áudio por URL (convertido para mp3 pelo gateway, máx. 6 min). */
    public Map<String, Object> sendAudio(Map<String, Object> body) {
        return request("sendAudio", body);
    }

    /** Envia áudio em base64. */
    public Map<String, Object> sendAudio64(Map<String, Object> body) {
        return request("sendAudio64", body);
    }

    /** Envia vídeo por URL. */
    public Map<String, Object> sendVideo(Map<String, Object> body) {
        return request("sendVideo", body);
    }

    /** Envia vídeo como GIF. */
    public Map<String, Object> sendVideoAsGif(Map<String, Object> body) {
        return request("sendVideoAsGif", body);
    }

    /** Envia GIF. */
    public Map<String, Object> sendGif(Map<String, Object> body) {
        return request("sendGif", body);
    }

    /** Envia link com preview. */
    public Map<String, Object> sendLink(Map<String, Object> body) {
        return request("sendLink", body);
    }

    /** Envia localização: {@code {"number": ..., "lat": ..., "lng": ...}}. */
    public Map<String, Object> sendLocation(Map<String, Object> body) {
        return request("sendLocation", body);
    }

    /** Envia contato. */
    public Map<String, Object> sendContact(Map<String, Object> body) {
        return request("sendContact", body);
    }

    /** Envia lista de contatos (vCard). */
    public Map<String, Object> sendContactVcardList(Map<String, Object> body) {
        return request("sendContactVcardList", body);
    }

    /** Envia figurinha. */
    public Map<String, Object> sendSticker(Map<String, Object> body) {
        return request("sendSticker", body);
    }

    /** Envia mensagem de lista. */
    public Map<String, Object> sendList(Map<String, Object> body) {
        return request("sendList", body);
    }

    /** Envia mensagem com botões. */
    public Map<String, Object> sendButtons(Map<String, Object> body) {
        return request("sendButtons", body);
    }

    /** Envia enquete. */
    public Map<String, Object> sendPollMessage(Map<String, Object> body) {
        return request("sendPollMessage", body);
    }

    /** Envia pedido/ordem. */
    public Map<String, Object> sendOrderMessage(Map<String, Object> body) {
        return request("sendOrderMessage", body);
    }

    /** Envia chave PIX. */
    public Map<String, Object> sendPixKey(Map<String, Object> body) {
        return request("sendPixKey", body);
    }

    /** Envia mensagem com thumbnail. */
    public Map<String, Object> sendMessageWithThumb(Map<String, Object> body) {
        return request("sendMessageWithThumb", body);
    }

    /** Envia mensagem mencionando participantes. */
    public Map<String, Object> sendMentioned(Map<String, Object> body) {
        return request("sendMentioned", body);
    }

    /** Marca mensagens como lidas. */
    public Map<String, Object> sendReadStatus(Map<String, Object> body) {
        return request("sendReadStatus", body);
    }

    /** Publica imagem no status. */
    public Map<String, Object> sendImageToStorie(Map<String, Object> body) {
        return request("sendImageToStorie", body);
    }

    /** Publica texto no status. */
    public Map<String, Object> sendTextToStorie(Map<String, Object> body) {
        return request("sendTextToStorie", body);
    }

    /** Publica vídeo no status. */
    public Map<String, Object> sendVideoToStorie(Map<String, Object> body) {
        return request("sendVideoToStorie", body);
    }

    /** Responde a uma mensagem. */
    public Map<String, Object> reply(Map<String, Object> body) {
        return request("reply", body);
    }

    /** Encaminha mensagens. */
    public Map<String, Object> forwardMessages(Map<String, Object> body) {
        return request("forwardMessages", body);
    }

    /** Apaga uma mensagem. */
    public Map<String, Object> deleteMessage(Map<String, Object> body) {
        return request("deleteMessage", body);
    }

    /** Baixa a mídia de uma mensagem. */
    public Map<String, Object> downloadMediaByMessage(Map<String, Object> body) {
        return request("downloadMediaByMessage", body);
    }

    /** Marca áudio como ouvido. */
    public Map<String, Object> markPlayed(Map<String, Object> body) {
        return request("markPlayed", body);
    }

    /** Reações de uma mensagem. */
    public Map<String, Object> getReactions(Map<String, Object> body) {
        return request("getReactions", body);
    }

    // ------------------------------------------------------------- presença

    /** Inicia o indicador "digitando". */
    public Map<String, Object> startTyping(Map<String, Object> body) {
        return request("startTyping", body);
    }

    /** Para o indicador "digitando". */
    public Map<String, Object> stopTyping(Map<String, Object> body) {
        return request("stopTyping", body);
    }

    /** Inicia o indicador "gravando". */
    public Map<String, Object> startRecording(Map<String, Object> body) {
        return request("startRecording", body);
    }

    /** Para o indicador "gravando". */
    public Map<String, Object> stopRecording(Map<String, Object> body) {
        return request("stopRecording", body);
    }

    // --------------------------------------------------------------- consulta

    /** Verifica se um número está no WhatsApp. */
    public Map<String, Object> checkNumberStatus(Map<String, Object> body) {
        return request("checkNumberStatus", body);
    }

    /** Valida um número. */
    public Map<String, Object> verifyNumber(Map<String, Object> body) {
        return request("verifyNumber", body);
    }

    /** Perfil de um número. */
    public Map<String, Object> getNumberProfile(Map<String, Object> body) {
        return request("getNumberProfile", body);
    }

    /** Foto de perfil. */
    public Map<String, Object> getProfilePic(Map<String, Object> body) {
        return request("getProfilePic", body);
    }

    /** Número a partir do LID. */
    public Map<String, Object> getPhoneNumberByLid(Map<String, Object> body) {
        return request("getPhoneNumberByLid", body);
    }

    /** Plataforma de origem de uma mensagem. */
    public Map<String, Object> getPlatformFromMessage(Map<String, Object> body) {
        return request("getPlatformFromMessage", body);
    }

    /** Estado da conexão. */
    public Map<String, Object> getConnectionState() {
        return request("getConnectionState");
    }

    /** Status da conexão. */
    public Map<String, Object> getConnectionStatus() {
        return request("getConnectionStatus");
    }

    /** Nível de bateria do aparelho. */
    public Map<String, Object> getBatteryLevel() {
        return request("getBatteryLevel");
    }

    /** Status atual da sessão. */
    public Map<String, Object> getStatus() {
        return request("getStatus");
    }

    /** WID da sessão. */
    public Map<String, Object> getWid() {
        return request("getWid");
    }

    /** {@code true} se a sessão está autenticada. */
    public Map<String, Object> isAuthenticated() {
        return request("isAuthenticated");
    }

    /** {@code true} se a sessão está conectada. */
    public Map<String, Object> isConnected() {
        return request("isConnected");
    }

    /** {@code true} se a sessão está logada. */
    public Map<String, Object> isLoggedIn() {
        return request("isLoggedIn");
    }

    /** {@code true} se a conta é multi-device. */
    public Map<String, Object> isMultiDevice() {
        return request("isMultiDevice");
    }

    /** Entra no WhatsApp Web Beta. */
    public Map<String, Object> joinWebBeta() {
        return request("joinWebBeta");
    }

    /** Inicia o watchdog do telefone. */
    public Map<String, Object> startPhoneWatchdog() {
        return request("startPhoneWatchdog");
    }

    /** Para o watchdog do telefone. */
    public Map<String, Object> stopPhoneWatchdog() {
        return request("stopPhoneWatchdog");
    }

    // ------------------------------------------------------------------ chats

    /** Lista todos os chats. */
    public Map<String, Object> getAllChats() {
        return request("getAllChats");
    }

    /** Lista os chats com suas mensagens. */
    public Map<String, Object> getAllChatsWithMessages() {
        return request("getAllChatsWithMessages");
    }

    /** Lista todos os contatos. */
    public Map<String, Object> getAllContacts() {
        return request("getAllContacts");
    }

    /** Lista as mensagens novas. */
    public Map<String, Object> getAllNewMessages() {
        return request("getAllNewMessages");
    }

    /** Lista as mensagens não lidas. */
    public Map<String, Object> getUnreadMessages() {
        return request("getUnreadMessages");
    }

    /** Lista as listas de transmissão. */
    public Map<String, Object> getAllBroadcastList() {
        return request("getAllBroadcastList");
    }

    /** Lista as etiquetas. */
    public Map<String, Object> getAllLabels() {
        return request("getAllLabels");
    }

    /** Lista os produtos do catálogo. */
    public Map<String, Object> getProducts() {
        return request("getProducts");
    }

    /** Dados de um chat. */
    public Map<String, Object> getChat(Map<String, Object> body) {
        return request("getChat", body);
    }

    /** Mensagens de um chat. */
    public Map<String, Object> getMessagesChat(Map<String, Object> body) {
        return request("getMessagesChat", body);
    }

    /** Mensagens a partir de um row id. */
    public Map<String, Object> getMessagesFromRowId(Map<String, Object> body) {
        return request("getMessagesFromRowId", body);
    }

    /** Carrega mensagens anteriores. */
    public Map<String, Object> loadEarlierMessages(Map<String, Object> body) {
        return request("loadEarlierMessages", body);
    }

    /** Abre um chat. */
    public Map<String, Object> openChat(Map<String, Object> body) {
        return request("openChat", body);
    }

    /** Fecha um chat. */
    public Map<String, Object> closeChat(Map<String, Object> body) {
        return request("closeChat", body);
    }

    /** Arquiva um chat. */
    public Map<String, Object> archiveChat(Map<String, Object> body) {
        return request("archiveChat", body);
    }

    /** Limpa um chat. */
    public Map<String, Object> clearChat(Map<String, Object> body) {
        return request("clearChat", body);
    }

    /** Apaga um chat. */
    public Map<String, Object> deleteChat(Map<String, Object> body) {
        return request("deleteChat", body);
    }

    // --------------------------------------------------------------- contatos

    /** Lista os contatos bloqueados. */
    public Map<String, Object> getBlockList() {
        return request("getBlockList");
    }

    /** Bloqueia um contato. */
    public Map<String, Object> blockContact(Map<String, Object> body) {
        return request("blockContact", body);
    }

    /** Desbloqueia um contato. */
    public Map<String, Object> unblockContact(Map<String, Object> body) {
        return request("unblockContact", body);
    }

    // ----------------------------------------------------------------- grupos

    /** Lista todos os grupos. */
    public Map<String, Object> getAllGroups() {
        return request("getAllGroups");
    }

    /** Lista os grupos com informações completas. */
    public Map<String, Object> getAllGroupsFull() {
        return request("getAllGroupsFull");
    }

    /** Cria um grupo. */
    public Map<String, Object> createGroup(Map<String, Object> body) {
        return request("createGroup", body);
    }

    /** Entra em um grupo. */
    public Map<String, Object> joinGroup(Map<String, Object> body) {
        return request("joinGroup", body);
    }

    /** Sai de um grupo. */
    public Map<String, Object> leaveGroup(Map<String, Object> body) {
        return request("leaveGroup", body);
    }

    /** Administradores do grupo. */
    public Map<String, Object> getGroupAdmins(Map<String, Object> body) {
        return request("getGroupAdmins", body);
    }

    /** Membros do grupo. */
    public Map<String, Object> getGroupMembers(Map<String, Object> body) {
        return request("getGroupMembers", body);
    }

    /** IDs dos membros do grupo. */
    public Map<String, Object> getGroupMembersIds(Map<String, Object> body) {
        return request("getGroupMembersIds", body);
    }

    /** Solicitações de entrada no grupo. */
    public Map<String, Object> getGroupMembershipRequests(Map<String, Object> body) {
        return request("getGroupMembershipRequests", body);
    }

    /** Aprova uma solicitação de entrada. */
    public Map<String, Object> approveGroupMembershipRequest(Map<String, Object> body) {
        return request("approveGroupMembershipRequest", body);
    }

    /** Link de convite do grupo. */
    public Map<String, Object> getGroupInviteLink(Map<String, Object> body) {
        return request("getGroupInviteLink", body);
    }

    /** Informações do grupo a partir do link de convite. */
    public Map<String, Object> getGroupInfoFromInviteLink(Map<String, Object> body) {
        return request("getGroupInfoFromInviteLink", body);
    }

    /** Limite de participantes do grupo. */
    public Map<String, Object> getGroupSizeLimit() {
        return request("getGroupSizeLimit");
    }

    /** Grupos em comum com um contato. */
    public Map<String, Object> getCommonGroups(Map<String, Object> body) {
        return request("getCommonGroups", body);
    }

    /** Adiciona participante. */
    public Map<String, Object> addParticipant(Map<String, Object> body) {
        return request("addParticipant", body);
    }

    /** Remove participante. */
    public Map<String, Object> removeParticipant(Map<String, Object> body) {
        return request("removeParticipant", body);
    }

    /** Promove participante a administrador. */
    public Map<String, Object> promoteParticipant(Map<String, Object> body) {
        return request("promoteParticipant", body);
    }

    /** Rebaixa administrador a participante. */
    public Map<String, Object> demoteParticipant(Map<String, Object> body) {
        return request("demoteParticipant", body);
    }

    /** Define o assunto do grupo. */
    public Map<String, Object> setGroupSubject(Map<String, Object> body) {
        return request("setGroupSubject", body);
    }

    /** Define a descrição do grupo. */
    public Map<String, Object> setGroupDescription(Map<String, Object> body) {
        return request("setGroupDescription", body);
    }

    /** Define a foto do grupo. */
    public Map<String, Object> setGroupPic(Map<String, Object> body) {
        return request("setGroupPic", body);
    }

    /** Remove a foto do grupo. */
    public Map<String, Object> removeGroupIcon(Map<String, Object> body) {
        return request("removeGroupIcon", body);
    }

    /** Define uma propriedade do grupo. */
    public Map<String, Object> setGroupProperty(Map<String, Object> body) {
        return request("setGroupProperty", body);
    }

    /** Restringe mensagens a administradores. */
    public Map<String, Object> setMessagesAdminsOnly(Map<String, Object> body) {
        return request("setMessagesAdminsOnly", body);
    }

    /** Define mensagens temporárias. */
    public Map<String, Object> setTemporaryMessages(Map<String, Object> body) {
        return request("setTemporaryMessages", body);
    }

    // ------------------------------------------------------------ comunidades

    /** Cria uma comunidade. */
    public Map<String, Object> createCommunity(Map<String, Object> body) {
        return request("createCommunity", body);
    }

    /** Desativa uma comunidade. */
    public Map<String, Object> deactivateCommunity(Map<String, Object> body) {
        return request("deactivateCommunity", body);
    }

    /** Participantes da comunidade. */
    public Map<String, Object> getCommunityParticipants(Map<String, Object> body) {
        return request("getCommunityParticipants", body);
    }

    /** Promove participante da comunidade. */
    public Map<String, Object> promoteCommunityParticipant(Map<String, Object> body) {
        return request("promoteCommunityParticipant", body);
    }

    /** Rebaixa participante da comunidade. */
    public Map<String, Object> demoteCommunityParticipant(Map<String, Object> body) {
        return request("demoteCommunityParticipant", body);
    }

    /** Adiciona subgrupos à comunidade. */
    public Map<String, Object> addSubgroupsCommunity(Map<String, Object> body) {
        return request("addSubgroupsCommunity", body);
    }

    /** Remove subgrupos da comunidade. */
    public Map<String, Object> removeSubgroupsCommunity(Map<String, Object> body) {
        return request("removeSubgroupsCommunity", body);
    }

    // ------------------------------------------------------------------ conta

    /** Define o nome do perfil. */
    public Map<String, Object> setProfileName(Map<String, Object> body) {
        return request("setProfileName", body);
    }

    /** Define a foto do perfil. */
    public Map<String, Object> setProfilePic(Map<String, Object> body) {
        return request("setProfilePic", body);
    }

    /** Define o tema (claro/escuro). */
    public Map<String, Object> setTheme(Map<String, Object> body) {
        return request("setTheme", body);
    }

    /** Configurações de download automático. */
    public Map<String, Object> getAutoDownloadSettings() {
        return request("getAutoDownloadSettings");
    }

    /** Define as configurações de download automático. */
    public Map<String, Object> setAutoDownloadSettings(Map<String, Object> body) {
        return request("setAutoDownloadSettings", body);
    }
}

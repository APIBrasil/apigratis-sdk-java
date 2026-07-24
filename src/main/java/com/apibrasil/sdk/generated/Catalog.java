package com.apibrasil.sdk.generated;

import java.util.List;
import java.util.Map;

/**
 * ARQUIVO GERADO AUTOMATICAMENTE — não edite manualmente.
 *
 * <p>Fonte: https://gateway.apibrasil.io/api/v2/documentations
 * <br>Regenerar: {@code mvn -Pcodegen exec:java}
 *
 * <p>242 documentações, 549 endpoints, 210 tipos de consulta conhecidos.
 */
public final class Catalog {

    private Catalog() {
    }

    /** Actions conhecidas da API de WhatsApp ({@code POST /whatsapp/{action}}). */
    public static final class WhatsAppActions {

        private WhatsAppActions() {
        }

        public static final String ADD_PARTICIPANT = "addParticipant";
        public static final String ADD_SUBGROUPS_COMMUNITY = "addSubgroupsCommunity";
        public static final String APPROVE_GROUP_MEMBERSHIP_REQUEST = "approveGroupMembershipRequest";
        public static final String ARCHIVE_CHAT = "archiveChat";
        public static final String BLOCK_CONTACT = "blockContact";
        public static final String CHECK_NUMBER_STATUS = "checkNumberStatus";
        public static final String CLEAR_CHAT = "clearChat";
        public static final String CLOSE = "close";
        public static final String CLOSE_CHAT = "closeChat";
        public static final String CREATE_COMMUNITY = "createCommunity";
        public static final String CREATE_GROUP = "createGroup";
        public static final String DEACTIVATE_COMMUNITY = "deactivateCommunity";
        public static final String DELETE_CHAT = "deleteChat";
        public static final String DELETE_MESSAGE = "deleteMessage";
        public static final String DELETE_SESSION = "deleteSession";
        public static final String DEMOTE_COMMUNITY_PARTICIPANT = "demoteCommunityParticipant";
        public static final String DEMOTE_PARTICIPANT = "demoteParticipant";
        public static final String DOWNLOAD_MEDIA_BY_MESSAGE = "downloadMediaByMessage";
        public static final String FILA = "fila";
        public static final String FORWARD_MESSAGES = "forwardMessages";
        public static final String GET_ALL_BROADCAST_LIST = "getAllBroadcastList";
        public static final String GET_ALL_CHATS = "getAllChats";
        public static final String GET_ALL_CHATS_WITH_MESSAGES = "getAllChatsWithMessages";
        public static final String GET_ALL_CONTACTS = "getAllContacts";
        public static final String GET_ALL_GROUPS = "getAllGroups";
        public static final String GET_ALL_GROUPS_FULL = "getAllGroupsFull";
        public static final String GET_ALL_LABELS = "getAllLabels";
        public static final String GET_ALL_NEW_MESSAGES = "getAllNewMessages";
        public static final String GET_AUTO_DOWNLOAD_SETTINGS = "getAutoDownloadSettings";
        public static final String GET_BATTERY_LEVEL = "getBatteryLevel";
        public static final String GET_BLOCK_LIST = "getBlockList";
        public static final String GET_CHAT = "getChat";
        public static final String GET_COMMON_GROUPS = "getCommonGroups";
        public static final String GET_COMMUNITY_PARTICIPANTS = "getCommunityParticipants";
        public static final String GET_CONNECTION_STATE = "getConnectionState";
        public static final String GET_CONNECTION_STATUS = "getConnectionStatus";
        public static final String GET_GROUP_ADMINS = "getGroupAdmins";
        public static final String GET_GROUP_INFO_FROM_INVITE_LINK = "getGroupInfoFromInviteLink";
        public static final String GET_GROUP_INVITE_LINK = "getGroupInviteLink";
        public static final String GET_GROUP_MEMBERS = "getGroupMembers";
        public static final String GET_GROUP_MEMBERS_IDS = "getGroupMembersIds";
        public static final String GET_GROUP_MEMBERSHIP_REQUESTS = "getGroupMembershipRequests";
        public static final String GET_GROUP_SIZE_LIMIT = "getGroupSizeLimit";
        public static final String GET_MESSAGES_CHAT = "getMessagesChat";
        public static final String GET_MESSAGES_FROM_ROW_ID = "getMessagesFromRowId";
        public static final String GET_NUMBER_PROFILE = "getNumberProfile";
        public static final String GET_PHONE_NUMBER_BY_LID = "getPhoneNumberByLid";
        public static final String GET_PLATFORM_FROM_MESSAGE = "getPlatformFromMessage";
        public static final String GET_PRODUCTS = "getProducts";
        public static final String GET_PROFILE_PIC = "getProfilePic";
        public static final String GET_REACTIONS = "getReactions";
        public static final String GET_STATUS = "getStatus";
        public static final String GET_UNREAD_MESSAGES = "getUnreadMessages";
        public static final String GET_WID = "getWid";
        public static final String IS_AUTHENTICATED = "isAuthenticated";
        public static final String IS_CONNECTED = "isConnected";
        public static final String IS_LOGGED_IN = "isLoggedIn";
        public static final String IS_MULTI_DEVICE = "isMultiDevice";
        public static final String JOIN_GROUP = "joinGroup";
        public static final String JOIN_WEB_BETA = "joinWebBeta";
        public static final String LEAVE_GROUP = "leaveGroup";
        public static final String LOAD_EARLIER_MESSAGES = "loadEarlierMessages";
        public static final String LOGOUT = "logout";
        public static final String MARK_PLAYED = "markPlayed";
        public static final String OPEN_CHAT = "openChat";
        public static final String PROMOTE_COMMUNITY_PARTICIPANT = "promoteCommunityParticipant";
        public static final String PROMOTE_PARTICIPANT = "promoteParticipant";
        public static final String QRCODE = "qrcode";
        public static final String REMOVE_GROUP_ICON = "removeGroupIcon";
        public static final String REMOVE_PARTICIPANT = "removeParticipant";
        public static final String REMOVE_SUBGROUPS_COMMUNITY = "removeSubgroupsCommunity";
        public static final String REPLY = "reply";
        public static final String RESTART_SESSION = "restartSession";
        public static final String SEND_AUDIO = "sendAudio";
        public static final String SEND_AUDIO64 = "sendAudio64";
        public static final String SEND_BUTTONS = "sendButtons";
        public static final String SEND_CONTACT = "sendContact";
        public static final String SEND_CONTACT_VCARD_LIST = "sendContactVcardList";
        public static final String SEND_FILE = "sendFile";
        public static final String SEND_FILE_QUEUE = "sendFile/queue";
        public static final String SEND_FILE64 = "sendFile64";
        public static final String SEND_GIF = "sendGif";
        public static final String SEND_IMAGE_TO_STORIE = "sendImageToStorie";
        public static final String SEND_LINK = "sendLink";
        public static final String SEND_LIST = "sendList";
        public static final String SEND_LOCATION = "sendLocation";
        public static final String SEND_MENTIONED = "sendMentioned";
        public static final String SEND_MESSAGE_WITH_THUMB = "sendMessageWithThumb";
        public static final String SEND_ORDER_MESSAGE = "sendOrderMessage";
        public static final String SEND_ORDER_MESSAGE_QUEUE = "sendOrderMessage/queue";
        public static final String SEND_PIX_KEY = "sendPixKey";
        public static final String SEND_POLL_MESSAGE = "sendPollMessage";
        public static final String SEND_READ_STATUS = "sendReadStatus";
        public static final String SEND_STICKER = "sendSticker";
        public static final String SEND_TEXT = "sendText";
        public static final String SEND_TEXT_QUEUE = "sendText/queue";
        public static final String SEND_TEXT_TO_STORIE = "sendTextToStorie";
        public static final String SEND_VIDEO = "sendVideo";
        public static final String SEND_VIDEO_QUEUE = "sendVideo/queue";
        public static final String SEND_VIDEO_AS_GIF = "sendVideoAsGif";
        public static final String SEND_VIDEO_TO_STORIE = "sendVideoToStorie";
        public static final String SET_AUTO_DOWNLOAD_SETTINGS = "setAutoDownloadSettings";
        public static final String SET_GROUP_DESCRIPTION = "setGroupDescription";
        public static final String SET_GROUP_PIC = "setGroupPic";
        public static final String SET_GROUP_PROPERTY = "setGroupProperty";
        public static final String SET_GROUP_SUBJECT = "setGroupSubject";
        public static final String SET_MESSAGES_ADMINS_ONLY = "setMessagesAdminsOnly";
        public static final String SET_PROFILE_NAME = "setProfileName";
        public static final String SET_PROFILE_PIC = "setProfilePic";
        public static final String SET_TEMPORARY_MESSAGES = "setTemporaryMessages";
        public static final String SET_THEME = "setTheme";
        public static final String START = "start";
        public static final String START_PHONE_WATCHDOG = "startPhoneWatchdog";
        public static final String START_RECORDING = "startRecording";
        public static final String START_TYPING = "startTyping";
        public static final String STOP_PHONE_WATCHDOG = "stopPhoneWatchdog";
        public static final String STOP_RECORDING = "stopRecording";
        public static final String STOP_TYPING = "stopTyping";
        public static final String UNBLOCK_CONTACT = "unblockContact";
        public static final String VERIFY_NUMBER = "verifyNumber";
        public static final String WHATSAPP_VERSIONS = "whatsapp-versions";

        /** Todos os valores conhecidos, em ordem alfabética. */
        public static final List<String> ALL = List.of(
                "addParticipant",
                "addSubgroupsCommunity",
                "approveGroupMembershipRequest",
                "archiveChat",
                "blockContact",
                "checkNumberStatus",
                "clearChat",
                "close",
                "closeChat",
                "createCommunity",
                "createGroup",
                "deactivateCommunity",
                "deleteChat",
                "deleteMessage",
                "deleteSession",
                "demoteCommunityParticipant",
                "demoteParticipant",
                "downloadMediaByMessage",
                "fila",
                "forwardMessages",
                "getAllBroadcastList",
                "getAllChats",
                "getAllChatsWithMessages",
                "getAllContacts",
                "getAllGroups",
                "getAllGroupsFull",
                "getAllLabels",
                "getAllNewMessages",
                "getAutoDownloadSettings",
                "getBatteryLevel",
                "getBlockList",
                "getChat",
                "getCommonGroups",
                "getCommunityParticipants",
                "getConnectionState",
                "getConnectionStatus",
                "getGroupAdmins",
                "getGroupInfoFromInviteLink",
                "getGroupInviteLink",
                "getGroupMembers",
                "getGroupMembersIds",
                "getGroupMembershipRequests",
                "getGroupSizeLimit",
                "getMessagesChat",
                "getMessagesFromRowId",
                "getNumberProfile",
                "getPhoneNumberByLid",
                "getPlatformFromMessage",
                "getProducts",
                "getProfilePic",
                "getReactions",
                "getStatus",
                "getUnreadMessages",
                "getWid",
                "isAuthenticated",
                "isConnected",
                "isLoggedIn",
                "isMultiDevice",
                "joinGroup",
                "joinWebBeta",
                "leaveGroup",
                "loadEarlierMessages",
                "logout",
                "markPlayed",
                "openChat",
                "promoteCommunityParticipant",
                "promoteParticipant",
                "qrcode",
                "removeGroupIcon",
                "removeParticipant",
                "removeSubgroupsCommunity",
                "reply",
                "restartSession",
                "sendAudio",
                "sendAudio64",
                "sendButtons",
                "sendContact",
                "sendContactVcardList",
                "sendFile",
                "sendFile/queue",
                "sendFile64",
                "sendGif",
                "sendImageToStorie",
                "sendLink",
                "sendList",
                "sendLocation",
                "sendMentioned",
                "sendMessageWithThumb",
                "sendOrderMessage",
                "sendOrderMessage/queue",
                "sendPixKey",
                "sendPollMessage",
                "sendReadStatus",
                "sendSticker",
                "sendText",
                "sendText/queue",
                "sendTextToStorie",
                "sendVideo",
                "sendVideo/queue",
                "sendVideoAsGif",
                "sendVideoToStorie",
                "setAutoDownloadSettings",
                "setGroupDescription",
                "setGroupPic",
                "setGroupProperty",
                "setGroupSubject",
                "setMessagesAdminsOnly",
                "setProfileName",
                "setProfilePic",
                "setTemporaryMessages",
                "setTheme",
                "start",
                "startPhoneWatchdog",
                "startRecording",
                "startTyping",
                "stopPhoneWatchdog",
                "stopRecording",
                "stopTyping",
                "unblockContact",
                "verifyNumber",
                "whatsapp-versions");
    }

    /** Caminhos conhecidos da Evolution API ({@code POST /evolution/{controller}/{action}}). */
    public static final class EvolutionPaths {

        private EvolutionPaths() {
        }

        public static final String CALL_OFFER = "call/offer";
        public static final String CHAT_DELETE_MESSAGE_FOR_EVERYONE = "chat/deleteMessageForEveryone";
        public static final String CHAT_FETCH_PRIVACY_SETTINGS = "chat/fetchPrivacySettings";
        public static final String CHAT_FETCH_PROFILE = "chat/fetchProfile";
        public static final String CHAT_FETCH_PROFILE_PICTURE_URL = "chat/fetchProfilePictureUrl";
        public static final String CHAT_FIND_CHATS = "chat/findChats";
        public static final String CHAT_FIND_CONTACTS = "chat/findContacts";
        public static final String CHAT_FIND_MESSAGES = "chat/findMessages";
        public static final String CHAT_GET_BASE64_FROM_MEDIA_MESSAGE = "chat/getBase64FromMediaMessage";
        public static final String CHAT_REMOVE_PROFILE_PICTURE = "chat/removeProfilePicture";
        public static final String CHAT_UPDATE_PRIVACY_SETTINGS = "chat/updatePrivacySettings";
        public static final String CHAT_UPDATE_PROFILE_NAME = "chat/updateProfileName";
        public static final String CHAT_UPDATE_PROFILE_PICTURE = "chat/updateProfilePicture";
        public static final String CHAT_UPDATE_PROFILE_STATUS = "chat/updateProfileStatus";
        public static final String CHAT_WHATSAPP_NUMBERS = "chat/whatsappNumbers";
        public static final String GROUP_CREATE = "group/create";
        public static final String GROUP_SEND_INVITE = "group/sendInvite";
        public static final String INSTANCE_CONNECT = "instance/connect";
        public static final String INSTANCE_CONNECTION_STATE = "instance/connectionState";
        public static final String INSTANCE_CREATE = "instance/create";
        public static final String INSTANCE_DELETE = "instance/delete";
        public static final String INSTANCE_LOGOUT = "instance/logout";
        public static final String INSTANCE_RESTART = "instance/restart";
        public static final String LABEL_FIND_LABELS = "label/findLabels";
        public static final String LABEL_HANDLE_LABEL = "label/handleLabel";
        public static final String MESSAGE_SEND_BUTTONS = "message/sendButtons";
        public static final String MESSAGE_SEND_CONTACT = "message/sendContact";
        public static final String MESSAGE_SEND_LOCATION = "message/sendLocation";
        public static final String MESSAGE_SEND_MEDIA = "message/sendMedia";
        public static final String MESSAGE_SEND_POLL = "message/sendPoll";
        public static final String MESSAGE_SEND_REACTION = "message/sendReaction";
        public static final String MESSAGE_SEND_STATUS = "message/sendStatus";
        public static final String MESSAGE_SEND_STICKER = "message/sendSticker";
        public static final String MESSAGE_SEND_TEXT = "message/sendText";
        public static final String MESSAGE_SEND_TEXT_QUEUE = "message/sendText/queue";
        public static final String MESSAGE_SEND_WHATS_APP_AUDIO = "message/sendWhatsAppAudio";
        public static final String SETTINGS_FIND = "settings/find";
        public static final String SETTINGS_SET = "settings/set";

        /** Todos os valores conhecidos, em ordem alfabética. */
        public static final List<String> ALL = List.of(
                "call/offer",
                "chat/deleteMessageForEveryone",
                "chat/fetchPrivacySettings",
                "chat/fetchProfile",
                "chat/fetchProfilePictureUrl",
                "chat/findChats",
                "chat/findContacts",
                "chat/findMessages",
                "chat/getBase64FromMediaMessage",
                "chat/removeProfilePicture",
                "chat/updatePrivacySettings",
                "chat/updateProfileName",
                "chat/updateProfilePicture",
                "chat/updateProfileStatus",
                "chat/whatsappNumbers",
                "group/create",
                "group/fetchAllGroups?getParticipants=true",
                "group/findGroupInfos?groupJid=120363314888103300",
                "group/inviteCode?groupJid=120363314888103300",
                "group/inviteInfo?groupJid=120363314888103300",
                "group/participants?groupJid=120363314888103300",
                "group/revokeInviteCode?groupJid=120363314888103300",
                "group/sendInvite",
                "group/updateGroupDescription?groupJid=120363314888103300",
                "group/updateGroupPicture?groupJid=120363314888103300",
                "group/updateGroupSubject?groupJid=120363314888103300",
                "group/updateParticipant?groupJid=120363314888103300",
                "group/updateSetting?groupJid=120363314888103300",
                "instance/connect",
                "instance/connectionState",
                "instance/create",
                "instance/delete",
                "instance/logout",
                "instance/restart",
                "label/findLabels",
                "label/handleLabel",
                "message/sendButtons",
                "message/sendContact",
                "message/sendLocation",
                "message/sendMedia",
                "message/sendPoll",
                "message/sendReaction",
                "message/sendStatus",
                "message/sendSticker",
                "message/sendText",
                "message/sendText/queue",
                "message/sendWhatsAppAudio",
                "settings/find",
                "settings/set");
    }

    /** Actions conhecidas do WhatsMeow ({@code POST /whatsmeow/{action}}). */
    public static final class WhatsMeowActions {

        private WhatsMeowActions() {
        }

        public static final String CHAT_ARCHIVE = "chat/archive";
        public static final String CHAT_MUTE = "chat/mute";
        public static final String CHAT_PIN = "chat/pin";
        public static final String CHAT_UNPIN = "chat/unpin";
        public static final String GROUP_CREATE = "group/create";
        public static final String GROUP_INFO = "group/info";
        public static final String GROUP_INVITELINK = "group/invitelink";
        public static final String GROUP_JOIN = "group/join";
        public static final String GROUP_LIST = "group/list";
        public static final String GROUP_MYALL = "group/myall";
        public static final String GROUP_NAME = "group/name";
        public static final String GROUP_PARTICIPANT = "group/participant";
        public static final String GROUP_PHOTO = "group/photo";
        public static final String INSTANCE_CONNECT = "instance/connect";
        public static final String INSTANCE_CREATE = "instance/create";
        public static final String INSTANCE_DISCONNECT = "instance/disconnect";
        public static final String INSTANCE_LOGOUT = "instance/logout";
        public static final String INSTANCE_QR = "instance/qr";
        public static final String SEND_CONTACT = "send/contact";
        public static final String SEND_LINK = "send/link";
        public static final String SEND_LOCATION = "send/location";
        public static final String SEND_MEDIA = "send/media";
        public static final String SEND_POLL = "send/poll";
        public static final String SEND_STICKER = "send/sticker";
        public static final String SEND_TEXT = "send/text";
        public static final String USER_AVATAR = "user/avatar";
        public static final String USER_BLOCK = "user/block";
        public static final String USER_BLOCKLIST = "user/blocklist";
        public static final String USER_CHECK = "user/check";
        public static final String USER_CONTACTS = "user/contacts";
        public static final String USER_INFO = "user/info";
        public static final String USER_PRIVACY = "user/privacy";
        public static final String USER_PROFILE = "user/profile";
        public static final String USER_UNBLOCK = "user/unblock";

        /** Todos os valores conhecidos, em ordem alfabética. */
        public static final List<String> ALL = List.of(
                "chat/archive",
                "chat/mute",
                "chat/pin",
                "chat/unpin",
                "group/create",
                "group/info",
                "group/invitelink",
                "group/join",
                "group/list",
                "group/myall",
                "group/name",
                "group/participant",
                "group/photo",
                "instance/connect",
                "instance/create",
                "instance/delete/<devicekey>",
                "instance/disconnect",
                "instance/info/<devicekey>",
                "instance/logout",
                "instance/qr",
                "send/contact",
                "send/link",
                "send/location",
                "send/media",
                "send/poll",
                "send/sticker",
                "send/text",
                "user/avatar",
                "user/block",
                "user/blocklist",
                "user/check",
                "user/contacts",
                "user/info",
                "user/privacy",
                "user/profile",
                "user/unblock");
    }

    /** Serviços de consulta por crédito ({@code POST /consulta/{servico}/credits}). */
    public static final class ConsultaServicos {

        private ConsultaServicos() {
        }

        public static final String API_RNTRC = "api-rntrc";
        public static final String CEP = "cep";
        public static final String CNPJ = "cnpj";
        public static final String CPF = "cpf";
        public static final String CRBM = "crbm";
        public static final String CRM = "crm";
        public static final String CRO = "cro";
        public static final String DDD_ANATEL = "ddd-anatel";
        public static final String EMISSAO_NOTAS = "emissao-notas";
        public static final String FRETE_ANTT = "frete-antt";
        public static final String GEOIP = "geoip";
        public static final String RASTREIO = "rastreio";
        public static final String TELEFONE = "telefone";
        public static final String VEHICLES = "vehicles";
        public static final String VEICULOS = "veiculos";
        public static final String WEATHER_API = "weather-api";

        /** Todos os valores conhecidos, em ordem alfabética. */
        public static final List<String> ALL = List.of(
                "api-rntrc",
                "cep",
                "cnpj",
                "cpf",
                "crbm",
                "crm",
                "cro",
                "ddd-anatel",
                "emissao-notas",
                "frete-antt",
                "geoip",
                "rastreio",
                "telefone",
                "vehicles",
                "veiculos",
                "weather-api");
    }

    /** Tipos de consulta conhecidos (campo {@code tipo} do body). */
    public static final class ConsultaTipos {

        private ConsultaTipos() {
        }

        public static final String ACERTA_COMPLETO_POSITIVO_PF = "acerta-completo-positivo-pf";
        public static final String ACERTA_ESSENCIAL = "acerta-essencial";
        public static final String ACERTA_ESSENCIAL_POSITIVO = "acerta-essencial-positivo";
        public static final String ACOES_PROCESSOS_JUDICIAIS = "acoes-processos-judiciais";
        public static final String AGREGADOS_BASICA = "agregados-basica";
        public static final String AGREGADOS_CHASSI = "agregados-chassi";
        public static final String AGREGADOS_INDICIO_SINISTRO = "agregados-indicio-sinistro";
        public static final String AGREGADOS_PROPRIA = "agregados-propria";
        public static final String AGREGADOS_RENAVAM = "agregados-renavam";
        public static final String AGREGADOS_RENAVAM_V2 = "agregados-renavam-v2";
        public static final String AGREGADOS_SIMPLES = "agregados-simples";
        public static final String AGREGADOS_V2 = "agregados-v2";
        public static final String AML_VINCULOS_SOCIETARIOS = "aml-vinculos-societarios";
        public static final String ANALISE_CREDITO_BASIC_PF = "analise-credito-basic-pf";
        public static final String ANALISE_CREDITO_BASIC_PJ = "analise-credito-basic-pj";
        public static final String ANALISE_CREDITO_BUSINESS = "analise-credito-business";
        public static final String ANALISE_CREDITO_COMPLETE_PF = "analise-credito-complete-pf";
        public static final String ANALISE_CREDITO_ESSENCIAL_PF = "analise-credito-essencial-pf";
        public static final String ANALISE_CREDITO_PLUS_PF = "analise-credito-plus-pf";
        public static final String ANALITICO_VEICULAR = "analitico-veicular";
        public static final String ANTECEDENTES_CRIMINAIS = "antecedentes-criminais";
        public static final String ANTIFRAUDE_CHAVE_PIX = "antifraude-chave-pix";
        public static final String BAIRROS = "bairros";
        public static final String BANCO_CENTRAL_INABILITADOS = "banco-central-inabilitados";
        public static final String BASE_ESTADUAL_V3 = "base-estadual-v3";
        public static final String BASE_ESTADUAL_V3_ASYNC = "base-estadual-v3-async";
        public static final String BASE_NACIONAL_ONLINE = "base-nacional-online";
        public static final String BASE_NACIONAL_V2 = "base-nacional-v2";
        public static final String BENEFICIOS_SOCIAIS_PF = "beneficios-sociais-pf";
        public static final String BET_SAFE_COMPLIANCE = "bet-safe-compliance";
        public static final String BOA_VISTA_ACERTA_PF = "boa-vista-acerta-pf";
        public static final String BOA_VISTA_DEFINE_SCORE = "boa-vista-define-score";
        public static final String CADASTRAL_PREMIUM_PJ = "cadastral-premium-pj";
        public static final String CAF_PF = "caf-pf";
        public static final String CALCULA_FRETE = "calcula-frete";
        public static final String CCF_BACEN = "ccf-bacen";
        public static final String CEP = "cep";
        public static final String CERTIDAO_CONJUNTA_DE_DEBITOS_PESSOA_FISICA = "certidao-conjunta-de-debitos-pessoa-fisica";
        public static final String CERTIDAO_CONJUNTA_DE_DEBITOS_PESSOA_JURIDICA = "certidao-conjunta-de-debitos-pessoa-juridica";
        public static final String CERTIDAO_NEGATIVA_DE_DEBITOS = "certidao-negativa-de-debitos";
        public static final String CERTIDAO_NEGATIVA_DE_LICITANTE_INIDONEO = "certidao-negativa-de-licitante-inidoneo";
        public static final String CERTIFICADO_UPLOAD = "certificado-upload";
        public static final String CHECK_LIST = "check-list";
        public static final String CIDADES = "cidades";
        public static final String CIDADES_POR_DDD = "cidadesPorDDD";
        public static final String CITY = "city";
        public static final String CNH_CRIMINALS = "cnh-criminals";
        public static final String CNH_CRIMINALS_ASYNC = "cnh-criminals-async";
        public static final String CNH_POR_CPF = "cnh-por-cpf";
        public static final String CNPJ_CADASTRAL = "cnpj-cadastral";
        public static final String CNPJ_SEARCH = "cnpj-search";
        public static final String COMPLIANCE_BASIC = "compliance-basic";
        public static final String COMPLIANCE_BASIC_PJ = "compliance-basic-pj";
        public static final String COMPLIANCE_COMPLETE = "compliance-complete";
        public static final String COMPLIANCE_COMPLETE_PJ = "compliance-complete-pj";
        public static final String CONSULTA_ANO_MODELO = "consulta-ano-modelo";
        public static final String CONSULTA_CGU = "consulta-cgu";
        public static final String CONSULTA_CHAVE = "consulta-chave";
        public static final String CONSULTA_CONSOLIDADA_DE_PESSOA_JURIDICA = "consulta-consolidada-de-pessoa-juridica";
        public static final String CONSULTA_MARCA = "consulta-marca";
        public static final String CONSULTA_MODELO = "consulta-modelo";
        public static final String CONSULTA_MODELOS_ATRAVES_DO_ANO = "consulta-modelos-atraves-do-ano";
        public static final String CONSULTA_TABELA_REFERENCIA = "consulta-tabela-referencia";
        public static final String CONSULTA_VALOR_COM_TODOS_PARAMETROS = "consulta-valor-com-todos-parametros";
        public static final String CONSULTAR_CHAVE_BASE_ESTADUAL_V3 = "consultar-chave-base-estadual-v3";
        public static final String CONSULTAR_CHAVE_CNH_CRIMINALS = "consultar-chave-cnh-criminals";
        public static final String CONSULTAR_CHAVE_CSV_RENAINF_RENAJUD_BIN_PROPRIETARIO = "consultar-chave-csv-renainf-renajud-bin-proprietario";
        public static final String CONSULTAR_CHAVE_LEILAO_V2 = "consultar-chave-leilao-v2";
        public static final String CONSULTAR_CHAVE_VEICULOS_TOTAL = "consultar-chave-veiculos-total";
        public static final String COORDINATES = "coordinates";
        public static final String CPF_DADOS = "cpf-dados";
        public static final String CPF_HOTLINE = "cpf-hotline";
        public static final String CPF_IMPEDIDOS = "cpf-impedidos";
        public static final String CPF_LITE = "cpf-lite";
        public static final String CPF_OBITO_GRUPO_CADASTRAL = "cpf-obito-grupo-cadastral";
        public static final String CPF_RELATORIO = "cpf-relatorio";
        public static final String CPF_SEARCH = "cpf-search";
        public static final String CPF_SEARCH_MAE = "cpf-search-mae";
        public static final String CPF_SOCIODEMOGRAFICOS = "cpf-sociodemograficos";
        public static final String CRBM = "crbm";
        public static final String CREDITOS_SIMPLES_PF = "creditos-simples-pf";
        public static final String CREDITOS_SIMPLES_PJ = "creditos-simples-pj";
        public static final String CRLVE = "crlve";
        public static final String CRM = "crm";
        public static final String CRO = "cro";
        public static final String CSV_RENAINF_RENAJUD_BIN_PROPRIETARIO = "csv-renainf-renajud-bin-proprietario";
        public static final String CSV_RENAINF_RENAJUD_BIN_PROPRIETARIO_ASYNC = "csv-renainf-renajud-bin-proprietario-async";
        public static final String DADOS_CADASTRAIS = "dados-cadastrais";
        public static final String DADOS_PROCESSOS = "dados-processos";
        public static final String DDD_CIDADE = "ddd-cidade";
        public static final String DEBITOS_RESTRICOES = "debitos-restricoes";
        public static final String DEBITOS_V4 = "debitos-v4";
        public static final String DEBITOS_V4_ASYNC = "debitos-v4-async";
        public static final String DECODIFICADOR_AGREGADOS = "decodificador-agregados";
        public static final String DECODIFICADOR_PRECIFICADOR = "decodificador-precificador";
        public static final String DEFINE_RISCO_PJ = "define-risco-pj";
        public static final String DETALHAMENTO_NEGATIVO = "detalhamento-negativo";
        public static final String DIVIDA_ATIVA = "divida-ativa";
        public static final String DOCUMENTO_CRLV_AC = "documento-crlv-ac";
        public static final String DOCUMENTO_CRLV_AP = "documento-crlv-ap";
        public static final String DOCUMENTO_CRLV_BA = "documento-crlv-ba";
        public static final String DOCUMENTO_CRLV_GO = "documento-crlv-go";
        public static final String DOCUMENTO_CRLV_MA = "documento-crlv-ma";
        public static final String DOCUMENTO_CRLV_MG = "documento-crlv-mg";
        public static final String DOCUMENTO_CRLV_MS = "documento-crlv-ms";
        public static final String DOCUMENTO_CRLV_MT = "documento-crlv-mt";
        public static final String DOCUMENTO_CRLV_PI = "documento-crlv-pi";
        public static final String DOCUMENTO_CRLV_RO = "documento-crlv-ro";
        public static final String DOCUMENTO_CRLV_RR = "documento-crlv-rr";
        public static final String DOCUMENTO_CRLV_TO = "documento-crlv-to";
        public static final String DOCUMENTO_FROTA = "documento-frota";
        public static final String EMITENTES_ATUALIZA = "emitentes-atualiza";
        public static final String EMITENTES_CADASTRO = "emitentes-cadastro";
        public static final String EMITENTES_LISTA = "emitentes-lista";
        public static final String ENDERECO_TELEFONE_POR_PLACA = "endereco-telefone-por-placa";
        public static final String ENRIQUECIMENTO_DE_LEAD = "enriquecimento-de-lead";
        public static final String ESTADUAL = "estadual";
        public static final String FAROL = "farol";
        public static final String FGTS_REGULARIDADE_DO_EMPREGADOR = "fgts-regularidade-do-empregador";
        public static final String FICHA_TECNICA = "ficha-tecnica";
        public static final String FIPE = "fipe";
        public static final String FIPE_CHASSI = "fipe-chassi";
        public static final String GEOIP = "geoip";
        public static final String GRAVAME = "gravame";
        public static final String GRAVAME_V2 = "gravame-v2";
        public static final String HISTORICO_ALTERACOES_EMPRESA = "historico-alteracoes-empresa";
        public static final String HISTORICO_KM = "historico-km";
        public static final String HISTORICO_PROPRIETARIO = "historico-proprietario";
        public static final String HISTORICO_VEICULOS_PF_PJ = "historico-veiculos-pf-pj";
        public static final String LEILAO = "leilao";
        public static final String LEILAO_COMPLETO_SCORE = "leilao-completo-score";
        public static final String LEILAO_CONJUGADO = "leilao-conjugado";
        public static final String LEILAO_SINTETICO = "leilao-sintetico";
        public static final String LEILAO_V2 = "leilao-v2";
        public static final String LEILAO_V2_ASYNC = "leilao-v2-async";
        public static final String LIMITE_PJ = "limite-pj";
        public static final String LIMITE_POSITIVO_PJ = "limite-positivo-pj";
        public static final String LOCALIZA_CNPJ = "localiza-cnpj";
        public static final String MODELOS_CARGA = "modelos-carga";
        public static final String MULTAS = "multas";
        public static final String MULTAS_PRF = "multas-prf";
        public static final String NACIONAL = "nacional";
        public static final String NFSE_CONSULTA = "nfse-consulta";
        public static final String NFSE_ENVIO = "nfse-envio";
        public static final String NORMATIVA_ATUAL = "normativa-atual";
        public static final String OBITO = "obito";
        public static final String OPCOES_CALCULO = "opcoes-calculo";
        public static final String PEP_LISTA_RESTRITIVA = "pep-lista-restritiva";
        public static final String PESSOA_EXPOSTA_POLITICAMENTE = "pessoa-exposta-politicamente";
        public static final String PESSOA_EXPOSTA_POLITICAMENTE_PARENTESCO = "pessoa-exposta-politicamente-parentesco";
        public static final String PESSOA_JURIDICA_PROTESTO = "pessoa-juridica-protesto";
        public static final String PROPRIETARIO_ATUAL = "proprietario-atual";
        public static final String PROPRIETARIO_ATUAL_V2 = "proprietario-atual-v2";
        public static final String PROTESTO_NACIONAL_V2 = "protesto-nacional-v2";
        public static final String PROTESTOS_NACIONAL_BASE = "protestos-nacional-base";
        public static final String PROTESTOS_SP = "protestos-sp";
        public static final String QUOD_PJ = "quod-pj";
        public static final String QUOD_RESTRICAO_PF = "quod-restricao-pf";
        public static final String RASTREIO = "rastreio";
        public static final String RATING_AVANCADO_PJ = "rating-avancado-pj";
        public static final String RECALL = "recall";
        public static final String RECALL_V2 = "recall-v2";
        public static final String RECEITA_FEDERAL = "receita-federal";
        public static final String RECEITA_FEDERAL_PF = "receita-federal-pf";
        public static final String RECEITA_FEDERAL_PF_V3 = "receita-federal-pf-v3";
        public static final String RECEITA_FEDERAL_PJ = "receita-federal-pj";
        public static final String RECEITA_FEDERAL_PJ_V3 = "receita-federal-pj-v3";
        public static final String REGULARIDADE_TRANSPORTADORA = "regularidade-transportadora";
        public static final String RELATORIO_POSITIVO = "relatorio-positivo";
        public static final String RELATORIO_POSITIVO_PJ = "relatorio-positivo-pj";
        public static final String RELATORIO_VEICULAR = "relatorio-veicular";
        public static final String RELATORIO_VEICULAR_COMPLETO = "relatorio-veicular-completo";
        public static final String RENAINF = "renainf";
        public static final String RENAJUD = "renajud";
        public static final String RISCO_POSITIVO_PJ = "risco-positivo-pj";
        public static final String ROUBO_FURTO = "roubo-furto";
        public static final String ROUBO_FURTO_V2 = "roubo-furto-v2";
        public static final String SCORE_CREDITO_QUOD = "score-credito-quod";
        public static final String SCPC_NET_PF = "scpc-net-pf";
        public static final String SCPC_NET_PJ = "scpc-net-pj";
        public static final String SCR_ANALITICO_RESUMO_BACEN = "scr-analitico-resumo-bacen";
        public static final String SCR_BACEN = "scr-bacen";
        public static final String SCR_BACEN_SCORE = "scr-bacen-score";
        public static final String SEARCH = "search";
        public static final String SECRETARIA_DA_FAZENDA_SAO_PAULO = "secretaria-da-fazenda-sao-paulo";
        public static final String SERASA_REAL_TIME = "serasa-real-time";
        public static final String SERASA_SCORE_PF = "serasa-score-pf";
        public static final String SERASA_SCORE_PJ = "serasa-score-pj";
        public static final String SIMPLES_NACIONAL = "simples-nacional";
        public static final String SINCRONIZA_NORMATIVAS = "sincroniza-normativas";
        public static final String SINTEGRA_CADASTROS_ESTADUAIS = "sintegra-cadastros-estaduais";
        public static final String SINTEGRA_CCC = "sintegra-ccc";
        public static final String SITUACAO_ELEITORAL = "situacao-eleitoral";
        public static final String SPC_BOA_VISTA = "spc-boa-vista";
        public static final String SPC_SERASA = "spc-serasa";
        public static final String SPC_TERCEIROS_PF = "spc-terceiros-pf";
        public static final String SPC_TERCEIROS_PJ = "spc-terceiros-pj";
        public static final String TELEFONE_OPERADORA = "telefone-operadora";
        public static final String TRANSACIONAL_PF = "transacional-pf";
        public static final String TRANSACIONAL_PJ = "transacional-pj";
        public static final String VAR = "var";
        public static final String VEICULAR_AGRUPADOS = "veicular-agrupados";
        public static final String VEICULOS_DADOS_V1 = "veiculos-dados-v1";
        public static final String VEICULOS_DOCUMENTO_PF = "veiculos-documento-pf";
        public static final String VEICULOS_DOCUMENTO_PJ = "veiculos-documento-pj";
        public static final String VEICULOS_TOTAL = "veiculos-total";
        public static final String VEICULOS_TOTAL_ASYNC = "veiculos-total-async";
        public static final String VERSOES_NORMATIVAS = "versoes-normativas";
        public static final String VINCULO_EMPREGATICIO = "vinculo-empregaticio";
        public static final String VIP_CAR = "vip-car";

        /** Todos os valores conhecidos, em ordem alfabética. */
        public static final List<String> ALL = List.of(
                "acerta-completo-positivo-pf",
                "acerta-essencial",
                "acerta-essencial-positivo",
                "acoes-processos-judiciais",
                "agregados-basica",
                "agregados-chassi",
                "agregados-indicio-sinistro",
                "agregados-propria",
                "agregados-renavam",
                "agregados-renavam-v2",
                "agregados-simples",
                "agregados-v2",
                "aml-vinculos-societarios",
                "analise-credito-basic-pf",
                "analise-credito-basic-pj",
                "analise-credito-business",
                "analise-credito-complete-pf",
                "analise-credito-essencial-pf",
                "analise-credito-plus-pf",
                "analitico-veicular",
                "antecedentes-criminais",
                "antifraude-chave-pix",
                "bairros",
                "banco-central-inabilitados",
                "base-estadual-v3",
                "base-estadual-v3-async",
                "base-nacional-online",
                "base-nacional-v2",
                "beneficios-sociais-pf",
                "bet-safe-compliance",
                "boa-vista-acerta-pf",
                "boa-vista-define-score",
                "cadastral-premium-pj",
                "caf-pf",
                "calcula-frete",
                "ccf-bacen",
                "cep",
                "certidao-conjunta-de-debitos-pessoa-fisica",
                "certidao-conjunta-de-debitos-pessoa-juridica",
                "certidao-negativa-de-debitos",
                "certidao-negativa-de-licitante-inidoneo",
                "certificado-upload",
                "check-list",
                "cidades",
                "cidadesPorDDD",
                "city",
                "cnh-criminals",
                "cnh-criminals-async",
                "cnh-por-cpf",
                "cnpj-cadastral",
                "cnpj-search",
                "compliance-basic",
                "compliance-basic-pj",
                "compliance-complete",
                "compliance-complete-pj",
                "consulta-ano-modelo",
                "consulta-cgu",
                "consulta-chave",
                "consulta-consolidada-de-pessoa-juridica",
                "consulta-marca",
                "consulta-modelo",
                "consulta-modelos-atraves-do-ano",
                "consulta-tabela-referencia",
                "consulta-valor-com-todos-parametros",
                "consultar-chave-base-estadual-v3",
                "consultar-chave-cnh-criminals",
                "consultar-chave-csv-renainf-renajud-bin-proprietario",
                "consultar-chave-leilao-v2",
                "consultar-chave-veiculos-total",
                "coordinates",
                "cpf-dados",
                "cpf-hotline",
                "cpf-impedidos",
                "cpf-lite",
                "cpf-obito-grupo-cadastral",
                "cpf-relatorio",
                "cpf-search",
                "cpf-search-mae",
                "cpf-sociodemograficos",
                "crbm",
                "creditos-simples-pf",
                "creditos-simples-pj",
                "crlve",
                "crm",
                "cro",
                "csv-renainf-renajud-bin-proprietario",
                "csv-renainf-renajud-bin-proprietario-async",
                "dados-cadastrais",
                "dados-processos",
                "ddd-cidade",
                "debitos-restricoes",
                "debitos-v4",
                "debitos-v4-async",
                "decodificador-agregados",
                "decodificador-precificador",
                "define-risco-pj",
                "detalhamento-negativo",
                "divida-ativa",
                "documento-crlv-ac",
                "documento-crlv-ap",
                "documento-crlv-ba",
                "documento-crlv-go",
                "documento-crlv-ma",
                "documento-crlv-mg",
                "documento-crlv-ms",
                "documento-crlv-mt",
                "documento-crlv-pi",
                "documento-crlv-ro",
                "documento-crlv-rr",
                "documento-crlv-to",
                "documento-frota",
                "emitentes-atualiza",
                "emitentes-cadastro",
                "emitentes-lista",
                "endereco-telefone-por-placa",
                "enriquecimento-de-lead",
                "estadual",
                "farol",
                "fgts-regularidade-do-empregador",
                "ficha-tecnica",
                "fipe",
                "fipe-chassi",
                "geoip",
                "gravame",
                "gravame-v2",
                "historico-alteracoes-empresa",
                "historico-km",
                "historico-proprietario",
                "historico-veiculos-pf-pj",
                "leilao",
                "leilao-completo-score",
                "leilao-conjugado",
                "leilao-sintetico",
                "leilao-v2",
                "leilao-v2-async",
                "limite-pj",
                "limite-positivo-pj",
                "localiza-cnpj",
                "modelos-carga",
                "multas",
                "multas-prf",
                "nacional",
                "nfse-consulta",
                "nfse-envio",
                "normativa-atual",
                "obito",
                "opcoes-calculo",
                "pep-lista-restritiva",
                "pessoa-exposta-politicamente",
                "pessoa-exposta-politicamente-parentesco",
                "pessoa-juridica-protesto",
                "proprietario-atual",
                "proprietario-atual-v2",
                "protesto-nacional-v2",
                "protestos-nacional-base",
                "protestos-sp",
                "quod-pj",
                "quod-restricao-pf",
                "rastreio",
                "rating-avancado-pj",
                "recall",
                "recall-v2",
                "receita-federal",
                "receita-federal-pf",
                "receita-federal-pf-v3",
                "receita-federal-pj",
                "receita-federal-pj-v3",
                "regularidade-transportadora",
                "relatorio-positivo",
                "relatorio-positivo-pj",
                "relatorio-veicular",
                "relatorio-veicular-completo",
                "renainf",
                "renajud",
                "risco-positivo-pj",
                "roubo-furto",
                "roubo-furto-v2",
                "score-credito-quod",
                "scpc-net-pf",
                "scpc-net-pj",
                "scr-analitico-resumo-bacen",
                "scr-bacen",
                "scr-bacen-score",
                "search",
                "secretaria-da-fazenda-sao-paulo",
                "serasa-real-time",
                "serasa-score-pf",
                "serasa-score-pj",
                "simples-nacional",
                "sincroniza-normativas",
                "sintegra-cadastros-estaduais",
                "sintegra-ccc",
                "situacao-eleitoral",
                "spc-boa-vista",
                "spc-serasa",
                "spc-terceiros-pf",
                "spc-terceiros-pj",
                "telefone-operadora",
                "transacional-pf",
                "transacional-pj",
                "var",
                "veicular-agrupados",
                "veiculos-dados-v1",
                "veiculos-documento-pf",
                "veiculos-documento-pj",
                "veiculos-total",
                "veiculos-total-async",
                "versoes-normativas",
                "vinculo-empregaticio",
                "vip-car");
    }

    /**
     * Metadados de um tipo de consulta: serviço da rota e campos do body
     * de exemplo documentado.
     *
     * @param service serviço da rota ({@code /consulta/{service}/credits})
     * @param fields  campos do body de exemplo, fora {@code tipo} e {@code homolog}
     */
    public record ConsultaTipoInfo(String service, List<String> fields) {
    }

    /** Actions documentadas por serviço do gateway. */
    public static final Map<String, List<String>> SERVICE_ACTIONS = Map.ofEntries(
            Map.entry("api", List.of("loterias/:sorteio")),
            Map.entry("apis", List.of("list")),
            Map.entry("auth", List.of("login", "logout")),
            Map.entry("balance", List.of()),
            Map.entry("cep", List.of("bairros", "cep", "cidades", "cidadesPorDDD", "distancia/calcular", "estados")),
            Map.entry("chip", List.of("virtual/activation", "virtual/buy", "virtual/operators", "virtual/services")),
            Map.entry("consulta", List.of("api-rntrc/credits", "cep/credits", "cnpj/credits", "cpf/credits", "crbm/credits", "crm/credits", "cro/credits", "ddd-anatel/credits", "emissao-notas/credits", "frete-antt/credits", "geoip/credits", "rastreio/credits", "telefone/credits", "vehicles/credits", "veiculos/credits", "weather-api/credits")),
            Map.entry("correios", List.of("rastreio")),
            Map.entry("dados", List.of("byquery", "capital-social", "cep", "cnae", "cnpj", "cnpj/credits", "cpf", "lista-cnaes", "lista-socios", "uf")),
            Map.entry("database", List.of("ip")),
            Map.entry("ddd", List.of()),
            Map.entry("devices", List.of("destroy", "requests", "search", "store")),
            Map.entry("evolution", List.of("call/offer", "chat/deleteMessageForEveryone", "chat/fetchPrivacySettings", "chat/fetchProfile", "chat/fetchProfilePictureUrl", "chat/findChats", "chat/findContacts", "chat/findMessages", "chat/getBase64FromMediaMessage", "chat/removeProfilePicture", "chat/updatePrivacySettings", "chat/updateProfileName", "chat/updateProfilePicture", "chat/updateProfileStatus", "chat/whatsappNumbers", "group/create", "group/fetchAllGroups?getParticipants=true", "group/findGroupInfos?groupJid=120363314888103300", "group/inviteCode?groupJid=120363314888103300", "group/inviteInfo?groupJid=120363314888103300", "group/participants?groupJid=120363314888103300", "group/revokeInviteCode?groupJid=120363314888103300", "group/sendInvite", "group/updateGroupDescription?groupJid=120363314888103300", "group/updateGroupPicture?groupJid=120363314888103300", "group/updateGroupSubject?groupJid=120363314888103300", "group/updateParticipant?groupJid=120363314888103300", "group/updateSetting?groupJid=120363314888103300", "instance/connect", "instance/connectionState", "instance/create", "instance/delete", "instance/logout", "instance/restart", "label/findLabels", "label/handleLabel", "message/sendButtons", "message/sendContact", "message/sendLocation", "message/sendMedia", "message/sendPoll", "message/sendReaction", "message/sendStatus", "message/sendSticker", "message/sendText", "message/sendText/queue", "message/sendWhatsAppAudio", "settings/find", "settings/set")),
            Map.entry("fipe", List.of("ConsultarAnoModelo", "ConsultarMarcas", "ConsultarModelos", "ConsultarModelosAtravesDoAno", "ConsultarTabelaDeReferencia", "ConsultarValorComTodosParametros")),
            Map.entry("geolocation", List.of("forward-geocoding", "geocode")),
            Map.entry("geomatrix", List.of("distance")),
            Map.entry("holidays", List.of("feriados")),
            Map.entry("invoices", List.of()),
            Map.entry("loterias", List.of(":sorteio/:concurso", ":sorteio/latest")),
            Map.entry("plan", List.of()),
            Map.entry("profile", List.of()),
            Map.entry("proxy", List.of("seller/credits")),
            Map.entry("quod", List.of("cnpj/credits")),
            Map.entry("recharge", List.of()),
            Map.entry("recharges", List.of()),
            Map.entry("recognize", List.of("base64", "uri")),
            Map.entry("requests", List.of("paginate")),
            Map.entry("servers", List.of()),
            Map.entry("sms", List.of("send/credits")),
            Map.entry("social", List.of("github/callback?code=SEU_CODE&state=SEU_STATE", "github/url")),
            Map.entry("ticket", List.of("TCK-HKGMWHCW2S", "TCK-HKGMWHCW2S/messages")),
            Map.entry("tickets", List.of()),
            Map.entry("translate", List.of("identify", "models")),
            Map.entry("ura", List.of("call/dialler", "call/status?callId=")),
            Map.entry("vehicles", List.of("base/000/dados", "dados", "fipe")),
            Map.entry("weather", List.of("city", "coordenates")),
            Map.entry("whatsapp", List.of("addParticipant", "addSubgroupsCommunity", "approveGroupMembershipRequest", "archiveChat", "blockContact", "checkNumberStatus", "clearChat", "close", "closeChat", "createCommunity", "createGroup", "deactivateCommunity", "deleteChat", "deleteMessage", "deleteSession", "demoteCommunityParticipant", "demoteParticipant", "downloadMediaByMessage", "fila", "forwardMessages", "getAllBroadcastList", "getAllChats", "getAllChatsWithMessages", "getAllContacts", "getAllGroups", "getAllGroupsFull", "getAllLabels", "getAllNewMessages", "getAutoDownloadSettings", "getBatteryLevel", "getBlockList", "getChat", "getCommonGroups", "getCommunityParticipants", "getConnectionState", "getConnectionStatus", "getGroupAdmins", "getGroupInfoFromInviteLink", "getGroupInviteLink", "getGroupMembers", "getGroupMembersIds", "getGroupMembershipRequests", "getGroupSizeLimit", "getMessagesChat", "getMessagesFromRowId", "getNumberProfile", "getPhoneNumberByLid", "getPlatformFromMessage", "getProducts", "getProfilePic", "getReactions", "getStatus", "getUnreadMessages", "getWid", "isAuthenticated", "isConnected", "isLoggedIn", "isMultiDevice", "joinGroup", "joinWebBeta", "leaveGroup", "loadEarlierMessages", "logout", "markPlayed", "openChat", "promoteCommunityParticipant", "promoteParticipant", "qrcode", "removeGroupIcon", "removeParticipant", "removeSubgroupsCommunity", "reply", "restartSession", "sendAudio", "sendAudio64", "sendButtons", "sendContact", "sendContactVcardList", "sendFile", "sendFile/queue", "sendFile64", "sendGif", "sendImageToStorie", "sendLink", "sendList", "sendLocation", "sendMentioned", "sendMessageWithThumb", "sendOrderMessage", "sendOrderMessage/queue", "sendPixKey", "sendPollMessage", "sendReadStatus", "sendSticker", "sendText", "sendText/queue", "sendTextToStorie", "sendVideo", "sendVideo/queue", "sendVideoAsGif", "sendVideoToStorie", "setAutoDownloadSettings", "setGroupDescription", "setGroupPic", "setGroupProperty", "setGroupSubject", "setMessagesAdminsOnly", "setProfileName", "setProfilePic", "setTemporaryMessages", "setTheme", "start", "startPhoneWatchdog", "startRecording", "startTyping", "stopPhoneWatchdog", "stopRecording", "stopTyping", "unblockContact", "verifyNumber", "whatsapp-versions")),
            Map.entry("whatsmeow", List.of("chat/archive", "chat/mute", "chat/pin", "chat/unpin", "group/create", "group/info", "group/invitelink", "group/join", "group/list", "group/myall", "group/name", "group/participant", "group/photo", "instance/connect", "instance/create", "instance/delete/<devicekey>", "instance/disconnect", "instance/info/<devicekey>", "instance/logout", "instance/qr", "send/contact", "send/link", "send/location", "send/media", "send/poll", "send/sticker", "send/text", "user/avatar", "user/block", "user/blocklist", "user/check", "user/contacts", "user/info", "user/privacy", "user/profile", "user/unblock")));

    /** Metadados por tipo de consulta por crédito. */
    public static final Map<String, ConsultaTipoInfo> CONSULTA_TIPOS_INFO = Map.ofEntries(
            Map.entry("acerta-completo-positivo-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("acerta-essencial", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("acerta-essencial-positivo", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("acoes-processos-judiciais", new ConsultaTipoInfo("cpf", List.of("//cnpj", "cpf"))),
            Map.entry("agregados-basica", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-chassi", new ConsultaTipoInfo("veiculos", List.of("chassi"))),
            Map.entry("agregados-indicio-sinistro", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-propria", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-renavam", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-renavam-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-simples", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("agregados-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("aml-vinculos-societarios", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("analise-credito-basic-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("analise-credito-basic-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("analise-credito-business", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("analise-credito-complete-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("analise-credito-essencial-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("analise-credito-plus-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("analitico-veicular", new ConsultaTipoInfo("veiculos", List.of("extra", "placa", "whitelabel"))),
            Map.entry("antecedentes-criminais", new ConsultaTipoInfo("cpf", List.of("cpf", "rg", "uf"))),
            Map.entry("antifraude-chave-pix", new ConsultaTipoInfo("cpf", List.of("chave-pix", "documento", "tipo-chave"))),
            Map.entry("bairros", new ConsultaTipoInfo("cep", List.of("cidade"))),
            Map.entry("banco-central-inabilitados", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("base-estadual-v3", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("base-estadual-v3-async", new ConsultaTipoInfo("vehicles", List.of("placa", "webhook_url"))),
            Map.entry("base-nacional-online", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("base-nacional-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("beneficios-sociais-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("bet-safe-compliance", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("boa-vista-acerta-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("boa-vista-define-score", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("cadastral-premium-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("caf-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("calcula-frete", new ConsultaTipoInfo("frete-antt", List.of("altoDesempenho", "composicaoVeicular", "dataReferencia", "distanciaKm", "eixos", "retornoVazio", "tipoCarga"))),
            Map.entry("ccf-bacen", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cep", new ConsultaTipoInfo("cep", List.of("cep"))),
            Map.entry("certidao-conjunta-de-debitos-pessoa-fisica", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("certidao-conjunta-de-debitos-pessoa-juridica", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("certidao-negativa-de-debitos", new ConsultaTipoInfo("cpf", List.of("cpf", "uf"))),
            Map.entry("certidao-negativa-de-licitante-inidoneo", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("certificado-upload", new ConsultaTipoInfo("emissao-notas", List.of("dados"))),
            Map.entry("check-list", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("cidades", new ConsultaTipoInfo("cep", List.of("uf"))),
            Map.entry("cidadesPorDDD", new ConsultaTipoInfo("cep", List.of("ddd"))),
            Map.entry("city", new ConsultaTipoInfo("weather-api", List.of("city"))),
            Map.entry("cnh-criminals", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cnh-criminals-async", new ConsultaTipoInfo("cpf", List.of("cpf", "webhook_url"))),
            Map.entry("cnh-por-cpf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cnpj-cadastral", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("cnpj-search", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("compliance-basic", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("compliance-basic-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("compliance-complete", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("compliance-complete-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("consulta-ano-modelo", new ConsultaTipoInfo("veiculos", List.of("codigoMarca", "codigoModelo", "codigoTabelaReferencia", "codigoTipoVeiculo"))),
            Map.entry("consulta-cgu", new ConsultaTipoInfo("cpf", List.of("cpf", "tipo-agente"))),
            Map.entry("consulta-chave", new ConsultaTipoInfo("vehicles", List.of("job-id"))),
            Map.entry("consulta-consolidada-de-pessoa-juridica", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("consulta-marca", new ConsultaTipoInfo("veiculos", List.of("codigoTabelaReferencia", "codigoTipoVeiculo"))),
            Map.entry("consulta-modelo", new ConsultaTipoInfo("veiculos", List.of("codigoMarca", "codigoTabelaReferencia", "codigoTipoVeiculo"))),
            Map.entry("consulta-modelos-atraves-do-ano", new ConsultaTipoInfo("veiculos", List.of("ano", "anoModelo", "codigoMarca", "codigoTabelaReferencia", "codigoTipoCombustivel", "codigoTipoVeiculo"))),
            Map.entry("consulta-tabela-referencia", new ConsultaTipoInfo("veiculos", List.of())),
            Map.entry("consulta-valor-com-todos-parametros", new ConsultaTipoInfo("veiculos", List.of("ano", "anoModelo", "codigoMarca", "codigoModelo", "codigoTabelaReferencia", "codigoTipoCombustivel", "codigoTipoVeiculo"))),
            Map.entry("consultar-chave-base-estadual-v3", new ConsultaTipoInfo("vehicles", List.of("job-id"))),
            Map.entry("consultar-chave-cnh-criminals", new ConsultaTipoInfo("cpf", List.of("job-id"))),
            Map.entry("consultar-chave-csv-renainf-renajud-bin-proprietario", new ConsultaTipoInfo("vehicles", List.of("job-id"))),
            Map.entry("consultar-chave-leilao-v2", new ConsultaTipoInfo("vehicles", List.of("job-id"))),
            Map.entry("consultar-chave-veiculos-total", new ConsultaTipoInfo("vehicles", List.of("job-id"))),
            Map.entry("coordinates", new ConsultaTipoInfo("weather-api", List.of("lat", "lon"))),
            Map.entry("cpf-dados", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-hotline", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-impedidos", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-lite", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-obito-grupo-cadastral", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-relatorio", new ConsultaTipoInfo("cpf", List.of("cpf", "extra", "whitelabel"))),
            Map.entry("cpf-search", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-search-mae", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("cpf-sociodemograficos", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("crbm", new ConsultaTipoInfo("crbm", List.of("numero_registro", "regiao"))),
            Map.entry("creditos-simples-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("creditos-simples-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("crlve", new ConsultaTipoInfo("veiculos", List.of("placa", "uf"))),
            Map.entry("crm", new ConsultaTipoInfo("crm", List.of("numero_registro", "uf"))),
            Map.entry("cro", new ConsultaTipoInfo("cro", List.of("categoria", "numero_registro", "uf"))),
            Map.entry("csv-renainf-renajud-bin-proprietario", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("csv-renainf-renajud-bin-proprietario-async", new ConsultaTipoInfo("veiculos", List.of("placa", "webhook_url"))),
            Map.entry("dados-cadastrais", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("dados-processos", new ConsultaTipoInfo("cpf", List.of("cpf", "whitelabel"))),
            Map.entry("ddd-cidade", new ConsultaTipoInfo("ddd-anatel", List.of("city"))),
            Map.entry("debitos-restricoes", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("debitos-v4", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("debitos-v4-async", new ConsultaTipoInfo("vehicles", List.of("placa", "webhook_url"))),
            Map.entry("decodificador-agregados", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("decodificador-precificador", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("define-risco-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("detalhamento-negativo", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("divida-ativa", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("documento-crlv-ac", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-ap", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-ba", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-go", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-ma", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-mg", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-ms", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("documento-crlv-mt", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-pi", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("documento-crlv-ro", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("documento-crlv-rr", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-crlv-to", new ConsultaTipoInfo("veiculos", List.of("cpf", "placa", "renavam"))),
            Map.entry("documento-frota", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("emitentes-atualiza", new ConsultaTipoInfo("emissao-notas", List.of("bairro", "cep", "cmun", "cnae", "cnpj", "crt", "documentos", "email", "emitente_id", "ie", "im", "logo", "municipio", "nome", "numero", "razao", "rua", "suframa", "telefone", "uf", "webhook_url"))),
            Map.entry("emitentes-cadastro", new ConsultaTipoInfo("emissao-notas", List.of("bairro", "cep", "cmun", "cnae", "cnpj", "crt", "documentos", "email", "ie", "im", "logo", "municipio", "nome", "numero", "razao", "rua", "suframa", "telefone", "uf", "webhook_url"))),
            Map.entry("emitentes-lista", new ConsultaTipoInfo("emissao-notas", List.of("emitente"))),
            Map.entry("endereco-telefone-por-placa", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("enriquecimento-de-lead", new ConsultaTipoInfo("cpf", List.of("email"))),
            Map.entry("estadual", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("farol", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("fgts-regularidade-do-empregador", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("ficha-tecnica", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("fipe", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("fipe-chassi", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("geoip", new ConsultaTipoInfo("geoip", List.of("ip"))),
            Map.entry("gravame", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("gravame-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("historico-alteracoes-empresa", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("historico-km", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("historico-proprietario", new ConsultaTipoInfo("vehicles", List.of("cnpj", "placa"))),
            Map.entry("historico-veiculos-pf-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("leilao", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("leilao-completo-score", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("leilao-conjugado", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("leilao-sintetico", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("leilao-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("leilao-v2-async", new ConsultaTipoInfo("veiculos", List.of("placa", "webhook_url"))),
            Map.entry("limite-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("limite-positivo-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("localiza-cnpj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("modelos-carga", new ConsultaTipoInfo("frete-antt", List.of("dataReferencia"))),
            Map.entry("multas", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("multas-prf", new ConsultaTipoInfo("veiculos", List.of("placa", "renavam"))),
            Map.entry("nacional", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("nfse-consulta", new ConsultaTipoInfo("emissao-notas", List.of("chave", "emitente_id"))),
            Map.entry("nfse-envio", new ConsultaTipoInfo("emissao-notas", List.of("dados", "emitente_id"))),
            Map.entry("normativa-atual", new ConsultaTipoInfo("frete-antt", List.of("dataReferencia"))),
            Map.entry("obito", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("opcoes-calculo", new ConsultaTipoInfo("frete-antt", List.of("dataReferencia"))),
            Map.entry("pep-lista-restritiva", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("pessoa-exposta-politicamente", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("pessoa-exposta-politicamente-parentesco", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("pessoa-juridica-protesto", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("proprietario-atual", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("proprietario-atual-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("protesto-nacional-v2", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("protestos-nacional-base", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("protestos-sp", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("quod-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("quod-restricao-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("rastreio", new ConsultaTipoInfo("rastreio", List.of("code"))),
            Map.entry("rating-avancado-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("recall", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("recall-v2", new ConsultaTipoInfo("veiculos", List.of("chassi"))),
            Map.entry("receita-federal", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("receita-federal-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("receita-federal-pf-v3", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("receita-federal-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("receita-federal-pj-v3", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("regularidade-transportadora", new ConsultaTipoInfo("cnpj", List.of("cnpj", "rntrc"))),
            Map.entry("relatorio-positivo", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("relatorio-positivo-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("relatorio-veicular", new ConsultaTipoInfo("veiculos", List.of("extra", "placa", "whitelabel"))),
            Map.entry("relatorio-veicular-completo", new ConsultaTipoInfo("veiculos", List.of("extra", "placa", "whitelabel"))),
            Map.entry("renainf", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("renajud", new ConsultaTipoInfo("veiculos", List.of("documento", "placa", "renavam"))),
            Map.entry("risco-positivo-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("roubo-furto", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("roubo-furto-v2", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("score-credito-quod", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("scpc-net-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("scpc-net-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("scr-analitico-resumo-bacen", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("scr-bacen", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("scr-bacen-score", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("search", new ConsultaTipoInfo("api-rntrc", List.of("filters", "pagination", "sort"))),
            Map.entry("secretaria-da-fazenda-sao-paulo", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("serasa-real-time", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("serasa-score-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("serasa-score-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("simples-nacional", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("sincroniza-normativas", new ConsultaTipoInfo("frete-antt", List.of())),
            Map.entry("sintegra-cadastros-estaduais", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("sintegra-ccc", new ConsultaTipoInfo("cnpj", List.of("cnpj", "uf"))),
            Map.entry("situacao-eleitoral", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("spc-boa-vista", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("spc-serasa", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("spc-terceiros-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("spc-terceiros-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("telefone-operadora", new ConsultaTipoInfo("telefone", List.of("numbers", "options"))),
            Map.entry("transacional-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("transacional-pj", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("var", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("veicular-agrupados", new ConsultaTipoInfo("veiculos", List.of("agrupados", "placa"))),
            Map.entry("veiculos-dados-v1", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("veiculos-documento-pf", new ConsultaTipoInfo("cpf", List.of("cpf"))),
            Map.entry("veiculos-documento-pj", new ConsultaTipoInfo("veiculos", List.of("cnpj"))),
            Map.entry("veiculos-total", new ConsultaTipoInfo("veiculos", List.of("placa"))),
            Map.entry("veiculos-total-async", new ConsultaTipoInfo("veiculos", List.of("placa", "webhook_url"))),
            Map.entry("versoes-normativas", new ConsultaTipoInfo("frete-antt", List.of())),
            Map.entry("vinculo-empregaticio", new ConsultaTipoInfo("cnpj", List.of("cnpj"))),
            Map.entry("vip-car", new ConsultaTipoInfo("veiculos", List.of("placa"))));

    /** Actions documentadas de um serviço (vazio se desconhecido). */
    public static List<String> actionsOf(String service) {
        return SERVICE_ACTIONS.getOrDefault(service, List.of());
    }

    /** Metadados de um tipo de consulta ({@code null} se desconhecido). */
    public static ConsultaTipoInfo consultaTipo(String tipo) {
        return CONSULTA_TIPOS_INFO.get(tipo);
    }
}

package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Saldo, plano, faturas, requisições, notificações e tickets da conta. */
public class AccountService extends BaseService {

    public AccountService(ApiHttpClient http) {
        super(http);
    }

    /** Saldo da conta: {@code GET /balance}. */
    public Map<String, Object> balance() {
        return get("balance");
    }

    /** Plano atual: {@code GET /plan}. */
    public Map<String, Object> plan() {
        return get("plan");
    }

    /** Faturas: {@code GET /invoices}. */
    public Map<String, Object> invoices() {
        return get("invoices");
    }

    /** Notas das faturas: {@code GET /invoices/notes}. */
    public Map<String, Object> invoiceNotes() {
        return get("invoices/notes");
    }

    /** Paga uma fatura: {@code POST /invoices/pay}. */
    public Map<String, Object> payInvoice(Map<String, Object> body) {
        return post("invoices/pay", body);
    }

    /** Requisições da conta: {@code POST /requests}. */
    public Map<String, Object> requests() {
        return post("requests", null);
    }

    public Map<String, Object> requests(Map<String, Object> body) {
        return post("requests", body);
    }

    /** Requisições por API: {@code POST /api/requests}. */
    public Map<String, Object> apiRequests() {
        return post("api/requests", null);
    }

    public Map<String, Object> apiRequests(Map<String, Object> body) {
        return post("api/requests", body);
    }

    /** Jobs em execução: {@code GET /jobs}. */
    public Map<String, Object> jobs() {
        return get("jobs");
    }

    /** Credenciais da conta: {@code GET /credentials}. */
    public Map<String, Object> credentials() {
        return get("credentials");
    }

    /** Indicações: {@code GET /indications}. */
    public Map<String, Object> indications() {
        return get("indications");
    }

    /** Notificações: {@code GET /notifications}. */
    public Map<String, Object> notifications() {
        return get("notifications");
    }

    /** Marca uma notificação como lida: {@code PATCH /notifications/{id}/read}. */
    public Map<String, Object> markNotificationRead(String id) {
        return patch("notifications/" + id + "/read", null);
    }

    /** Marca todas as notificações como lidas: {@code POST /notifications/mark-all-read}. */
    public Map<String, Object> markAllNotificationsRead() {
        return post("notifications/mark-all-read", null);
    }

    /** Tickets de suporte: {@code GET /tickets}. */
    public Map<String, Object> tickets() {
        return get("tickets");
    }

    /** Abre um ticket: {@code POST /ticket}. */
    public Map<String, Object> createTicket(Map<String, Object> body) {
        return post("ticket", body);
    }

    public Map<String, Object> createTicket(Map<String, Object> body, RequestOptions options) {
        return post("ticket", body, options);
    }

    /** Atualiza um ticket: {@code PUT /ticket/{id}}. */
    public Map<String, Object> updateTicket(String id, Map<String, Object> body) {
        return put("ticket/" + id, body);
    }

    /** Mensagens do ticket: {@code GET /ticket/{id}/messages}. */
    public Map<String, Object> ticketMessages(String id) {
        return get("ticket/" + id + "/messages");
    }

    /** Responde um ticket: {@code POST /ticket/{id}/messages}. */
    public Map<String, Object> addTicketMessage(String id, Map<String, Object> body) {
        return post("ticket/" + id + "/messages", body);
    }
}

package com.apibrasil.sdk.services;

import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.helpers.ApiTestCase;
import com.apibrasil.sdk.helpers.FakeTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Serviços de plataforma")
class PlatformTest extends ApiTestCase {

    // -------------------------------------------------------------------- auth

    @Test
    @DisplayName("Auth: login guarda o Bearer Token")
    void loginStoresToken() {
        transport.respondWith(FakeTransport.ok(Map.of(
                "authorization", Map.of("token", "novo-jwt"))));

        api.auth.login(Json.of("email", "a@b.com", "password", "123"));

        assertCall("POST", "/auth/login");
        assertEquals("novo-jwt", api.http.bearerToken());
    }

    @Test
    @DisplayName("Auth: fluxo 2FA")
    void twoFactor() {
        transport.respondWith(FakeTransport.ok(Map.of("requires_2fa", true, "challenge", "xyz")));
        api.auth.login(Json.of("email", "a@b.com", "password", "123"));
        assertCall("POST", "/auth/login");

        api.auth.send2fa(Json.of("challenge", "xyz", "method", "email"));
        assertCall("POST", "/auth/2fa/send");

        transport.respondWith(FakeTransport.ok(Map.of("authorization", Map.of("token", "jwt-2fa"))));
        api.auth.verify2fa(Json.of("challenge", "xyz", "code", "000000"));
        assertCall("POST", "/auth/login/verify-2fa");
        assertEquals("jwt-2fa", api.http.bearerToken());

        api.auth.twoFactorMethods();
        assertCall("GET", "/auth/2fa/methods");
    }

    @Test
    @DisplayName("Auth: cadastro, senha e perfil")
    void authRoutes() {
        api.auth.register(Json.of("email", "a@b.com"));
        assertCall("POST", "/auth/register");

        api.auth.registerSimple(Json.of("email", "a@b.com"));
        assertCall("POST", "/auth/register/simple");

        api.auth.verificationSend(Json.of("type", "email"));
        assertCall("POST", "/auth/verification/send");

        api.auth.verificationVerify(Json.of("code", "123456"));
        assertCall("POST", "/auth/verification/verify");

        api.auth.passwordForgot(Json.of("email", "a@b.com"));
        assertCall("POST", "/auth/password/forgot");

        api.auth.passwordVerifyCode(Json.of("code", "123456"));
        assertCall("POST", "/auth/password/verify-code");

        api.auth.passwordReset(Json.of("password", "nova"));
        assertCall("POST", "/auth/password/reset");

        api.auth.passwordResend(Json.of("email", "a@b.com"));
        assertCall("POST", "/auth/password/resend");

        api.auth.changePassword(Json.of("current_password", "a", "password", "b"));
        assertCall("POST", "/password/change");

        api.auth.profile();
        assertCall("POST", "/profile");

        api.auth.me();
        assertCall("GET", "/profile/me");

        api.auth.updateMe(Json.of("first_name", "Jhon"));
        assertCall("PUT", "/profile/me");

        api.auth.verify();
        assertCall("GET", "/auth/verify");

        api.auth.tokenRotate();
        assertCall("POST", "/auth/token/rotate");

        api.auth.tokenRevoke();
        assertCall("POST", "/auth/token/revoke");
    }

    @Test
    @DisplayName("Auth: refresh troca o token e logout limpa")
    void refreshAndLogout() {
        transport.respondWith(FakeTransport.ok(Map.of("token", "jwt-renovado")));
        api.auth.refresh();
        assertCall("POST", "/refresh");
        assertEquals("jwt-renovado", api.http.bearerToken());

        api.auth.logout();
        assertCall("POST", "/auth/logout");
        assertNull(api.http.bearerToken());
    }

    // ----------------------------------------------------------------- devices

    @Test
    @DisplayName("Devices: CRUD")
    void devices() {
        api.devices.list();
        assertCall("GET", "/devices");

        api.devices.store(Json.of("device_name", "meu-bot", "type", "server"),
                RequestOptions.secretKey("minha-secret"));
        assertCall("POST", "/devices/store");
        assertEquals("minha-secret", transport.lastHeaders().get("SecretKey"));

        api.devices.show();
        assertCall("GET", "/devices/show?search=device-de-teste");

        api.devices.show("outro-device");
        assertCall("GET", "/devices/show?search=outro-device");

        api.devices.update(Json.of("device_token", "abc", "device_name", "novo"));
        assertCall("POST", "/devices/update");

        api.devices.destroy("abc");
        assertCall("DELETE", "/devices/destroy");
        assertEquals("abc", transport.lastBody().get("search"));

        api.devices.requests();
        assertCall("POST", "/devices/requests");
    }

    // ----------------------------------------------------------------- account

    @Test
    @DisplayName("Account: saldo, faturas, notificações e tickets")
    void account() {
        api.account.balance();
        assertCall("GET", "/balance");

        api.account.plan();
        assertCall("GET", "/plan");

        api.account.invoices();
        assertCall("GET", "/invoices");

        api.account.invoiceNotes();
        assertCall("GET", "/invoices/notes");

        api.account.payInvoice(Json.of("invoice_id", 1));
        assertCall("POST", "/invoices/pay");

        api.account.requests();
        assertCall("POST", "/requests");

        api.account.apiRequests();
        assertCall("POST", "/api/requests");

        api.account.jobs();
        assertCall("GET", "/jobs");

        api.account.credentials();
        assertCall("GET", "/credentials");

        api.account.indications();
        assertCall("GET", "/indications");

        api.account.notifications();
        assertCall("GET", "/notifications");

        api.account.markNotificationRead("7");
        assertCall("PATCH", "/notifications/7/read");

        api.account.markAllNotificationsRead();
        assertCall("POST", "/notifications/mark-all-read");

        api.account.tickets();
        assertCall("GET", "/tickets");

        api.account.createTicket(Json.of("subject", "ajuda"));
        assertCall("POST", "/ticket");

        api.account.updateTicket("7", Json.of("status", "closed"));
        assertCall("PUT", "/ticket/7");

        api.account.ticketMessages("7");
        assertCall("GET", "/ticket/7/messages");

        api.account.addTicketMessage("7", Json.of("message", "obrigado"));
        assertCall("POST", "/ticket/7/messages");
    }

    // ---------------------------------------------------------------- payments

    @Test
    @DisplayName("Payments: recargas, PIX, boleto e cartão")
    void payments() {
        api.payments.recharges();
        assertCall("GET", "/recharges");

        api.payments.recharge(Json.of("amount", 50));
        assertCall("POST", "/recharge");

        api.payments.rechargeShow("rec-1");
        assertCall("GET", "/recharge/rec-1");

        api.payments.pixGenerate("santander", Json.of("amount", 50));
        assertCall("POST", "/santander/pix/generate");

        api.payments.pixStatus("santander", "tx-1");
        assertCall("GET", "/santander/pix/tx-1");

        api.payments.boletoGenerate("inter", Json.of("amount", 50));
        assertCall("POST", "/inter/boleto/generate");

        api.payments.boletoStatus("inter", "bol-1");
        assertCall("GET", "/inter/boleto/bol-1");

        api.payments.cardProcess(Json.of("token", "card-token"));
        assertCall("POST", "/mercadopago/card/process");

        api.payments.cardInstallments(Json.of("amount", 50));
        assertCall("POST", "/mercadopago/card/installments");

        api.payments.cardStatus("pay-1");
        assertCall("GET", "/mercadopago/card/pay-1");

        api.payments.checkoutPaymentMethods();
        assertCall("GET", "/checkout/payment-methods");

        api.payments.checkoutPeriods();
        assertCall("GET", "/checkout/periods");

        api.payments.validateCoupon(Json.of("coupon", "APIBRASIL"));
        assertCall("POST", "/checkout/validate-coupon");

        api.payments.checkoutFinalize(Json.of("plan", "pro"));
        assertCall("POST", "/checkout/finalize");
    }

    // ----------------------------------------------------------------- catalog

    @Test
    @DisplayName("Catalog: APIs, planos, docs e servidores")
    void catalog() {
        api.catalog.apis();
        assertCall("GET", "/apis");

        api.catalog.apis("whatsapp");
        assertCall("GET", "/apis?search=whatsapp");

        api.catalog.api("42");
        assertCall("GET", "/apis/42");

        api.catalog.apiByName("WhatsApp API");
        assertCall("GET", "/apis/name/WhatsApp+API");

        api.catalog.apiCategories();
        assertCall("GET", "/apis/categories");

        api.catalog.myApis();
        assertCall("GET", "/apis/list");

        api.catalog.apisByDevice("dev-1");
        assertCall("GET", "/apis/device/dev-1");

        api.catalog.plans();
        assertCall("GET", "/plans");

        api.catalog.documentations();
        assertCall("GET", "/documentations");

        api.catalog.documentationsByServer("srv-1");
        assertCall("GET", "/documentations/server/srv-1");

        api.catalog.servers();
        assertCall("GET", "/servers");

        api.catalog.endpointUrl(Json.of("endpoint_id", 1));
        assertCall("POST", "/endpoint/url");

        api.catalog.endpointBody(Json.of("endpoint_id", 1));
        assertCall("POST", "/endpoint/body");

        api.catalog.status();
        assertCall("GET", "/status");
    }

    // ------------------------------------------------------- segurança e relatórios

    @Test
    @DisplayName("IP whitelist")
    void ipWhitelist() {
        api.ipWhitelist.list();
        assertCall("GET", "/ip-whitelist");

        api.ipWhitelist.get();
        assertCall("GET", "/ip-whitelist");

        api.ipWhitelist.set(List.of("1.2.3.4", "10.0.0.0/8"));
        assertCall("PUT", "/ip-whitelist");
        assertEquals(List.of("1.2.3.4", "10.0.0.0/8"), transport.lastBody().get("ip_whitelist"));

        api.ipWhitelist.add("1.2.3.4");
        assertCall("POST", "/ip-whitelist/add");

        api.ipWhitelist.remove("1.2.3.4");
        assertCall("DELETE", "/ip-whitelist/remove");
        assertEquals("1.2.3.4", transport.lastBody().get("entry"));

        api.ipWhitelist.addCurrent();
        assertCall("POST", "/ip-whitelist/add-current");

        api.ipWhitelist.reset();
        assertCall("POST", "/ip-whitelist/reset");

        api.ipWhitelist.validate("1.2.3.4");
        assertCall("POST", "/ip-whitelist/validate");

        api.ipWhitelist.currentIp();
        assertCall("GET", "/ip-whitelist/current-ip");
    }

    @Test
    @DisplayName("Rate limit por Bearer Token")
    void bearerRateLimit() {
        api.bearerRateLimit.get();
        assertCall("GET", "/bearer-rate-limit");

        api.bearerRateLimit.set(Json.of("limit", 100));
        assertCall("PUT", "/bearer-rate-limit");
    }

    @Test
    @DisplayName("Relatórios")
    void reports() {
        api.reports.dashboardStats();
        assertCall("GET", "/dashboard/stats");

        api.reports.consumption();
        assertCall("GET", "/reports/consumption");

        api.reports.generateConsumptionReport();
        assertCall("POST", "/reports/generate-consumption-report");

        api.reports.extract();
        assertCall("GET", "/reports/extract");

        api.reports.dashboard();
        assertCall("GET", "/reports/dashboard");

        api.reports.summary();
        assertCall("GET", "/reports/summary");

        api.reports.dailyUsage();
        assertCall("GET", "/reports/daily-usage");

        api.reports.monthlySummary();
        assertCall("GET", "/reports/monthly-summary");

        api.reports.errorAnalysis();
        assertCall("GET", "/reports/error-analysis");

        api.reports.deviceAnalysis();
        assertCall("GET", "/reports/device-analysis");

        api.reports.recentRequests();
        assertCall("GET", "/reports/recent-requests");

        api.reports.quickStats();
        assertCall("GET", "/reports/quick-stats");
    }
}

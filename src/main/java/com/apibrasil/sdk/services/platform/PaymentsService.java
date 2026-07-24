package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.HttpMethod;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/**
 * Recargas e pagamentos — PIX, boleto e cartão
 * (Santander, Inter, Mercado Pago, Sicoob).
 *
 * <p>O {@code provider} das rotas PIX/boleto é o banco emissor
 * (ex: {@code santander}, {@code inter}, {@code sicoob}).
 */
public class PaymentsService extends BaseService {

    public PaymentsService(ApiHttpClient http) {
        super(http);
    }

    /** Histórico de recargas: {@code GET /recharges}. */
    public Map<String, Object> recharges() {
        return get("recharges");
    }

    /** Cria uma recarga: {@code POST /recharge}. */
    public Map<String, Object> recharge(Map<String, Object> body) {
        return post("recharge", body);
    }

    /** Detalha uma recarga: {@code GET /recharge/{identifier}}. */
    public Map<String, Object> rechargeShow(String identifier) {
        return get("recharge/" + identifier);
    }

    /** Gera cobrança PIX: {@code POST /{provider}/pix/generate}. */
    public Map<String, Object> pixGenerate(String provider, Map<String, Object> body) {
        return post(provider + "/pix/generate", body);
    }

    public Map<String, Object> pixGenerate(String provider, Map<String, Object> body, RequestOptions options) {
        return post(provider + "/pix/generate", body, options);
    }

    /** Consulta cobrança PIX: {@code GET /{provider}/pix/{txId}}. */
    public Map<String, Object> pixStatus(String provider, String txId) {
        return get(provider + "/pix/" + txId);
    }

    /** Gera boleto: {@code POST /{provider}/boleto/generate}. */
    public Map<String, Object> boletoGenerate(String provider, Map<String, Object> body) {
        return post(provider + "/boleto/generate", body);
    }

    /** Consulta boleto: {@code GET /{provider}/boleto/{id}}. */
    public Map<String, Object> boletoStatus(String provider, String id) {
        return get(provider + "/boleto/" + id);
    }

    /** Baixa o PDF do boleto: {@code GET /{provider}/boleto/{id}/pdf}. */
    public byte[] boletoPdf(String provider, String id) {
        return boletoPdf(provider, id, RequestOptions.NONE);
    }

    public byte[] boletoPdf(String provider, String id, RequestOptions options) {
        return http.bytes(HttpMethod.GET, provider + "/boleto/" + id + "/pdf", null, options);
    }

    /** Processa pagamento com cartão: {@code POST /mercadopago/card/process}. */
    public Map<String, Object> cardProcess(Map<String, Object> body) {
        return post("mercadopago/card/process", body);
    }

    /** Simula parcelas: {@code POST /mercadopago/card/installments}. */
    public Map<String, Object> cardInstallments(Map<String, Object> body) {
        return post("mercadopago/card/installments", body);
    }

    /** Status do pagamento com cartão: {@code GET /mercadopago/card/{id}}. */
    public Map<String, Object> cardStatus(String id) {
        return get("mercadopago/card/" + id);
    }

    /** Métodos de pagamento do checkout: {@code GET /checkout/payment-methods}. */
    public Map<String, Object> checkoutPaymentMethods() {
        return get("checkout/payment-methods");
    }

    /** Períodos do checkout: {@code GET /checkout/periods}. */
    public Map<String, Object> checkoutPeriods() {
        return get("checkout/periods");
    }

    /** Valida um cupom: {@code POST /checkout/validate-coupon}. */
    public Map<String, Object> validateCoupon(Map<String, Object> body) {
        return post("checkout/validate-coupon", body);
    }

    /** Finaliza o checkout: {@code POST /checkout/finalize}. */
    public Map<String, Object> checkoutFinalize(Map<String, Object> body) {
        return post("checkout/finalize", body);
    }
}

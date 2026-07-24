package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/**
 * Autenticação e conta ({@code /auth/*}, {@code /profile*}, {@code /password/*}).
 *
 * <p>{@link #login(Map)} e {@link #verify2fa(Map)} guardam automaticamente o
 * Bearer Token retornado no cliente, deixando as próximas chamadas
 * autenticadas.
 */
public class AuthService extends BaseService {

    public AuthService(ApiHttpClient http) {
        super(http);
    }

    /**
     * Autentica com email/senha: {@code POST /auth/login}.
     * Se a conta tiver 2FA, retorna {@code {"requires_2fa": true, "challenge": ...}} —
     * use {@link #send2fa(Map)} + {@link #verify2fa(Map)} para concluir.
     */
    public Map<String, Object> login(Map<String, Object> body) {
        return login(body, RequestOptions.NONE);
    }

    public Map<String, Object> login(Map<String, Object> body, RequestOptions options) {
        Map<String, Object> response = post("auth/login", body, options);
        storeToken(response);
        return response;
    }

    /** Envia o código 2FA pelo método escolhido: {@code POST /auth/2fa/send}. */
    public Map<String, Object> send2fa(Map<String, Object> body) {
        return post("auth/2fa/send", body);
    }

    /** Conclui o login com o código 2FA: {@code POST /auth/login/verify-2fa}. */
    public Map<String, Object> verify2fa(Map<String, Object> body) {
        return verify2fa(body, RequestOptions.NONE);
    }

    public Map<String, Object> verify2fa(Map<String, Object> body, RequestOptions options) {
        Map<String, Object> response = post("auth/login/verify-2fa", body, options);
        storeToken(response);
        return response;
    }

    /** Lista os métodos 2FA ativos da conta: {@code GET /auth/2fa/methods}. */
    public Map<String, Object> twoFactorMethods() {
        return get("auth/2fa/methods");
    }

    /** Cria uma conta: {@code POST /auth/register}. */
    public Map<String, Object> register(Map<String, Object> body) {
        return post("auth/register", body);
    }

    /** Cadastro simplificado: {@code POST /auth/register/simple}. */
    public Map<String, Object> registerSimple(Map<String, Object> body) {
        return post("auth/register/simple", body);
    }

    /** Dispara verificação de email/celular: {@code POST /auth/verification/send}. */
    public Map<String, Object> verificationSend(Map<String, Object> body) {
        return post("auth/verification/send", body);
    }

    /** Confirma o código de verificação: {@code POST /auth/verification/verify}. */
    public Map<String, Object> verificationVerify(Map<String, Object> body) {
        return post("auth/verification/verify", body);
    }

    /** Esqueci a senha: {@code POST /auth/password/forgot}. */
    public Map<String, Object> passwordForgot(Map<String, Object> body) {
        return post("auth/password/forgot", body);
    }

    /** Valida o código de recuperação: {@code POST /auth/password/verify-code}. */
    public Map<String, Object> passwordVerifyCode(Map<String, Object> body) {
        return post("auth/password/verify-code", body);
    }

    /** Redefine a senha: {@code POST /auth/password/reset}. */
    public Map<String, Object> passwordReset(Map<String, Object> body) {
        return post("auth/password/reset", body);
    }

    /** Reenvia o código de recuperação: {@code POST /auth/password/resend}. */
    public Map<String, Object> passwordResend(Map<String, Object> body) {
        return post("auth/password/resend", body);
    }

    /** Troca a senha logado: {@code POST /password/change}. */
    public Map<String, Object> changePassword(Map<String, Object> body) {
        return post("password/change", body);
    }

    /** Perfil completo (com estatísticas): {@code POST /profile}. */
    public Map<String, Object> profile() {
        return post("profile", null);
    }

    /** Perfil atual: {@code GET /profile/me}. */
    public Map<String, Object> me() {
        return get("profile/me");
    }

    /** Atualiza o perfil: {@code PUT /profile/me}. */
    public Map<String, Object> updateMe(Map<String, Object> body) {
        return put("profile/me", body);
    }

    /** Valida o token atual: {@code GET /auth/verify}. */
    public Map<String, Object> verify() {
        return get("auth/verify");
    }

    /** Renova o JWT: {@code POST /refresh}. */
    public Map<String, Object> refresh() {
        Map<String, Object> response = post("refresh", null);

        String token = null;
        Map<String, Object> authorization = Json.object(response, "authorization");
        if (authorization != null) {
            token = Json.string(authorization, "token");
        }
        if (token == null) {
            token = Json.string(response, "token");
        }
        if (token != null && !token.isBlank()) {
            http.setBearerToken(token);
        }
        return response;
    }

    /** Rotaciona o token: {@code POST /auth/token/rotate}. */
    public Map<String, Object> tokenRotate() {
        return post("auth/token/rotate", null);
    }

    /** Revoga o token atual: {@code POST /auth/token/revoke}. */
    public Map<String, Object> tokenRevoke() {
        return post("auth/token/revoke", null);
    }

    /** Encerra a sessão: {@code POST /auth/logout}. */
    public Map<String, Object> logout() {
        Map<String, Object> response = post("auth/logout", null);
        http.setBearerToken(null);
        return response;
    }

    /** Guarda o Bearer Token quando a resposta trouxer {@code authorization.token}. */
    private void storeToken(Map<String, Object> response) {
        Map<String, Object> authorization = Json.object(response, "authorization");
        if (authorization == null) {
            return;
        }
        String token = Json.string(authorization, "token");
        if (token != null && !token.isBlank()) {
            http.setBearerToken(token);
        }
    }
}

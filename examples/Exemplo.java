import com.apibrasil.sdk.ApiBrasil;
import com.apibrasil.sdk.core.CreditResponse;
import com.apibrasil.sdk.core.DeviceResponse;
import com.apibrasil.sdk.core.Hooks;
import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.RequestOptions;
import com.apibrasil.sdk.core.errors.InsufficientBalanceException;
import com.apibrasil.sdk.core.errors.RateLimitException;

import java.time.Duration;
import java.util.Map;

/**
 * Exemplo de uso da SDK. Rode com as credenciais no ambiente:
 *
 * <pre>{@code
 * export APIBRASIL_BEARER_TOKEN=...
 * export APIBRASIL_DEVICE_TOKEN=...
 * }</pre>
 */
public final class Exemplo {

    public static void main(String[] args) {
        // Credenciais vêm do ambiente quando não informadas aqui.
        try (ApiBrasil api = ApiBrasil.builder()
                .timeout(Duration.ofSeconds(30))
                .hooks(Hooks.builder()
                        .onRequest(info -> System.out.printf("→ %s %s%n", info.method(), info.url()))
                        .onRetry(info -> System.out.printf("retry em %s: %s%n", info.delay(), info.reason()))
                        .build())
                .build()) {

            // ---------------------------------------------------------- WhatsApp
            api.whatsapp.start();

            Map<String, Object> qr = api.whatsapp.qrcode();
            System.out.println("QR Code: " + Json.object(qr, "response"));

            DeviceResponse enviado = DeviceResponse.of(
                    api.whatsapp.sendText(Json.of("number", "5511999999999", "text", "Olá! 👋")));
            System.out.println("WhatsApp: " + enviado.message());

            // Envio assíncrono (fila) em outro device
            api.whatsapp.sendText(
                    Json.of("number", "5511988888888", "text", "Mensagem do outro device"),
                    RequestOptions.deviceToken("OUTRO_DEVICE_TOKEN"));

            // ---------------------------------------------------------- Consultas
            try {
                CreditResponse cnpj = CreditResponse.of(
                        api.consulta.cnpj(Json.of("cnpj", "00000000000000")));
                System.out.println("Saldo restante: " + cnpj.balance());
                System.out.println("Empresa: " + cnpj.data());
            } catch (InsufficientBalanceException error) {
                System.out.println("Recarregue seus créditos.");
            } catch (RateLimitException error) {
                System.out.println("Rate limit — aguarde " + error.retryAfter());
            }

            // Homologação: não cobra créditos
            api.consulta.cpf(Json.of("cpf", "00000000000", "homolog", true));

            // ------------------------------------------------------------ Dados
            System.out.println(api.cep.cep(Json.of("cep", "01001000")));
            System.out.println(api.vehicles.dados(Json.of("placa", "ABC1234")));
            System.out.println(api.correios.rastreio(Json.of("code", "AA123456789BR")));

            // ----------------------------------------------------------- Conta
            System.out.println("Saldo: " + api.account.balance());
            System.out.println("Devices: " + api.devices.list());

            // ------------------------------------- Endpoint sem método dedicado
            System.out.println(api.requestJson("GET", "/reports/quick-stats", null));
        }
    }

    private Exemplo() {
    }
}

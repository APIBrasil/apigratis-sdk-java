package e2e.login;

import com.apibrasil.sdk.client.ApiClientImpl;
import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.login.LoginClient;
import com.apibrasil.sdk.dto.login.LoginReq;
import com.apibrasil.sdk.dto.login.LoginRes;
import com.apibrasil.sdk.exception.ApiException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginClientE2ETest {
    @Test
    void testLoginE2E() {
        ApiClient realApiClient = new ApiClientImpl();
        LoginClient loginClient = new LoginClient(realApiClient);
        LoginReq request = new LoginReq();
        request.setEmail("seuemail@exemplo.com");
        request.setPassword("suasenha");

        try {
            LoginRes response = loginClient.login(request);
            assertNotNull(response);
            assertNotNull(response.getAuthorization().getToken());
        } catch (ApiException e) {
            e.printStackTrace();
            fail("Falha na chamada da API");
        }
    }
}

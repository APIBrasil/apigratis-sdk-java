package unit.login;

import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.client.login.LoginClient;
import com.apibrasil.sdk.dto.base.authorization.Authorization;
import com.apibrasil.sdk.dto.login.LoginReq;
import com.apibrasil.sdk.dto.login.LoginRes;
import com.apibrasil.sdk.exception.ApiException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class LoginClientTest {
    @Test
    void testLoginSuccess() throws ApiException {
        ApiClient mockApiClient = mock(ApiClient.class);
        LoginClient loginClient = new LoginClient(mockApiClient);
        LoginReq mockRequest = new LoginReq();
        LoginRes mockResponse = new LoginRes();
        Authorization authorization = new Authorization();
        authorization.setToken("fakeToken");
        mockResponse.setAuthorization(authorization);

        when(mockApiClient.executeApiCall(any(HttpPost.class), eq(LoginRes.class)))
                .thenReturn(mockResponse);

        LoginRes response = loginClient.login(mockRequest);

        assertNotNull(response);
        assertEquals("fakeToken", response.getAuthorization().getToken());
    }
}

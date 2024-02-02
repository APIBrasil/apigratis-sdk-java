package com.apibrasil.sdk.client.login;


import com.apibrasil.sdk.client.base.ApiClient;
import com.apibrasil.sdk.dto.base.authorization.Authorization;
import com.apibrasil.sdk.dto.base.user.User;
import com.apibrasil.sdk.dto.login.LoginReq;
import com.apibrasil.sdk.dto.login.LoginRes;
import com.apibrasil.sdk.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LoginClientTest {

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private LoginClient loginClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginClient = new LoginClient(apiClient);
    }

    @Test
    void testLogin() throws ApiException {
        LoginRes loginResponseMock = loginResponseMock();
        when(apiClient.executeApiCall(any(), any())).thenReturn(loginResponseMock());
        LoginRes responseMethod = loginClient.login(loginRequestMock());
        assertEquals(loginResponseMock.getMessage(), responseMethod.getMessage());
        assertEquals(loginResponseMock.getUser(), responseMethod.getUser());
        assertEquals(loginResponseMock.getAuthorization(), responseMethod.getAuthorization());
        assertEquals(loginResponseMock.getInvoices(), responseMethod.getInvoices());
    }

    @Test
    void testLoginThrowsApiException() throws ApiException {
        when(apiClient.executeApiCall(any(), any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> loginClient.login(loginRequestMock()));
    }

    private LoginReq loginRequestMock() {
        LoginReq loginRequestMock = new LoginReq();
        loginRequestMock.setEmail("seuemail@exemplo.com");
        loginRequestMock.setPassword("suasenha");
        return loginRequestMock;
    }

    private LoginRes loginResponseMock() {
        LoginRes loginResponseMock = new LoginRes();
        loginResponseMock.setError(false);
        loginResponseMock.setInvoices(List.of());
        loginResponseMock.setMessage("Success");
        loginResponseMock.setUser(userMock());
        loginResponseMock.setAuthorization(authorizationMock());
        return loginResponseMock;
    }

    private User userMock() {
        User userMock = new User();
        userMock.setCellphone("5511999999999");
        userMock.setDevices(List.of());
        userMock.setEmail("seuemail@exemplo.com");
        userMock.setLastLoginAt("2021-08-25T14:00:00Z");
        userMock.setDevicesCount(1);
        userMock.setLastLoginIp("192.168.1.10");
        userMock.setFirstName("Teste");
        userMock.setLastName("Testado");
        userMock.setInvoicesOpenCount(0);
        userMock.setSearch("teste");
        userMock.setStatus("active");
        userMock.setStatusFinance("active");
        userMock.setUpdatedAt("2021-08-25T14:00:00Z");
        return userMock;
    }

    private Authorization authorizationMock() {
        Authorization authorizationMock = new Authorization();
        authorizationMock.setExpiresIn(3600);
        authorizationMock.setToken("fakeToken");
        authorizationMock.setType("Bearer");
        return authorizationMock;
    }

}

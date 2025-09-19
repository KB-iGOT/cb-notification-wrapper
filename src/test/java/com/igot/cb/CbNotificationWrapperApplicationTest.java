package com.igot.cb;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestFactory;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class CbNotificationWrapperApplicationTest {

    @Test
    void testMainInvokesSpringApplicationRun() {
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            CbNotificationWrapperApplication.main(new String[]{"arg1", "arg2"});
            mocked.verify(() -> SpringApplication.run(
                    eq(CbNotificationWrapperApplication.class),
                    eq(new String[]{"arg1", "arg2"})
            ));
        }
    }

    @Test
    void testRestTemplateBeanNotNull() {
        CbNotificationWrapperApplication app = new CbNotificationWrapperApplication();
        RestTemplate restTemplate = app.restTemplate();
        assertNotNull(restTemplate);
        assertNotNull(restTemplate.getRequestFactory());
    }

    @Test
    void testGetClientHttpRequestFactoryPrivateMethod() throws Exception {
        CbNotificationWrapperApplication app = new CbNotificationWrapperApplication();
        Method method = CbNotificationWrapperApplication.class
                .getDeclaredMethod("getClientHttpRequestFactory");
        method.setAccessible(true);

        Object factory = method.invoke(app);
        assertNotNull(factory);
        assertTrue(factory instanceof ClientHttpRequestFactory);
    }
}

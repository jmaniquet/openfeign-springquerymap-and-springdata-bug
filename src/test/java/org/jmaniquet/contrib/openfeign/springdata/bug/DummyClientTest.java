package org.jmaniquet.contrib.openfeign.springdata.bug;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.function.Consumer;

import org.jmaniquet.contrib.openfeign.springdata.bug.DummyClientTest.DummyClientTestConfig;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.RetryableException;

@SpringBootTest(classes = DummyClientTestConfig.class)
class DummyClientTest {

    @Autowired
    private DummyClient underTest;

    private static final String WHATEVER1 = "whatever1";
    private static final String WHATEVER2 = "whatever2";

    @Nested
    class WithLombokRequestTest {

        @Test
        void fooBar_ShouldHave_Dash_InUrl() {
            assertThatExceptionOfType(RetryableException.class)
                .isThrownBy(() -> underTest.findByCriteria(request()))
                .satisfies(
                        hasRequestParam("fizzBuzz=whatever1"),
                        hasRequestParam("foo-bar=whatever2")
                );
        }

        private static DummyRequestWithLombok request() {
            DummyRequestWithLombok request = new DummyRequestWithLombok();
            request.setFizzBuzz(WHATEVER1);
            request.setFooBar(WHATEVER2);
            return request;
        }
    }

    @Nested
    class NoLombokRequestTest {

        @Test
        void fooBar_ShouldHave_Dash_InUrl() {
            assertThatExceptionOfType(RetryableException.class)
                .isThrownBy(() -> underTest.findByCriteria(request()))
                .satisfies(
                        hasRequestParam("fizzBuzz=whatever1"),
                        hasRequestParam("foo-bar=whatever2")
                );
        }

        private static DummyRequestNoLombok request() {
            DummyRequestNoLombok request = new DummyRequestNoLombok();
            request.setFizzBuzz(WHATEVER1);
            request.setFooBar(WHATEVER2);
            return request;
        }
    }

    private static Consumer<RetryableException> hasRequestParam(String expectedRequestParam) {
        return ex -> {
            Request request = ex.request();
            String url = request.url();
            assertThat(url).contains(expectedRequestParam);
        };
    }

    @Configuration
    @EnableFeignClients(clients = DummyClient.class)
    @ImportAutoConfiguration({ JacksonAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class, FeignAutoConfiguration.class })
    static class DummyClientTestConfig {}
}

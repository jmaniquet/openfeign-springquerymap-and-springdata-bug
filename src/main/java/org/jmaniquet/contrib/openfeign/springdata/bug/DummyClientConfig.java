package org.jmaniquet.contrib.openfeign.springdata.bug;

import org.springframework.context.annotation.Bean;

import feign.Logger;

public class DummyClientConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

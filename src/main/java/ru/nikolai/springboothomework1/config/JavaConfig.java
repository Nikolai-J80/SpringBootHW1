package ru.nikolai.springboothomework1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nikolai.springboothomework1.service.DevProfile;
import ru.nikolai.springboothomework1.service.ProductionProfile;
import ru.nikolai.springboothomework1.service.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(value = "nikolai.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "nikolai.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}

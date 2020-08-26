package pl.dmuszynski.aquashop.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@Configuration
@EnableScheduling
@EnableJpaAuditing
public class ResourceConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

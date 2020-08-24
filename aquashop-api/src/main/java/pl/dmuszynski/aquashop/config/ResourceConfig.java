package pl.dmuszynski.aquashop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@Configuration
public class ResourceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

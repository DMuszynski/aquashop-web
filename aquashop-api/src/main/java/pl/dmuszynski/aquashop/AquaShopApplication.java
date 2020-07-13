package pl.dmuszynski.aquashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AquaShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AquaShopApplication.class, args);
    }
}

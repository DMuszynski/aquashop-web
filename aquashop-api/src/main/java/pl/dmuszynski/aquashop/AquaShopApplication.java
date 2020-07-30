package pl.dmuszynski.aquashop;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@EnableJpaAuditing
@SpringBootApplication
public class AquaShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AquaShopApplication.class, args);
    }
}

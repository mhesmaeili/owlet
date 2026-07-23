package com.owlet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"com.owlet.common", "com.owlet.api"})
@EntityScan(basePackages = {
        "com.owlet.api.domain"
})
public class OwletApplication {

    public static void main(String[] args) {
        /*SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);

        String secret = Base64.getEncoder()
                .encodeToString(bytes);

        System.out.println(secret);*/
        SpringApplication.run(OwletApplication.class, args);
    }
}

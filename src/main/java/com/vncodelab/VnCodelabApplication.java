package com.vncodelab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.vncodelab.respository.LabRespository;

@SpringBootApplication
public class VnCodelabApplication extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(VnCodelabApplication.class);


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VnCodelabApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(LabRespository repository) {
        return (args) -> {
//
//           boolean a = repository.existsByDocID("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0");
//            System.out.println(a);

        };
    }
}

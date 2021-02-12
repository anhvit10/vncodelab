package com.vncodelab;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VnCodelabApplication extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(VnCodelabApplication.class);


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VnCodelabApplication.class, args);





    }


//    @Bean
//    public CommandLineRunner demo(LabRespository repository) {
//        return (args) -> {
//            // save a few customers
//            repository.save(new Lab("123", "Bauer"));
//            repository.save(new Lab("456", "O'Brian"));
//
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Lab customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Lab lab = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(lab.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
//        };
//    }
}

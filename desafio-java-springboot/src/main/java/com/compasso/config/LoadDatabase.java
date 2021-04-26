package com.compasso.config;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.compasso.entity.*;
import com.compasso.interfaces.*;

@Configuration
public class LoadDatabase { 
 
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
  
      return args -> {
        log.info("Preloading " + repository.save(new Product("Cobertor","Cobertor para casal",49.7)));
        log.info("Preloading " + repository.save(new Product("Colchão", "King-size",679.99)));
        log.info("Preloading " + repository.save(new Product("Edredom", "Edredom de casal", 359.99))); 
        log.info("Preloading " + repository.save(new Product("Lenço", "Lençou de poliester de casal", 123.99)));
        log.info("Preloading " + repository.save(new Product("Toalha de banho", "Toalha trinity", 45.00)));
        log.info("Preloading " + repository.save(new Product("Toalha de mesa", "Toalha de mesa retangular", 50.00)));
        log.info("Preloading " + repository.save(new Product("Travesseiro", "Traveseiro ant-ácaro", 90.99)));
      };
    }
  }
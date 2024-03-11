package com.calmcodecollective.roommatematcher.model.roommate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("bilbo_baggins", "Bilbo",  "Baggins")));
            log.info("Preloading " + repository.save(new Account("frodo_baggins", "Frodo", "Baggins")));
        };
    }
}

package com.soulrebel.ml;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Clase que inicia la aplicación.
 *
 * @author Jonathan Hinestroza
 */
@SpringBootApplication
@Slf4j
public class MutantsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutantsApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting...");
        SpringApplication.run(MutantsApplication.class, args);
    }

}

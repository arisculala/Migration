package com.demo.esmigration.rest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.esmigration.service.EsMigrationService;

/**
 * REST controller for managing iconverse ES data migration from 2.XX to 6.XX
 */
@RestController
@RequestMapping("/api")
public class EsMigrationResource {
    private final Logger log = LoggerFactory.getLogger(EsMigrationResource.class);

    @Inject
    private EsMigrationService esMigrationResource;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        return new ResponseEntity<String>("test hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/saveIndexData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveIndexData() {
        esMigrationResource.saveIndexData();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}

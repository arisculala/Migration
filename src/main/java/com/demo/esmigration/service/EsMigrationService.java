package com.demo.esmigration.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EsMigrationService {
    private final Logger log = LoggerFactory.getLogger(EsMigrationService.class);


    private RestTemplate restTemplate = new RestTemplate();

    public void saveIndexData() {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:9200/<index-name>/<index-type>/_search").build().toUri();
        String response = restTemplate.getForObject(url, String.class);
        
        java.nio.file.Path kbVersionFilePath = java.nio.file.Paths.get("/Users/arisculala/Desktop" + File.separator + "testing.json");
        
        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter(kbVersionFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            writer.write(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

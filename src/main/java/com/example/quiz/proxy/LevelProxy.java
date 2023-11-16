package com.example.quiz.proxy;

import com.example.quiz.CustomProperties;
import com.example.quiz.model.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class LevelProxy {
    @Autowired
    private CustomProperties props;
    public Iterable<Level> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/levels";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Level>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Level>>() {}
        );

        log.debug("Get levels call " + response.getStatusCode().toString());

        return response.getBody();
    }
    public Level createLevel(Level l) {
        String baseApiUrl = props.getApiUrl();
        String createLevelUrl = baseApiUrl + "/level"; // Modifier le nom de la variable pour refléter la création d'un niveau

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Level> request = new HttpEntity<>(l, headers);
        ResponseEntity<Level> response = restTemplate.exchange(
                createLevelUrl,
                HttpMethod.POST,
                request,
                Level.class);

        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        log.debug("Create Level call " + statusCode);

        if (statusCode == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            // Gérer les erreurs ou les scénarios où la création a échoué
            log.error("Failed to create Level. Status code: " + statusCode);
            // Peut-être renvoyer une exception ou une valeur par défaut selon votre logique métier
            return null;
        }
    }
}

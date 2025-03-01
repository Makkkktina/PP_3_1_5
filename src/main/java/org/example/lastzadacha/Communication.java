package org.example.lastzadacha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private static final Logger logger = LoggerFactory.getLogger(Communication.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";
    private final StringBuilder sb = new StringBuilder();

    public StringBuilder getSb() {
        return sb;
    }

    public List<User> getAllUsers() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
                });
        // Сохраняем Cookie из ответа
        String cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        headers.set(HttpHeaders.COOKIE, cookie); // Устанавливаем Cookie для последующих запросов
        return responseEntity.getBody();
    }

    public void saveUser(User user) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        sb.append(responseEntity.getBody());
    }

    public void updateUser(User user) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        sb.append(responseEntity.getBody());
    }

    public void deleteUser(Long id) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers); // Тело запроса не нужно
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        sb.append(responseEntity.getBody());
    }
}
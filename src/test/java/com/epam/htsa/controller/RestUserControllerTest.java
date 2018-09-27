package com.epam.htsa.controller;

import com.epam.htsa.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Configuration
public class RestUserControllerTest {

    private static final String URL = "http://localhost:8080/rest";
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> requestEntity;

    @Before
    public void before() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestEntity = new HttpEntity<String>(headers);
    }

    @Test
    public void getUserById() {
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        long userId = 5l;
        ResponseEntity<User> responseEntity = restTemplate.exchange(URL + "/user/{userId}",
                HttpMethod.GET, requestEntity, User.class, userId);
        User response = responseEntity.getBody();
        Assertions.assertThat(response.getId()).isEqualTo(userId);
    }

    @Test
    public void getUsers() {
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(URL + "/user/users", HttpMethod.GET, requestEntity, User[].class);
        User[] usersResponse = responseEntity.getBody();

        Assertions.assertThat(usersResponse.length).isEqualTo(3);
    }
}
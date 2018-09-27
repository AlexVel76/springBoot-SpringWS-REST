package com.epam.htsa.controller;

import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.User;
import com.epam.htsa.restconvertor.Ticket2PdfMessageConverter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Configuration
public class RestBookingControllerTest {

    /*
    *
    * Call localhost:8080/rest/booking/8 with [{"key":"Accept","value":"application/pdf"}] from POSTman to download PDF
    * or with [{"key":"Accept","value":"application/json"}] to get JSON
    *
    * */


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
    public void getTicketById() {
        long id = 8l;
        ResponseEntity<Ticket> responseEntity = restTemplate.exchange(URL + "/booking/{ticketId}",
                HttpMethod.GET, requestEntity, Ticket.class, id);
        Object response = responseEntity.getBody();
        Assertions.assertThat(((Ticket) response).getId()).isEqualTo(id);
    }

    @Test
    @Ignore
    public void getTickets() {
        ResponseEntity<Ticket[]> responseEntity = restTemplate.exchange(URL + "/booking", HttpMethod.GET, requestEntity, Ticket[].class);
        Ticket[] result = responseEntity.getBody();

        Assertions.assertThat(result.length).isEqualTo(2);
    }

}
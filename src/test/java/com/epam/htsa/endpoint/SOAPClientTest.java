package com.epam.htsa.endpoint;

import com.epam.htsa.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Configuration
public class SOAPClientTest {


      /*

    To see wsdl go to http://localhost:8080/ws/userevent.wsdl

    See SOAPClientTest.java as SOAP Client. To test it just run main Spring Boot Application as usual
    and than run the test as common test.

    */


    @Bean
    WebServiceTemplate webServiceTemplateClient() throws SOAPException, Exception {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
        webServiceTemplate.setMarshaller(getMarshaller());
        webServiceTemplate.setUnmarshaller(getMarshaller());
        webServiceTemplate.setMessageSender(new HttpUrlConnectionMessageSender());
        webServiceTemplate.setCheckConnectionForFault(true);
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws/");

        messageFactory.afterPropertiesSet();
        return webServiceTemplate;
    }

    @Bean
    Jaxb2Marshaller getMarshaller() throws Exception {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.epam.htsa.entity");
        jaxb2Marshaller.afterPropertiesSet();
        return jaxb2Marshaller;
    }

    @Test
    public void testGetUserById() throws Exception{
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(5l);

        GetUserResponse userResponse = getUser(getUserRequest);
        Assertions.assertThat(userResponse.getUser().getId()).isEqualTo(getUserRequest.getId());
    }

    private GetUserResponse getUser(GetUserRequest getUserRequest) throws Exception {
        GetUserResponse result = (GetUserResponse) webServiceTemplateClient().marshalSendAndReceive(getUserRequest,
                new SoapActionCallback("http://localhost:8080/ws/getUserRequest"));
        return result;

    }

    @Test
    public void testGetAllUser() throws Exception{
        GetAllUserRequest getUserRequest = new GetAllUserRequest();

        GetAllUserResponse userResponse = getAllUser(getUserRequest);
        Assertions.assertThat(userResponse.getUser().size()).isEqualTo(3);
    }

    private GetAllUserResponse getAllUser(GetAllUserRequest getUserRequest) throws Exception {
        GetAllUserResponse result = (GetAllUserResponse) webServiceTemplateClient().marshalSendAndReceive(getUserRequest,
                new SoapActionCallback("http://localhost:8080/ws/getAllUserRequest"));
        return result;

    }

    @Test
    public void testGetEventById() throws Exception{
        GetEventRequest getEventRequest = new GetEventRequest();
        getEventRequest.setId(3l);

        GetEventResponse eventResponse = getEvent(getEventRequest);
        Assertions.assertThat(eventResponse.getEvent().getId()).isEqualTo(getEventRequest.getId());
    }

    private GetEventResponse getEvent(GetEventRequest getEventRequest) throws Exception {
        GetEventResponse result = (GetEventResponse) webServiceTemplateClient().marshalSendAndReceive(getEventRequest,
                new SoapActionCallback("http://localhost:8080/ws/getEventRequest"));
        return result;

    }

    @Test
    public void testGetAllEvent() throws Exception{
        GetAllEventRequest getEventRequest = new GetAllEventRequest();

        GetAllEventResponse eventResponse = getAllEvent(getEventRequest);
        Assertions.assertThat(eventResponse.getEvent().size()).isEqualTo(1);
    }

    private GetAllEventResponse getAllEvent(GetAllEventRequest request) throws Exception {
        GetAllEventResponse result = (GetAllEventResponse) webServiceTemplateClient().marshalSendAndReceive(request,
                new SoapActionCallback("http://localhost:8080/ws/getAllEventRequest"));
        return result;

    }


}

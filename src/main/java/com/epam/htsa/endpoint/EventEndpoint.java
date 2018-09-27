package com.epam.htsa.endpoint;

import com.epam.htsa.entity.*;
import com.epam.htsa.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://localhost/hometask/entity";

    @Autowired
    private EventService eventService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    @ResponsePayload
    public GetEventResponse getEventById(@RequestPayload GetEventRequest request) {
        GetEventResponse result = new GetEventResponse();
        result.setEvent(eventService.getById(request.getId()));
        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEventRequest")
    @ResponsePayload
    public GetAllEventResponse getAllEvent(@RequestPayload GetAllEventRequest request) {
        GetAllEventResponse result = new GetAllEventResponse();
        result.getEvent().addAll((List<Event>) eventService.getAll());
        return result;
    }

}

package com.epam.htsa.endpoint;

import com.epam.htsa.entity.*;
import com.epam.htsa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://localhost/hometask/entity";

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserById(@RequestPayload GetUserRequest userRequest) {
        GetUserResponse result = new GetUserResponse();
        result.setUser(userService.getById(userRequest.getId()));
        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUserRequest")
    @ResponsePayload
    public GetAllUserResponse getAllUser(@RequestPayload GetAllUserRequest userRequest) {
        GetAllUserResponse result = new GetAllUserResponse();
        result.getUser().addAll((List<User>) userService.getAll());
        return result;
    }

}

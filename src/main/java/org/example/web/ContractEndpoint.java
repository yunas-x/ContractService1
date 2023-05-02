package org.example.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.example.CreateNewContractRequest;
import org.example.CreateNewContractResponse;
import org.example.data.CreateNewContract;
import org.example.mapping.RequestToNewContractMapperImplementation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@PropertySource("classpath:application.properties")
public class ContractEndpoint {


    @Getter
    private static final String NAMESPACE_URI = "http://contractservice.org/service";
    private final ContractService service;

    @Autowired
    public ContractEndpoint(ContractService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = "NAMESPACE_URI", localPart = "createNewContractRequest")
    @ResponsePayload
    public CreateNewContractResponse createNewContract(@RequestPayload CreateNewContractRequest request) {

        return service.createNewContractResponse(request);
    }


}
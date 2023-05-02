package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CreateNewContractRequest;
import org.example.CreateNewContractResponse;
import org.example.Request;
import org.example.data.CreateNewContract;
import org.example.mapping.RequestToNewContractMapperImplementation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

interface ContractService {
    public CreateNewContractResponse createNewContractResponse(CreateNewContractRequest request);
}

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    AmqpTemplate template;

    @Override
    public CreateNewContractResponse createNewContractResponse(CreateNewContractRequest request) {
        CreateNewContractResponse response = new CreateNewContractResponse();
        CreateNewContract contact = getCreateNewContract(request, response);

        if (contact == null) return response;

        String json = getJson(response, contact);

        if (json == null) return response;

        sendToRabbit(response, json);

        return response;
    }

    private void sendToRabbit(CreateNewContractResponse response, String json) {
        try {
            template.convertAndSend("contract.create", json);
            response.setStatus("Success");
            response.setContent("OK");
        } catch (Exception e) {
            response.setStatus("Error");
            response.setContent("Unable to send json");
        }
    }

    private String getJson(CreateNewContractResponse response, CreateNewContract contact) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(contact);
        } catch (Exception e) {
            response.setStatus("Error");
            response.setContent("Unable to convert to JSON");
        }
        return json;
    }

    private static CreateNewContract getCreateNewContract(CreateNewContractRequest request, CreateNewContractResponse response) {
        CreateNewContract contact = null;
        try {
            contact =
                    new RequestToNewContractMapperImplementation().toCreateNewContract(request.getRequest());
        } catch (Exception e) {
            response.setStatus("Error");
            response.setContent("Corrupt Data");
        }
        return contact;
    }
}

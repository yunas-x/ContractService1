package org.example.mapping;

import org.example.data.ClientAPIs;
import org.example.data.CreateNewContract;
import org.example.Request;

import java.time.LocalDateTime;

// There has been a trouble with mapping which I could not solve
// If you know how to solve it, share it

public class RequestToNewContractMapperImplementation extends RequestToNewContractMapper {

    @Override
    public CreateNewContract toCreateNewContract(Request request) {
        if ( request == null ) {
            return null;
        }

        PartiesMapperImpl partiesMapper = new PartiesMapperImpl();

        CreateNewContract createNewContract = new CreateNewContract();
        createNewContract.setContractName(request.getContractName());
        createNewContract.setContractNumber(request.getContractNumber());
        createNewContract.setId(request.getId());
        createNewContract.setDateEnd(request.getDateEnd().toGregorianCalendar().getTime());
        createNewContract.setDateStart(request.getDateStart().toGregorianCalendar().getTime());
        createNewContract.getContractualParties()
                .setContractualParties(partiesMapper.map(request.getContractualParties().getContractualParties()));
        createNewContract.setClientAPI(ClientAPIs.SOAP);
        createNewContract.setDateSend(LocalDateTime.now());

        return createNewContract;
    }
}

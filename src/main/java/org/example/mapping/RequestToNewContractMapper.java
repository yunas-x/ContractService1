package org.example.mapping;

import org.example.*;
import org.example.data.CreateNewContract;
import org.mapstruct.Mapper;


@Mapper
public abstract class RequestToNewContractMapper {

    public abstract CreateNewContract toCreateNewContract(Request request);

    /*
        createNewContract.setContractName(request.getContractName());
        createNewContract.setContractNumber(request.getContractNumber());
        createNewContract.setId(request.getId());
        createNewContract.setDateEnd(request.getDateEnd().toGregorianCalendar().getTime());
        createNewContract.setDateStart(request.getDateStart().toGregorianCalendar().getTime());
        createNewContract.setContractualParties(request.getContractualParties());
        createNewContract.setClientAPI(ClientAPIs.SOAP);
        createNewContract.setDateSend(LocalDateTime.now());
     */
}

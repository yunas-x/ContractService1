package org.example.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateNewContract {

    LocalDateTime dateSend;
    ClientAPIs clientAPI;
    int id;
    Date dateStart;
    Date dateEnd;
    int contractNumber;
    String contractName;
    ContractualPartiesInnerRepresentation contractualParties;

}

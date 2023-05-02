package org.example.mapping;

import org.example.ContractualParty;
import org.example.data.ContractualPartyInnerRepresentation;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public abstract class PartiesMapper {

    ContractualPartyInnerRepresentation map(ContractualParty party) {
        ContractualPartyInnerRepresentation partyInnerRepresentation = new ContractualPartyInnerRepresentation();
        partyInnerRepresentation.setBik(party.getBik());
        partyInnerRepresentation.setName(party.getName());
        partyInnerRepresentation.setBankAccountNumber(party.getBankAccountNumber());
        return partyInnerRepresentation;
    }

    abstract List<ContractualPartyInnerRepresentation> map (Collection<ContractualParty> parties);
}

package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.web.dto.ClientDto;

import java.util.stream.Collectors;

@Component
public class ClientConverter extends AbstractConverterBaseEntityConverter<Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var model = new Client();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDateOfBirth(dto.getDateOfBirth());
        model.setAddress(dto.getAddress());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .name(client.getName())
                .dateOfBirth(client.getDateOfBirth())
                .address(client.getAddress())
                .gunTypes(client.getGunTypes().stream().map(BaseEntity::getId).collect(Collectors.toSet()))
                .build();
//        ClientDto dto = new ClientDto(client.getName(),
//                                    client.getDateOfBirth(),
//                                    client.getAddress());
        clientDto.setId(client.getId());
        return clientDto;
    }
}


package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.web.dto.ClientGunDto;

@Component
public class ClientGunConverter extends AbstractConverter<Rental, ClientGunDto>{
    @Override
    public Rental convertDtoToModel(ClientGunDto dto) {
        return null;
    }

    @Override
    public ClientGunDto convertModelToDto(Rental rental) {
        return ClientGunDto.builder()
                .clientId(rental.getClient().getId())
                .gunTypeId(rental.getGunType().getId())
                .price(rental.getPrice())
                .build();
    }
}

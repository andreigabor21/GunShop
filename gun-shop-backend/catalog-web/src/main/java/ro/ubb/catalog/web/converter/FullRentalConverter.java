package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.core.repository.ClientRepository;
import ro.ubb.catalog.core.repository.GunTypeRepository;
import ro.ubb.catalog.web.dto.FullRentalDto;
import ro.ubb.catalog.web.dto.RentalDto;

@Component
public class FullRentalConverter extends AbstractConverter<Rental, FullRentalDto>{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GunTypeRepository gunTypeRepository;

    @Override
    public Rental convertDtoToModel(FullRentalDto fullRentalDto) {
        return Rental.builder()
                .client(clientRepository.findById(fullRentalDto.getClientId()).orElse(null))
                .gunType(gunTypeRepository.findById(fullRentalDto.getGunTypeId()).orElse(null))
                .price(fullRentalDto.getPrice())
                .build();
    }

    @Override
    public FullRentalDto convertModelToDto(Rental rental) {
        return FullRentalDto.builder()
                .clientId(rental.getClient().getId())
                .clientName(rental.getClient().getName())
                .gunTypeId(rental.getGunType().getId())
                .gunTypeName(rental.getGunType().getName())
                .price(rental.getPrice())
                .build();
    }
}

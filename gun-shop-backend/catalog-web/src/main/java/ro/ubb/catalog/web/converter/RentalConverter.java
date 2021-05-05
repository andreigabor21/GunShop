package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.web.dto.RentalDto;

@Component
public class RentalConverter extends AbstractConverter<Rental, RentalDto>{
    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        return null;
    }

    @Override
    public RentalDto convertModelToDto(Rental rental) {
        return RentalDto.builder()
                .clientId(rental.getClient().getId())
                .gunTypeId(rental.getGunType().getId())
                .price(rental.getPrice())
                .build();
    }
}

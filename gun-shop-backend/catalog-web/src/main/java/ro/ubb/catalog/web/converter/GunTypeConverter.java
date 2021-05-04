package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.web.dto.GunProviderDto;
import ro.ubb.catalog.web.dto.GunTypeDto;

@Component
public class GunTypeConverter extends BaseConverter<GunType, GunTypeDto> {

    @Autowired
    private GunProviderConverter gunProviderConverter;

    @Override
    public GunType convertDtoToModel(GunTypeDto dto) {
        var model = new GunType();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCategory(dto.getCategory());

        GunProviderDto gunProviderDto = dto.getGunProviderDto();
        GunProvider gunProvider = gunProviderConverter.convertDtoToModel(gunProviderDto);

        model.setGunProvider(gunProvider);
        return model;
    }

    @Override
    public GunTypeDto convertModelToDto(GunType gunType) {
        GunTypeDto dto = new GunTypeDto(gunType.getName(),
                                gunType.getCategory(),
                                gunProviderConverter
                                        .convertModelToDto(gunType.getGunProvider()));
        dto.setId(gunType.getId());
        return dto;
    }
}

package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.web.dto.GunProviderDto;
import ro.ubb.catalog.web.dto.GunTypeDto;

import java.util.Objects;

@Component
public class GunTypeConverter extends AbstractConverterBaseEntityConverter<GunType, GunTypeDto> {

    @Autowired
    private PlainGunProviderConverter plainGunProviderConverter;

    @Override
    public GunType convertDtoToModel(GunTypeDto dto) {
        var model = new GunType();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCategory(dto.getCategory());
        return model;
    }

    @Override
    public GunTypeDto convertModelToDto(GunType gunType) {
        GunTypeDto gunTypeDto = new GunTypeDto();
        gunTypeDto.setId(gunType.getId());
        gunTypeDto.setName(gunType.getName());
        gunTypeDto.setCategory(gunType.getCategory());
        if(Objects.nonNull(gunType.getGunProvider())){
            gunTypeDto.setGunProvider(
                    plainGunProviderConverter.convertModelToDto(gunType.getGunProvider()));
        }
        return gunTypeDto;
//        GunTypeDto dto = new GunTypeDto(gunType.getName(),
//                                gunType.getCategory(),
//                                gunProviderConverter
//                                        .convertModelToDto(gunType.getGunProvider()));
//        dto.setId(gunType.getId());

    }
}

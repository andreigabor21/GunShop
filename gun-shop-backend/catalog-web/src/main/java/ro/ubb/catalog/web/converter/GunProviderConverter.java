package ro.ubb.catalog.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.web.dto.GunProviderDto;
import ro.ubb.catalog.web.dto.GunTypeDto;

@Component
public class GunProviderConverter extends BaseConverter<GunProvider, GunProviderDto> {

    @Autowired
    private GunTypeConverter gunTypeConverter;

    @Override
    public GunProvider convertDtoToModel(GunProviderDto dto) {
        var model = new GunProvider();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setReputation(dto.getReputation());
        model.setSpeciality(dto.getSpeciality());
        return model;
    }

    @Override
    public GunProviderDto convertModelToDto(GunProvider gunProvider) {
        GunProviderDto dto = new GunProviderDto();
        dto.setId(gunProvider.getId());
        dto.setName(gunProvider.getName());
        dto.setReputation(gunProvider.getReputation());
        dto.setSpeciality(gunProvider.getSpeciality());
        dto.setGunTypeDtos(gunTypeConverter.convertModelsToDtos(
                gunProvider.getGunTypes()));
        return dto;
    }
}

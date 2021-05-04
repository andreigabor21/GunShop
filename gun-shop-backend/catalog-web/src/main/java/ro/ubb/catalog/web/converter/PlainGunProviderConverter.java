package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.web.dto.GunProviderDto;
import ro.ubb.catalog.web.dto.PlainGunProviderDto;

@Component
public class PlainGunProviderConverter extends AbstractConverterBaseEntityConverter<GunProvider, PlainGunProviderDto> {
    @Override
    public GunProvider convertDtoToModel(PlainGunProviderDto dto) {
        return null;
    }

    @Override
    public PlainGunProviderDto convertModelToDto(GunProvider gunProvider) {
        PlainGunProviderDto plainProviderDto = new PlainGunProviderDto();
        plainProviderDto.setId(gunProvider.getId());
        plainProviderDto.setName(gunProvider.getName());
        plainProviderDto.setSpeciality(gunProvider.getSpeciality());
        plainProviderDto.setReputation(gunProvider.getReputation());
        return plainProviderDto;
    }
}

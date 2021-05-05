package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.core.service.GunProviderService;
import ro.ubb.catalog.core.service.GunTypeService;
import ro.ubb.catalog.web.converter.GunTypeConverter;
import ro.ubb.catalog.web.dto.GunTypeDto;

import java.util.Collection;

@RestController
public class GunTypeController {

    public static final Logger logger = LoggerFactory.getLogger(GunTypeController.class);

    @Autowired
    private GunTypeService gunTypeService;

    @Autowired
    private GunProviderService gunProviderService;

    @Autowired
    private GunTypeConverter gunTypeConverter;

    @RequestMapping(value = "/gun-types")
    ResponseEntity<Collection<GunTypeDto>> getAllGunTypes() {
        logger.trace("getAllGunTypes - method entered;");
        Collection<GunTypeDto> result = gunTypeConverter.convertModelsToDtos(
                        gunTypeService.getAllGunTypes());

        logger.trace("getAllGunTypes - method finished; result = {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-types", method = RequestMethod.POST)
    ResponseEntity<GunTypeDto> addGunType(@RequestBody GunTypeDto gunTypeDto){
        logger.trace("addGunType - method entered; gunTypeDto = {}", gunTypeDto);
        var gunType = gunTypeConverter.convertDtoToModel(gunTypeDto);
        GunType result;
        try {
            result = gunTypeService.saveGunType(gunType);
            logger.trace("AICI!!! - provider:{} cu ID: {}", gunTypeDto.getGunProvider(), gunTypeDto.getGunProvider().getId());
            logger.trace("AICII!!!2 - result={} cu ID={}", result, result.getId());
            gunProviderService.addGunToProvider(gunTypeDto.getGunProvider().getId(), result.getId());
        } catch (Exception e) {
            throw new RuntimeException("Could not add");
        }
        var resultModel = gunTypeConverter.convertModelToDto(result);

        logger.trace("addGunType - method finished; resultModel = {}", resultModel);
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-types/{id}", method = RequestMethod.PUT)
    ResponseEntity<GunTypeDto> updateGunType(@PathVariable Long id,
                             @RequestBody GunTypeDto dto) {
        logger.trace("updateGunType - method entered; dto = {}", dto);
        dto.setId(id);
        GunTypeDto result = gunTypeConverter.convertModelToDto(
                gunTypeService.updateGunType(
                        gunTypeConverter.convertDtoToModel(dto)
                ));

        logger.trace("updateGunType - method finished; result = {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-types/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGunType(@PathVariable Long id) {
        logger.trace("deleteGunType - method entered; result = {}", gunTypeService.getGunTypeById(id));
        gunTypeService.deleteGunType(id);
        logger.trace("deleteGunType - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-types/{id}")
    ResponseEntity<GunTypeDto> getGunTypeById(@PathVariable Long id) {
        GunTypeDto gunTypeDto = gunTypeConverter.convertModelToDto(
                gunTypeService.getGunTypeById(id));
        return new ResponseEntity<>(gunTypeDto, HttpStatus.OK);
    }





//    @RequestMapping(value = "/gun-types/filter/{category}")
//    GunTypesDto filterGunTypesByCategory(@PathVariable Category category) {
//        return new GunTypesDto(
//                gunTypeConverter.convertModelsToDtos(
//                        gunTypeService.filterGunTypesByCategory(category)));
//    }
}

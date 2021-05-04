package ro.ubb.catalog.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.GunProvider;
import ro.ubb.catalog.core.service.GunProviderService;
import ro.ubb.catalog.web.converter.GunProviderConverter;
import ro.ubb.catalog.web.dto.GunProviderDto;
import ro.ubb.catalog.web.dto.GunProvidersDto;

import java.util.List;

@RestController
public class GunProviderController {

    public static final Logger logger = LoggerFactory.getLogger(GunProviderController.class);

    @Autowired
    private GunProviderService gunProviderService;

    @Autowired
    private GunProviderConverter gunProviderConverter;

    @RequestMapping(value = "/gun-providers")
    ResponseEntity<List<GunProviderDto>> getAllGunProviders() {
        logger.trace("addGunProvider - method entered;");
        List<GunProviderDto> result = gunProviderConverter.convertModelsToDtos(
                gunProviderService.getAllGunProviders());
        logger.trace("addGunProvider - method finished; result = {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-providers", method = RequestMethod.POST)
    ResponseEntity<GunProviderDto> addGunProvider(@RequestBody GunProviderDto gunProviderDto){
        logger.trace("addGunProvider - method entered; gunProviderDto = {}", gunProviderDto);
        GunProvider gunProvider = gunProviderConverter.convertDtoToModel(gunProviderDto);

        GunProvider result = gunProviderService.addGunProvider(gunProvider);

        GunProviderDto resultModel = gunProviderConverter.convertModelToDto(result);
        logger.trace("addGunProvider - method finished; resultModel = {}", resultModel);
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-providers/{id}", method = RequestMethod.PUT)
    ResponseEntity<GunProviderDto> updateGunProvider(@PathVariable Long id,
                                     @RequestBody GunProviderDto dto) {
        logger.trace("updateGunProvider - method entered; dto = {}", dto);
        dto.setId(id);
        GunProviderDto result = gunProviderConverter.convertModelToDto(
                gunProviderService.updateGunProvider(
                        gunProviderConverter.convertDtoToModel(dto)
                ));
        logger.trace("updateGunProvider - method finished; result = {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-providers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGunProvider(@PathVariable Long id) {
        logger.trace("deleteGunProvider - method entered; result = {}", gunProviderService.getGunProviderById(id));
        gunProviderService.deleteGunProvider(id);
        logger.trace("deleteGunProvider - method finished;");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/gun-providers/{id}")
    GunProviderDto getGunProviderById(@PathVariable Long id) {
        return gunProviderConverter.convertModelToDto(
                gunProviderService.getGunProviderById(id));
    }

    @RequestMapping(value = "gun-providers/sort/name")
    List<GunProviderDto> getGunProvidersSortedByName() {
        logger.trace("getGunProvidersSortedByName - method entered;");
        List<GunProviderDto> result = gunProviderConverter.convertModelsToDtos(
                gunProviderService.getGunProvidersSortedByName());
        return result;
    }


    @RequestMapping(value = "gun-providers/filter")
    List<GunProviderDto> filterGunProvidersByReputation(@RequestParam("reputation") int reputation) {
        logger.trace("filterGunProvidersByReputation - method entered;");
        List<GunProviderDto> result = gunProviderConverter.convertModelsToDtos(
                gunProviderService.getGunProvidersFilteredByReputation(reputation));
        logger.trace("filterGunProvidersByReputation - method finished; result = {}", result);
        return result;
    }

    @PostMapping(value = "gun-providers/{providerId}/guns/{gunTypeId}/add")
    public ResponseEntity<GunProviderDto> addItem(@PathVariable final Long providerId,
                                                 @PathVariable final Long gunTypeId){
        GunProvider gunProvider = gunProviderService.addGunToProvider(providerId, gunTypeId);
        return new ResponseEntity<>(gunProviderConverter.convertModelToDto(gunProvider),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "gun-providers/{providerId}/guns/{gunTypeId}/remove")
    public ResponseEntity<GunProviderDto> removeItemFromCart(@PathVariable final Long providerId,
                                                      @PathVariable final Long gunTypeId){
        GunProvider gunProvider = gunProviderService.removeGunFromProvider(providerId, gunTypeId);
        return new ResponseEntity<>(gunProviderConverter.convertModelToDto(gunProvider),
                HttpStatus.OK);
    }
}

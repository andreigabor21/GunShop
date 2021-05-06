package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.FullRentalConverter;
import ro.ubb.catalog.web.converter.GunTypeConverter;
import ro.ubb.catalog.web.converter.RentalConverter;
import ro.ubb.catalog.web.dto.FullRentalDto;
import ro.ubb.catalog.web.dto.GunTypeDto;
import ro.ubb.catalog.web.dto.RentalDto;

import java.util.HashSet;
import java.util.Set;

@RestController
public class RentalController {

    private static final Logger log = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private RentalConverter rentalConverter;

    @Autowired
    private FullRentalConverter fullRentalConverter;

    @Autowired
    private GunTypeConverter gunTypeConverter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public ResponseEntity<Set<FullRentalDto>> getRentals() {
        Set<Rental> rentals = clientService.getRentals();
        log.trace("fetch rentals: {}", rentals);
        Set<FullRentalDto> rentalDtos = new HashSet<>(fullRentalConverter.convertModelsToDtos(rentals));

        return new ResponseEntity<>(rentalDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public ResponseEntity<?> addRental(@RequestBody RentalDto dto) {
        try {
            clientService.addRental(
                    dto.getClientId(),
                    dto.getGunTypeId(),
                    dto.getPrice()
            );
        } catch (Exception e) {
            log.trace("rental already exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("rental added");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals/{clientId}/{gunId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRental(
            @PathVariable Long clientId,
            @PathVariable Long gunId,
            @RequestBody RentalDto dto) {
        log.trace("updateRental - method entered with clientId={}, gunId={}, price={}", clientId, gunId, dto.getPrice());

        try {
            Rental rental = clientService.updateRental(clientId, gunId, dto.getPrice());
            RentalDto rentalDto = rentalConverter.convertModelToDto(rental);
            log.trace("rental updated");
            return new ResponseEntity<>(rentalDto, HttpStatus.OK);
        } catch (Exception e) {
            log.trace("rental could not be updated");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rentals/most-rented")
    public ResponseEntity<GunTypeDto> getMostRentedGun() {
        log.trace("getMostRentedGun - method entered");
        try {
            GunType gun = clientService.getMostRentedGunType();
            GunTypeDto gunDto = gunTypeConverter.convertModelToDto(gun);
            return new ResponseEntity<>(gunDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/rentals/{clientId}/{gunTypeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRental(@PathVariable Long clientId, @PathVariable Long gunTypeId) {
        log.trace("updateRental - method entered with clientId={}, gunTypeId={}", clientId, gunTypeId);
        try {
            clientService.deleteRental(clientId, gunTypeId);
        } catch (Exception e) {
            log.trace("rental could not be deleted");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("rental deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

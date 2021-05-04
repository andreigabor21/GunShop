package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.ClientGunConverter;
import ro.ubb.catalog.web.dto.ClientGunDto;

import java.util.Set;

@RestController
public class RentalController {

    private static final Logger log = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientGunConverter converter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public ResponseEntity<Set<ClientGunDto>> getRentals() {
        Set<Rental> rentals = clientService.getRentals();
        log.trace("fetch rentals: {}", rentals);

        Set<ClientGunDto> clientGunDtos = (Set)converter.convertModelsToDtos(rentals);
        return new ResponseEntity<>(clientGunDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public ResponseEntity<?> addRental(@RequestBody ClientGunDto dto) {
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.PUT)
//    public ResponseEntity<Set<ClientGunDto>> addRental(
//            @PathVariable final Long studentId,
//            @RequestBody final Set<StudentDisciplineDto> studentDisciplineDtos) {
//        log.trace("updateStudentGrades: studentId={}, studentDisciplineDtos={}",
//                studentId, studentDisciplineDtos);
//
//        throw new RuntimeException("not yet implemented");
//    }

}

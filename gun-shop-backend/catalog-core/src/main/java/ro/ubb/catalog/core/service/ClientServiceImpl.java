package ro.ubb.catalog.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Rental;
import ro.ubb.catalog.core.model.GunType;
import ro.ubb.catalog.core.model.validators.ClientValidator;
import ro.ubb.catalog.core.repository.ClientRepository;
import ro.ubb.catalog.core.repository.GunTypeRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    public static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientValidator validator;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GunTypeRepository gunTypeRepository;

    @Override
    public List<Client> getAllClients() {
        logger.trace("getAllClients - method entered");
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client client) {
        logger.trace("addClient - method entered; client = {}", client);
        validator.validate(client);
        Client clientAdded = clientRepository.save(client);
        logger.trace("addClient - method finished; client = {}", clientAdded);
        return clientAdded;
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        logger.trace("deleteClient - method entered; client = {}", client);

        logger.trace("deleting the rentals from client");
        Set<Rental> clientRentalSet = client.getRentalSet();
        clientRentalSet.removeIf(rental ->
                rental.getClient().getId().equals(id));
        client.setRentalSet(clientRentalSet);

        logger.trace("deleting the rentals from gun type");
        gunTypeRepository.findAll()
                .forEach(
                        gunType -> {
                            Set<Rental> rentalSet = gunType.getRentalSet();
                            rentalSet.removeIf(rental ->
                                    rental.getClient().getId().equals(id));
                            gunType.setRentalSet(rentalSet);
                        }
                );
        clientRepository.deleteById(id);
        logger.trace("deleteClient - method finished");
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        logger.trace("updateClient - method entered; client = {}", client);
        validator.validate(client);
        Client updateClient = clientRepository.findById(client.getId()).orElseThrow();
        updateClient.setName(client.getName());
        updateClient.setDateOfBirth(client.getDateOfBirth());
        updateClient.setAddress(client.getAddress());
        updateClient.setRentalSet(client.getRentalSet()); //new
        logger.trace("updateClient - method finished; client = {}", updateClient);
        return client;
    }

    @Override
    public Client getClientById(Long id) {
        logger.trace("getClientById - method entered={}", id);
        Client clientById = clientRepository.findById(id).orElseThrow();
        logger.trace("getClientById - method finished; result={}", clientById);
        return clientById;
    }

    @Override
    public List<Client> getAllClientsBornBefore(LocalDate date) {
        logger.trace("getAllClientsBornBefore - method entered; date={}", date);
        List<Client> clientsBornBefore = clientRepository.findClientsByDateOfBirthBefore(date);
        logger.trace("getAllClientsBornBefore - method finished; result={}", clientsBornBefore);
        return clientsBornBefore;
    }

    public Set<Rental> getRentals() {  //TODO
        logger.trace("Fetching all rentals");
        Set<Rental> result = clientRepository.findAll()
                .stream()
                .map(Client::getRentalSet)
                .reduce(new HashSet<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        /*List<Rental> result2 = clientRepository.findAll()
                .stream()
                .map(Client::getRentalSet)
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });*/

        logger.trace("FETCHED IN SERVICE: {}", result);
        return result;
    }

    @Transactional
    public Rental addRental(Long clientId, Long gunTypeId, Integer price) {
        logger.trace("addRental - method entered; clientId={}, gunTypeId={}, price={}", clientId, gunTypeId, price);
        Client client = clientRepository.findById(clientId).orElseThrow();
        GunType gunType = gunTypeRepository.findById(gunTypeId).orElseThrow();
        Rental rental = new Rental(client, gunType, price);
        logger.trace("Adding rental {}", rental);
        clientRepository.findById(client.getId()).orElseThrow()
                            .addGunTypeWithPrice(gunType, price);
        return rental;
    }

    @Override
    @Transactional
    public Rental updateRental(Long clientId, Long gunTypeId, Integer price) {
        logger.trace("updateRental - method entered; clientId={}, gunTypeId={}, price={}", clientId, gunTypeId, price);
        System.out.printf("clientId=%d, gunTypeId=%d, price=%d", clientId, gunTypeId, price);
        Client client = clientRepository.findById(clientId).orElseThrow();
        GunType gunType = gunTypeRepository.findById(gunTypeId).orElseThrow();
        clientRepository.findById(client.getId()).orElseThrow()
                        .getRentalSet()
                        .stream()
                        .filter(rental -> rental.getClient().getId().equals(clientId))
                        .filter(rental -> rental.getGunType().getId().equals(gunTypeId))
                        .findFirst()
                        .orElseThrow(RuntimeException::new)
                        .setPrice(price);
        return new Rental(client, gunType, price);
    }

    @Override
    @Transactional
    public void deleteRental(Long clientId, Long gunTypeId) {
        logger.trace("deleteRental - method entered; clientId = {}, gunTypeId={}", clientId, gunTypeId);
        Client client = clientRepository.findById(clientId).orElseThrow();
        Optional<Rental> rentalOptional = client
                .getRentalSet()
                .stream()
                .filter(rental -> rental.getGunType().getId().equals(gunTypeId))
                .findFirst();
        rentalOptional.ifPresent(
                rental -> {
                    Set<Rental> rentalSet = client.getRentalSet();
                    rentalSet.remove(rental);
                    client.setRentalSet(rentalSet);
                }
        );
        GunType gunType = gunTypeRepository.findById(gunTypeId).orElseThrow();
        rentalOptional = gunType
                .getRentalSet()
                .stream()
                .filter(rental -> rental.getClient().getId().equals(clientId))
                .findFirst();
        rentalOptional.ifPresent(
                rental -> {
                    Set<Rental> rentalSet = gunType.getRentalSet();
                    rentalSet.remove(rental);
                    gunType.setRentalSet(rentalSet);
                }
        );
        logger.trace("deleteRental - method finished");
    }

    @Override
    public GunType getMostRentedGunType() {
        logger.trace("getMostRentedGunType - method entered");
        try {
            Map<GunType, Integer> count = new HashMap<>();
            this.getRentals().forEach(rental -> {
                count.put(rental.getGunType(), count.getOrDefault(rental.getGunType(), 0) + 1);
            });

            var max = count.entrySet().stream()
                    .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get()
                    .getKey();
            GunType result = gunTypeRepository.findById(max.getId()).orElse(null);
            logger.trace("getMostRentedGunType: result={}", result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

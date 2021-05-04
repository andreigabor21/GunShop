package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ClientService {
    List<Client> getAllClients();

    Client addClient(Client client);

    void deleteClient(Long id);

    Client updateClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClientsBornBefore(LocalDate date);

    Set<Rental> getRentals();

    void addRental(Long clientId, Long gunTypeId, Integer price);
}

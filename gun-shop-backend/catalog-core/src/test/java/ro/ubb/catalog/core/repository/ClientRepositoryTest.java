package ro.ubb.catalog.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.ITConfig;
import ro.ubb.catalog.core.model.Address;
import ro.ubb.catalog.core.model.Client;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data-clients.xml")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAll() {
        List<Client> clients = clientRepository.findAll();
        assertEquals(3, clients.size());
    }

    @Test
    public void add() {
        Address address = new Address("city", "street", 40);
        Client client = Client.builder()
                .name("New")
                .dateOfBirth(LocalDate.of(2000, 9, 9))
                .address(address)
                .build();
        client.setId(1000L);
        clientRepository.save(client);
        List<Client> clients = clientRepository.findAll();
        assertEquals(4, clients.size());
    }

    @Test
    @Transactional
    public void update() throws Exception {
        Client client = clientRepository.findById(1L).orElseThrow();
        client.setName("newName");
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.get(0).getName(), "newName");
    }

    @Test
    public void delete() throws Exception {
        clientRepository.deleteById(3L);
        List<Client> clients = clientRepository.findAll();
        assertEquals(2, clients.size());
    }
}
















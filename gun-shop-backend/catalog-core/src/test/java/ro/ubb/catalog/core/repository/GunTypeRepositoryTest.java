package ro.ubb.catalog.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.ITConfig;
import ro.ubb.catalog.core.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data-guntypes.xml")
public class GunTypeRepositoryTest {

    @Autowired
    private GunTypeRepository gunTypeRepository;

    @Autowired
    private GunProviderRepository gunProviderRepository;

    @Test
    public void findAll() {
        List<GunType> all = gunTypeRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    public void add() {
        GunProvider provider = new GunProvider("name", "speciality", 3, null);
        gunProviderRepository.save(provider);
        GunType gunType = GunType.builder()
                .name("newGun")
                .category(Category.RIFLE)
                .gunProvider(provider)
                .build();
        gunType.setId(3L);
        gunTypeRepository.save(gunType);
        List<GunType> all = gunTypeRepository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    @Transactional
    public void update() throws Exception {
        GunType gunType = gunTypeRepository.findById(1L).orElseThrow();
        gunType.setName("newName");
        List<GunType> all = gunTypeRepository.findAll();
        assertEquals(all.get(0).getName(), "newName");
    }

    @Test
    public void delete() throws Exception {
        gunTypeRepository.deleteById(2L);
        List<GunType> all = gunTypeRepository.findAll();
        assertEquals(1, all.size());
    }
}

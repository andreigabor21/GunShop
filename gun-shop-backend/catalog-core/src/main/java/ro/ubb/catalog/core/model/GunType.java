package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "gun_type")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class GunType extends BaseEntity<Long> {

    private String name;

    private Category category;

    @ManyToOne
    private GunProvider gunProvider;


    @OneToMany(mappedBy = "gunType",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Set<Rental> rentalSet = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                rentalSet.stream()
                        .map(Rental::getClient)
                        .collect(Collectors.toSet())
        );
    }

    public void addClient(Client client) {
        Rental rental = new Rental();
        rental.setClient(client);
        rental.setGunType(this);
        rentalSet.add(rental);
    }

    public void addPrice(Client client, Integer price) {
        Rental rental = new Rental();
        rental.setClient(client);
        rental.setPrice(price);
        rental.setGunType(this);
        rentalSet.add(rental);
    }
}


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
    private Set<ClientGun> clientGunSet = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                clientGunSet.stream()
                        .map(ClientGun::getClient)
                        .collect(Collectors.toSet())
        );
    }

    public void addClient(Client client) {
        ClientGun clientGun = new ClientGun();
        clientGun.setClient(client);
        clientGun.setGunType(this);
        clientGunSet.add(clientGun);
    }

    public void addPrice(Client client, Integer price) {
        ClientGun clientGun = new ClientGun();
        clientGun.setClient(client);
        clientGun.setPrice(price);
        clientGun.setGunType(this);
        clientGunSet.add(clientGun);
    }
}


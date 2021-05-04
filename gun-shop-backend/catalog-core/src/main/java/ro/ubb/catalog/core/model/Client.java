package ro.ubb.catalog.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long> {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d/M/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<ClientGun> clientGunSet = new HashSet<>();

    public Set<GunType> getGunTypes() {
        clientGunSet = clientGunSet == null ? new HashSet<>() : clientGunSet;
        return Collections.unmodifiableSet(
                this.clientGunSet.stream().
                        map(ClientGun::getGunType).
                        collect(Collectors.toSet()));
    }

    public void addGunType(GunType gunType) {
        ClientGun clientGun = new ClientGun();
        clientGun.setClient(this);
        clientGun.setGunType(gunType);
        clientGunSet.add(clientGun);
    }

    public void addGunTypes(Set<GunType> gunTypes) {
        gunTypes.forEach(this::addGunType);
    }

    public void addPrice(GunType gunType, Integer price) {
        ClientGun clientGun = new ClientGun();
        clientGun.setGunType(gunType);
        clientGun.setPrice(price);
        clientGun.setClient(this);
        clientGunSet.add(clientGun);
    }
}

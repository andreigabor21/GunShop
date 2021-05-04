package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_gun")
@IdClass(ClientGunPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Rental implements Serializable {
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client client;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "guntype_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GunType gunType;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString() {
        return "ClientGun{" +
                "price=" + price +
                '}';
    }
}

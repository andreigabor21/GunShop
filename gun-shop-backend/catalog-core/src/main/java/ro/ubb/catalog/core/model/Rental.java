package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "rental")
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
    private Client client;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "guntype_id")
    private GunType gunType;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString() {
        return "Rental{" +
                "client=" + client.getName() +
                ", gunType=" + gunType.getName() +
                ", price=" + price +
                '}';
    }



}

package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gun_provider")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class GunProvider extends BaseEntity<Long> {

    private String name;

    private String speciality;

    private int reputation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "gunprovider_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<GunType> gunTypes = new ArrayList<>();

    public void addGunType(GunType gunType){
        gunTypes.add(gunType);
    }

    public void removeGunType(GunType gunType){
        gunTypes.remove(gunType);
    }
}

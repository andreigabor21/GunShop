package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gun_provider")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GunProvider that = (GunProvider) o;
        return reputation == that.reputation && Objects.equals(name, that.name) && Objects.equals(speciality, that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speciality, reputation);
    }
}

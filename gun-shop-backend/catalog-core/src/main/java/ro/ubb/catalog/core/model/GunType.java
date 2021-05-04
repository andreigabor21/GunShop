package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gun_type")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class GunType extends BaseEntity<Long> {

    private String name;

    private Category category;

    @ManyToOne
    private GunProvider gunProvider;
}


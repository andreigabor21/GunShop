package ro.ubb.catalog.web.dto;

import lombok.*;
import ro.ubb.catalog.core.model.Address;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto{

    private String name;
    private LocalDate dateOfBirth;
    private Address address;

    private Set<Long> gunTypes;
}
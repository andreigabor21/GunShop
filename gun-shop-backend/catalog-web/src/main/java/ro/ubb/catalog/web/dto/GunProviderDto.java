package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GunProviderDto extends BaseDto{
    private String name;
    private String speciality;
    private int reputation;
    private List<GunTypeDto> gunTypeDtos = new ArrayList<>();

    //from
}

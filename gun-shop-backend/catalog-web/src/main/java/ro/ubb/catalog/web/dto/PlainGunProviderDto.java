package ro.ubb.catalog.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlainGunProviderDto extends BaseDto{
    private String name;
    private String speciality;
    private int reputation;
}

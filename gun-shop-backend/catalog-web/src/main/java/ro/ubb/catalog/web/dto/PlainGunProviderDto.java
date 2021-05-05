package ro.ubb.catalog.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class PlainGunProviderDto extends BaseDto{
    private String name;
    private String speciality;
    private int reputation;
}

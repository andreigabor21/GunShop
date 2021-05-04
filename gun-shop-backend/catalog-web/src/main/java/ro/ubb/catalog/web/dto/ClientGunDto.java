package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClientGunDto {
    private Long clientId;
    private Long gunTypeId;
    private Integer price;
}

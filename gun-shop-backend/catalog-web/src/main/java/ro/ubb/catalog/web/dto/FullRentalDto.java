package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FullRentalDto {
    private Long clientId;
    private String clientName;
    private Long gunTypeId;
    private String gunTypeName;
    private Integer price;
}

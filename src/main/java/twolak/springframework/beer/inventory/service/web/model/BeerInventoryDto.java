package twolak.springframework.beer.inventory.service.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 *
 * @author twolak
 */
public class BeerInventoryDto {
    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private UUID beerId;
    private String upc;
    private Integer quantityOnHand;
}

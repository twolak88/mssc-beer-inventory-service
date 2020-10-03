package twolak.springframework.beer.inventory.service.web.mappers;

import org.mapstruct.Mapper;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;
import twolak.springframework.beer.inventory.service.web.model.BeerInventoryDto;

/**
 *
 * @author twolak
 */
@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {
    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDto);
    
    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}

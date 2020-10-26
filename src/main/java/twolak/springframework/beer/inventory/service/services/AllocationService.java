package twolak.springframework.beer.inventory.service.services;

import twolak.springframework.brewery.model.BeerOrderDto;

/**
 *
 * @author twolak
 */
public interface AllocationService {
    
    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);
}

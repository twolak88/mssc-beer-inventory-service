package twolak.springframework.beer.inventory.service.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;
import twolak.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import twolak.springframework.brewery.model.BeerOrderDto;
import twolak.springframework.brewery.model.BeerOrderLineDto;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final BeerInventoryRepository beerInventoryRepository;
    
    @Override
    public Boolean allocateOrder(BeerOrderDto beerOrderDto) {
        log.debug("Allocating Order Id: " + beerOrderDto.getId());
        
        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();
        
        beerOrderDto.getBeerOrderLines().forEach((beerOrderLine) -> {
            if ((beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0) 
                    - (beerOrderLine.getOrderQuantityAllocated() != null ? beerOrderLine.getOrderQuantityAllocated() : 0) > 0) {
                allocateBeerOrderLine(beerOrderLine);
            }
            totalOrdered.set(totalOrdered.get() + beerOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + 
                    (beerOrderLine.getOrderQuantityAllocated() != null ? beerOrderLine.getOrderQuantityAllocated() : 0));
        });
        
        log.debug("Total ordered: " + totalOrdered.get() + " Total allocated: " + totalAllocated.get());
        
        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLine) {
        List<BeerInventory> beerInventorys = this.beerInventoryRepository.findAllByUpc(beerOrderLine.getUpc());
        
        beerInventorys.forEach((beerInventory) -> {
            int inventory = beerInventory.getQuantityOnHand() != null ? beerInventory.getQuantityOnHand() : 0;
            int orderQuantity = beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0;
            int allocatedQuantity = beerOrderLine.getOrderQuantityAllocated() != null ? beerOrderLine.getOrderQuantityAllocated() : 0;
            int quantityToAllocate = orderQuantity - allocatedQuantity;
            
            if (inventory >= quantityToAllocate) {
                inventory = inventory - quantityToAllocate;
                beerOrderLine.setOrderQuantityAllocated(orderQuantity);
                beerInventory.setQuantityOnHand(inventory);
                
                beerInventoryRepository.save(beerInventory);
            } else if (inventory > 0) {
                beerOrderLine.setOrderQuantityAllocated(allocatedQuantity + inventory);
                beerInventory.setQuantityOnHand(0);
                
                beerInventoryRepository.delete(beerInventory);
            }
        });
    }
}

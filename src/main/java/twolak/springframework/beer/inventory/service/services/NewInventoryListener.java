package twolak.springframework.beer.inventory.service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import twolak.springframework.beer.inventory.service.config.JmsConfig;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;
import twolak.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import twolak.springframework.common.events.NewInventoryEvent;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {
    
    private final BeerInventoryRepository beerInventoryRepository;
    
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent) {
        log.debug("Got Inventory: " + newInventoryEvent.toString());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(newInventoryEvent.getBeerDto().getId())
                .upc(newInventoryEvent.getBeerDto().getUpc())
                .quantityOnHand(newInventoryEvent.getBeerDto().getQuantityOnHand())
                .build());
    }
}

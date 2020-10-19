package twolak.springframework.beer.inventory.service.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;
import twolak.springframework.beer.inventory.service.repositories.BeerInventoryRepository;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
//@Component
public class BeerInventoryBootstrap implements CommandLineRunner{
    
    private static final String BEER_1_UPC = "0631234200036";
    private static final String BEER_2_UPC = "0631234300019";
    private static final String BEER_3_UPC = "0083783375213";
    private static final String BEER_4_UPC = "0083783375214";
    private static final String BEER_5_UPC = "0083783375215";
    
    private final BeerInventoryRepository beerInventoryRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (this.beerInventoryRepository.count() == 0) {
            loadInitialInventories();
        }
    }
    
    private void loadInitialInventories() {
        this.beerInventoryRepository.save(BeerInventory
                .builder()
//                .beerId(null)
                .upc(BEER_1_UPC)
                .quantityOnHand(60)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
//                .beerId(null)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
//                .beerId(null)
                .upc(BEER_3_UPC)
                .quantityOnHand(55)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
//                .beerId(null)
                .upc(BEER_4_UPC)
                .quantityOnHand(59)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
//                .beerId(null)
                .upc(BEER_5_UPC)
                .quantityOnHand(73)
                .build());
        
        log.debug("Loaded Inventory. Record count: " + this.beerInventoryRepository.count());
    }
    
}

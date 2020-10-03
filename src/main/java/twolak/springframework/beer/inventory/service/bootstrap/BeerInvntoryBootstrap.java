package twolak.springframework.beer.inventory.service.bootstrap;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;
import twolak.springframework.beer.inventory.service.repositories.BeerInventoryRepository;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerInvntoryBootstrap implements CommandLineRunner{
    
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
    
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
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(50)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build());
        
        this.beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_3_UUID)
                .upc(BEER_3_UPC)
                .quantityOnHand(50)
                .build());
        
        log.debug("Loaded Inventory. Record count: " + this.beerInventoryRepository.count());
    }
    
}

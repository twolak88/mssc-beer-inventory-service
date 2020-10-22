package twolak.springframework.beer.inventory.service.web.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twolak.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import twolak.springframework.beer.inventory.service.web.mappers.BeerInventoryMapper;
import twolak.springframework.brewery.model.BeerInventoryDto;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer/{beerId}/inventory")
public class BeerInventoryController {
    
    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;
    
    @GetMapping
    public List<BeerInventoryDto> listBeersById(@PathVariable("beerId") UUID beerId) {
        log.debug("Finding Inventory for beerId:" + beerId);
        
        return this.beerInventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(this.beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }
}

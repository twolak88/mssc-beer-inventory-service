package twolak.springframework.beer.inventory.service.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import twolak.springframework.beer.inventory.service.domain.BeerInventory;

/**
 *
 * @author twolak
 */
public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID>{
    List<BeerInventory> findAllByBeerId(UUID beerId);
}

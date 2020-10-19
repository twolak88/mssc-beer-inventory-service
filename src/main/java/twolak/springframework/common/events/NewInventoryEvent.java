package twolak.springframework.common.events;

import lombok.NoArgsConstructor;
import twolak.springframework.common.events.model.BeerDto;

/**
 *
 * @author twolak
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    private static final long serialVersionUID = 1632128619517083212L;
    
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

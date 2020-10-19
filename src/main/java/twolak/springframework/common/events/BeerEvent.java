package twolak.springframework.common.events;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import twolak.springframework.common.events.model.BeerDto;

/**
 *
 * @author twolak
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 4872277003464505134L;
    
    private BeerDto beerDto;
}

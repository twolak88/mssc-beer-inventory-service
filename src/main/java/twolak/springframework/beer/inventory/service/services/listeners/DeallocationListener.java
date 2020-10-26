package twolak.springframework.beer.inventory.service.services.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import twolak.springframework.beer.inventory.service.config.JmsConfig;
import twolak.springframework.beer.inventory.service.services.AllocationService;
import twolak.springframework.brewery.model.events.DeallocateBeerOrderRequest;

/**
 *
 * @author twolak
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {
    
    private final AllocationService allocationService;
    
    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateBeerOrderRequest deallocateBeerOrderRequest) {
        log.debug("Deallocating... resources for beer! Id: " + deallocateBeerOrderRequest.getBeerOrderDto().getId());
        allocationService.deallocateOrder(deallocateBeerOrderRequest.getBeerOrderDto());
    }
}

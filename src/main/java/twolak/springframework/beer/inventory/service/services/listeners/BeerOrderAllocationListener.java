package twolak.springframework.beer.inventory.service.services.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import twolak.springframework.beer.inventory.service.config.JmsConfig;
import twolak.springframework.beer.inventory.service.services.AllocationService;
import twolak.springframework.brewery.model.events.AllocateBeerOrderRequest;
import twolak.springframework.brewery.model.events.AllocateBeerOrderResult;

/**
 *
 * @author twolak
 */
@Slf4j 
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationListener {
    
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;
    
    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateBeerOrderRequest allocateBeerOrderRequest) {
        AllocateBeerOrderResult.AllocateBeerOrderResultBuilder builder = AllocateBeerOrderResult.builder();
        builder.beerOrderDto(allocateBeerOrderRequest.getBeerOrderDto());
        
        try {
            Boolean allocationResult = this.allocationService.allocateOrder(allocateBeerOrderRequest.getBeerOrderDto());
            builder.pendingInventory(!allocationResult);
            builder.allocationError(Boolean.FALSE);
        } catch (Exception e) {
            log.error("Allocation failed for Order Id: " + allocateBeerOrderRequest.getBeerOrderDto().getId(), e);
            builder.allocationError(Boolean.TRUE);
        }
        this.jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESULT_QUEUE, builder.build());
    }
}

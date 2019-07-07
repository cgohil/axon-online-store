package io.entsys.lab.axon.order.event

import io.entsys.lab.axon.order.OrderCreateEvent
import io.entsys.lab.axon.order.persistence.Order
import io.entsys.lab.axon.order.persistence.OrderRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("OrderEventsProcessor")
class OrderEventHandler(val orderRepository: OrderRepository) {

    @EventHandler
    fun handle(event: OrderCreateEvent) {
        this.orderRepository.save(Order(null, event.orderId, event.productId, event.quantity))
    }
}


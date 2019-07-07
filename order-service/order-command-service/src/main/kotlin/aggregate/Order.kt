@file:JvmName("Order")
package io.entsys.lab.axon.order.aggregate

import io.entsys.lab.axon.order.OrderCancelEvent
import io.entsys.lab.axon.order.OrderConfirmEvent
import io.entsys.lab.axon.order.OrderCreateEvent
import io.entsys.lab.axon.order.OrderRejectEvent
import io.entsys.lab.axon.order.command.CreateOrderCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class Order {
    @AggregateIdentifier
    var orderId: UUID? = null
    var productId: Long? = null
    var quantity: Int? = null
    var state: OrderState? = null

    @CommandHandler
    constructor(cmd: CreateOrderCommand) {
        AggregateLifecycle.apply(OrderCreateEvent(cmd.orderId, cmd.productId, cmd.quantity))
    }

    @EventSourcingHandler
    fun on(event: OrderCreateEvent) {
        this.orderId = event.orderId
        this.productId = event.productId
        this.quantity = event.quantity
        this.state = OrderState.PENDING
    }

    @EventSourcingHandler
    fun on(event: OrderConfirmEvent) {
        this.state = OrderState.CONFIRMED
    }

    @EventSourcingHandler
    fun on(event: OrderRejectEvent) {
        this.state = OrderState.REJECTED
    }

    @EventSourcingHandler
    fun on(event: OrderCancelEvent) {
        this.state = OrderState.CANCLLED
    }

}




enum class OrderState {PENDING, CONFIRMED, REJECTED, CANCLLED, DISPATCHED}
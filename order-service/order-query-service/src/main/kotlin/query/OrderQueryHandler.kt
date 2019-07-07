package io.entsys.lab.axon.order.query

import io.entsys.lab.axon.order.api.Order
import io.entsys.lab.axon.order.persistence.OrderRepository
import org.axonframework.queryhandling.QueryHandler
import java.util.*

class OrderQueryHandler(private val repository: OrderRepository) {

    @QueryHandler
    fun on(query: OrderQuery): Order {
        val order = repository.findByOrderId(query.orderId)
        return Order(order.orderId, order.productId, order.quantity)
    }
}

data class OrderQuery(val orderId: UUID)
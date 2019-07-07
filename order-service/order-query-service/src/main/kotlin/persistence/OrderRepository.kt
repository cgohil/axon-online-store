package io.entsys.lab.axon.order.persistence

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByOrderId(orderId: UUID): Order
}

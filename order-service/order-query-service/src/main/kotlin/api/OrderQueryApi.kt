package io.entsys.lab.axon.order.api

import io.entsys.lab.axon.order.query.OrderQuery
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

class OrderQueryApi(val queryGateway: QueryGateway) {

    @GetMapping("/order/{id}")
    fun queryOrder(@PathVariable("id") id: UUID): ResponseEntity<Order> {
       queryGateway.query(OrderQuery(id), Order::class.java).join().let { order ->
           return ResponseEntity.ok(order)
       }
    }
}

data class Order(val  orderId: UUID, val productId: Long, val quantity: Int)

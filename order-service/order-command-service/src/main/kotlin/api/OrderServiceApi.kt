package io.entsys.lab.axon.order.api

import io.entsys.lab.axon.order.command.CreateOrderCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
class OrderServiceApi(val commandGateway: CommandGateway) {

    @PostMapping("/order")
    fun createOrder(@RequestBody order: Order): ResponseEntity<*> {
        val orderId = UUID.randomUUID()
        commandGateway.send<Any>(CreateOrderCommand(orderId, order.productId, order.quantity))
        return ResponseEntity.created(URI.create("/order/$orderId")).build<Any>()
    }
}
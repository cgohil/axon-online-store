package io.entsys.lab.axon.order.persistence

import io.entsys.lab.axon.order.OrderCreateEvent
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order(
        @Id
        @GeneratedValue
        @Column(name = "ID")
        var id: Long?,

        @Column(name = "ORDER_ID")
        val orderId: UUID,

        @Column(name = "PRODUCT_ID")
        val productId: Long,

        @Column(name = "QUANTITY")
        val quantity: Int

) : Serializable


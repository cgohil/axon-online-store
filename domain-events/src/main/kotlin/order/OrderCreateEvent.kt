@file:JvmName("OrderCreateEvent")

package io.entsys.lab.axon.order

import java.util.UUID

data class OrderCreateEvent(
        val orderId: UUID,
        val productId: Long,
        val quantity: Int
)
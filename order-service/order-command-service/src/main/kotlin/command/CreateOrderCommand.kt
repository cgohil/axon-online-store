@file:JvmName("CreateOrderCommand")

package io.entsys.lab.axon.order.command

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateOrderCommand(
        @TargetAggregateIdentifier
        val orderId: UUID,
        val productId: Long,
        val quantity: Int
)
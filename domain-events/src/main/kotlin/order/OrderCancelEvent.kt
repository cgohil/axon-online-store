@file:JvmName("OrderCancelEvent")

package io.entsys.lab.axon.order

import java.util.UUID

data class OrderCancelEvent(val orderId: UUID)
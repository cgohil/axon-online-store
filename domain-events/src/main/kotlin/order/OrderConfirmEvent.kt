@file:JvmName("OrderConfirmEvent")

package io.entsys.lab.axon.order

import java.util.UUID

data class OrderConfirmEvent(val orderId: UUID)
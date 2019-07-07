@file:JvmName("OrderRejectEvent")

package io.entsys.lab.axon.order

import java.util.UUID

data class OrderRejectEvent(val orderId: UUID)
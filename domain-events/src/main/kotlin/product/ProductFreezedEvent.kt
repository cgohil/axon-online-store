@file:JvmName("ProductFreezedEvent")

package io.entsys.lab.axon.product

import java.util.UUID

data class ProductFreezedEvent (
        val productId: UUID
)

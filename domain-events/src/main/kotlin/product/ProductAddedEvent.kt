@file:JvmName("ProductAddedEvent")

package io.entsys.lab.axon.product

import java.util.UUID

data class ProductAddedEvent (
        val productId: UUID,
        val productName: String,
        val price: Double
)

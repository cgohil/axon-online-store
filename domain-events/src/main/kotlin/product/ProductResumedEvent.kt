@file:JvmName("ProductResumedEvent")

package io.entsys.lab.axon.product

import java.util.UUID

data class ProductResumedEvent (
        val productId: UUID
)
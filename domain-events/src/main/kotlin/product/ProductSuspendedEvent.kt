@file:JvmName("ProductSuspendedEvent")

package io.entsys.lab.axon.product

import java.util.UUID

class ProductSuspendedEvent (
        val productId: UUID
)
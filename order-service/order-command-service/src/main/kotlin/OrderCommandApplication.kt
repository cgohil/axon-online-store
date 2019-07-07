package io.entsys.lab.axon.order

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OrderCommandApplication

fun main(args: Array<String>) {
    SpringApplication.run(OrderCommandApplication::class.java)
}
package io.entsys.lab.axon.order.config

import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.springframework.context.annotation.Bean
import org.axonframework.spring.config.AxonConfiguration
import org.axonframework.extensions.kafka.eventhandling.DefaultKafkaMessageConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter
import org.axonframework.serialization.Serializer
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan(basePackages = ["io.entsys.lab.axon.order"])
open class EventSourcingConfiguration {

    @ConditionalOnMissingBean
    @Bean
    internal open fun kafkaMessageConverter(
            @Qualifier("eventSerializer") eventSerializer: Serializer): KafkaMessageConverter<String, ByteArray> {
        return DefaultKafkaMessageConverter.builder()
                .serializer(eventSerializer).build()
    }

    @Bean
    internal open fun eventStore(storageEngine: EventStorageEngine, configuration: AxonConfiguration): EmbeddedEventStore {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore::class.java, "eventStore"))
                .build()
    }

    // The `InMemoryEventStorageEngine` stores each event in memory
    @Bean
    internal open fun storageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

    @Bean
    internal open fun tokenSTore(): TokenStore {
        return InMemoryTokenStore()
    }

}

package io.entsys.lab.axon.order.config

import com.mongodb.MongoClient
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.SnapshotTriggerDefinition
import org.axonframework.eventsourcing.Snapshotter
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.messaging.annotation.ParameterResolverFactory
import org.axonframework.spring.config.AxonConfiguration
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors

@Configuration
@ComponentScan(basePackages = ["io.entsys.lab.axon.order"])
open class EventSourcingConfiguration {

    @Bean
    internal open fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build()
    }

    @Bean
    internal open fun eventStore(storageEngine: EventStorageEngine, configuration: AxonConfiguration): EmbeddedEventStore {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore::class.java, "eventStore"))
                .build()
    }

    @Bean
    internal open fun snapshotter(parameterResolverFactory: ParameterResolverFactory, eventStore: EventStore,
                    transactionManager: TransactionManager): SpringAggregateSnapshotter {
        val executor = Executors.newSingleThreadExecutor() //Or any other executor of course
        return SpringAggregateSnapshotter.builder()
                .eventStore(eventStore)
                .parameterResolverFactory(parameterResolverFactory)
                .executor(executor)
                .transactionManager(transactionManager).build()
    }

    @Bean
    internal open fun snapshotTriggerDefinition(snapshotter: Snapshotter): SnapshotTriggerDefinition {
        return EventCountSnapshotTriggerDefinition(snapshotter, 3)
    }
}


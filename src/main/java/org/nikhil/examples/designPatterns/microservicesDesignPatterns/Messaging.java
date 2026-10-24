package org.nikhil.examples.designPatterns.microservicesDesignPatterns;

public class Messaging {

    // ** Kafka **
    // Kafka is a distributed streaming platform that allows you to publish and subscribe to streams of records, similar to a message queue or enterprise messaging system.
    // It is designed for high-throughput, fault-tolerant and scalable messaging.
    // Kafka is often used for building real-time data pipelines and streaming applications. It can handle large volumes of data and is suitable for scenarios
    // where you need to process and analyze data in real-time.
    // Kafka is a good choice for scenarios where you need to process and analyze data in real-time, such as log aggregation, event sourcing and stream processing.
    // It is also suitable for building event-driven architectures and microservices that require asynchronous communication.

    // Kafka is not a good choice for scenarios where you need low-latency messaging or where you need to guarantee message delivery.
    // Why kafka is not suitable where you need guarantee message delivery? Because Kafka is designed for high-throughput and scalability, it does not provide strong
    // guarantees for message delivery. While Kafka does provide some level of durability and fault tolerance, it is not designed to guarantee that every message will
    // be delivered to every consumer. In scenarios where you need guaranteed message delivery, you may want to consider using a different messaging system that
    // provides stronger guarantees, such as RabbitMQ or ActiveMQ.
    // It is also not suitable for scenarios where you need to process messages in a specific order as Kafka does not guarantee message ordering.

    // How does Kafka work? Name its core components.
    // Kafka works by organizing data into topics, which are divided into partitions. Producers write data to topics and consumers read data from topics.
    // Kafka uses a distributed architecture, where multiple brokers manage the storage and retrieval of data.
    // The core components of Kafka are:
    // 1. Producer: A producer is a client application that publishes records to Kafka topics. Producers are responsible for sending data to Kafka and can choose which partition
    // to send the data to.
    // 2. Consumer: A consumer is a client application that subscribes to Kafka topics and reads records from them. Consumers can be part of a consumer group, which allows
    // for load balancing and parallel processing of data.
    // 3. Broker: A broker is a Kafka server that stores and serves data.
    // 4. Topic: A topic is a category or feed name to which records are published. Topics are divided into partitions, which allow for parallel processing of data.
    // 5. Partition: A partition is a unit of parallelism in Kafka. Each partition is an ordered, immutable sequence of records that is continually appended to.
    // Partitions allow for scalability and fault tolerance in Kafka.
    // 6. Zookeeper: Zookeeper is a centralized service that manages the configuration and coordination of Kafka brokers. It is responsible for leader election,
    // cluster membership, and maintaining metadata about topics and partitions.
    // 7. Kafka Connect: Kafka Connect is a tool for integrating Kafka with external systems, such as databases, key-value stores, and file systems. It provides a framework for
    // building and running connectors that move data between Kafka and other systems.
    // 8. Kafka Streams: Kafka Streams is a client library for building real-time stream processing applications that read data from Kafka topics, process it, and write the results
    // back to Kafka topics.
    // 9. Schema Registry: Schema Registry is a service that manages and enforces schemas for data in Kafka topics. It allows producers and consumers to validate and evolve schemas
    // over time, ensuring compatibility between different versions of data.
    // 10. Kafka REST Proxy: Kafka REST Proxy is a RESTful interface for interacting with Kafka. It allows clients to produce and consume messages over HTTP, making it easier to
    // integrate Kafka with web applications and other systems that use HTTP.
    // 11. Kafka Control Center: Kafka Control Center is a web-based management and monitoring tool for Kafka. It provides a user interface for managing topics, monitoring cluster health
    // and analyzing data streams in real-time.
    // 12. Kafka Streams API: Kafka Streams API is a Java library for building stream processing applications that read data from Kafka topics, process it and write the results back to Kafka topics.
    // It provides a high-level DSL for defining stream processing logic and supports stateful processing, windowing, and joins.
    // 13. Kafka Connect API: Kafka Connect API is a framework for building and running connectors that move data between Kafka and external systems. It provides a simple interface
    // for defining source and sink connectors, as well as a runtime environment for executing them.
    // 14. Kafka Admin API: Kafka Admin API is a Java library for managing and configuring Kafka clusters. It provides a programmatic interface for creating and deleting topics,
    // managing partitions and configuring broker settings.
    // 15. Kafka Streams Processor API: Kafka Streams Processor API is a low-level API for building custom stream processing applications that read data from Kafka topics, process it
    // and write the results back to Kafka topics. It provides a flexible interface for defining processing logic and supports stateful processing, windowing and joins.
    // 16. Kafka Streams State Store API: Kafka Streams State Store API is a low-level API for managing state in stream processing applications. It provides a simple interface
    // for storing and retrieving state, as well as support for fault tolerance and recovery.

    // The producer is publishing, but the consumer receives nothing. Diagnose the root cause.
    // There could be several reasons why the consumer is not receiving messages from the producer in Kafka. Here are some common causes and their solutions:
    // 1. Topic does not exist: Ensure that the topic you are publishing to exists. You can create the topic using the Kafka command-line tools or programmatically using the Admin API.
    // 2. Consumer group mismatch: Ensure that the consumer is part of the same consumer group as the producer. If the consumer is in a different group, it will not receive messages
    // from the producer.
    // 3. Offset management: Ensure that the consumer is configured to start reading from the correct offset. If the consumer is set to read from the latest offset, it will not receive messages that were published before it started. You can configure the consumer to read from the earliest offset or manage offsets manually.
    // 4. Network issues: Ensure that there are no network issues preventing the consumer from connecting to the Kafka broker. Check firewall settings, network configurations, and ensure that the broker is reachable from the consumer.
    // 5. Serialization issues: Ensure that the producer and consumer are using compatible serializers and deserializers for the message format. If the producer is sending messages
    // in a format that the consumer cannot deserialize, the consumer will not be able to process the messages.
    // 6. Broker configuration: Ensure that the Kafka broker is configured correctly and is running. Check the broker logs for any errors or warnings that may indicate issues with message delivery.
    // 7. Consumer lag: Check if the consumer is lagging behind the producer. If the consumer is not able to keep up with the rate of message production, it may not receive messages
    // in a timely manner. You can monitor consumer lag using Kafka monitoring tools or metrics.
    // 8. Message retention: Ensure that the messages are not being deleted from the topic before the consumer has a chance to read them. Check the topic's retention policy and adjust
    // it if necessary.
    // 9. Consumer polling: Ensure that the consumer is actively polling for messages. If the consumer is not calling the poll() method regularly, it will not receive messages from the broker.
    // 10. Logging and monitoring: Enable logging and monitoring for both the producer and consumer to identify any issues or errors that may be occurring during message production or consumption.
    // By systematically checking these potential causes, you should be able to diagnose and resolve the issue preventing the consumer from receiving messages from the producer in Kafka.

    // How does Kafka achieve high throughput and durability compared to traditional message queues, and what is the role of Partitions, Consumer Groups and Offsets?
    // Kafka achieves high throughput and durability through its distributed architecture, efficient data storage and message processing mechanisms. Here's how it works:
    // 1. Partitions: Kafka topics are divided into partitions, which allow for parallel processing of messages. Each partition is an ordered, immutable sequence of records that can be read independently by multiple consumers. This enables Kafka to handle a large volume of messages and distribute the load across multiple brokers, improving throughput and scalability.
    // 2. Consumer Groups: Kafka allows consumers to be organized into consumer groups. Each consumer in a group reads messages from different partitions of a topic,
    // allowing for parallel processing of messages. This enables Kafka to scale horizontally, as more consumers can be added to a group to handle increased message load.
    // Additionally, consumer groups provide fault tolerance, as if one consumer fails, another consumer in the group can take over its partitions and continue processing messages.
    // 3. Offsets: Kafka maintains offsets for each consumer, which track the position of the last consumed message in a partition. This allows consumers to resume processing from where
    // they left off in case of failures or restarts. Offsets can be committed either automatically or manually, providing flexibility in message processing and ensuring that messages
    // are not lost or duplicated. This contributes to Kafka's durability, as messages can be retained for a configurable period of time, allowing consumers to reprocess messages if needed.
    // Overall, Kafka's architecture, with its use of partitions, consumer groups and offsets, enables high throughput, durability and fault tolerance,
    // making it suitable for real-time data processing and streaming applications.

    // How do you guarantee exactly-once semantics (EOS) in Kafka, and how does Kafka handle rebalancing and partition assignment?
    // Kafka guarantees exactly-once semantics (EOS) through a combination of idempotent producers, transactional messaging and careful offset management. Here's how it works:
    // 1. Idempotent Producers: Kafka producers can be configured to be idempotent, meaning that they can safely retry sending messages without the risk of duplicating them.
    // This is achieved by assigning a unique producer ID and sequence number to each message, allowing the broker to recognize and discard duplicate messages.
    // 2. Transactional Messaging: Kafka supports transactions, allowing producers to send multiple messages as part of a single atomic operation. This ensures that either all messages
    // in the transaction are successfully written to the topic, or none of them are, maintaining data consistency.
    // Consumers can also read messages from a transaction and commit offsets only after the transaction is complete, ensuring that they process messages exactly once.
    // 3. Offset Management: Kafka consumers can manage offsets manually, allowing them to commit offsets only after successfully processing messages.
    // This ensures that if a consumer fails or restarts, it can resume processing from the last committed offset, avoiding duplicate processing of messages.

    // Rebalancing and partition assignment in Kafka occur when the membership of a consumer group changes, such as when a new consumer joins or an existing consumer leaves the group.
    // Kafka uses a partition assignment strategy to distribute partitions among the available consumers in the group.
    // The default strategy is range assignment, where partitions are assigned to consumers based on their order in the topic.
    // Other strategies, such as round-robin or sticky assignment, can also be used.
    // During rebalancing, Kafka temporarily pauses message consumption and redistributes partitions among the consumers, ensuring that each partition is assigned to only one consumer
    // at a time. This process ensures that messages are processed in a balanced and fault-tolerant manner, while maintaining exactly-once semantics through careful offset management
    // and transactional messaging.

    // What is heartbeat in Kafka? Heartbeat is a mechanism used by Kafka consumers to maintain their membership in a consumer group and to signal their liveness to the Kafka broker.
    // Each consumer sends periodic heartbeat messages to the broker, indicating that it is still active and able to process messages.
    // If a consumer fails to send heartbeats within a specified timeout period, the broker considers it dead and triggers a rebalance of the consumer group,
    // redistributing its partitions to other active consumers. This ensures that the consumer group remains balanced and that messages are processed in a fault-tolerant manner,
    // even in the presence of consumer failures. Heartbeats are an essential part of Kafka's consumer group management and help maintain the overall health and stability of the system.
    // Heartbeat URL: The heartbeat URL is an endpoint provided by the Kafka broker that consumers use to send their heartbeat messages.
    // It is typically in the format of "http://<broker-host>:<broker-port>/heartbeat" and is used by consumers to signal their liveness to the broker.
    // The broker uses this information to manage consumer group membership and trigger rebalances when necessary.
    // How consumer sends heartbeat to broker? Consumers send heartbeat messages to the broker by making HTTP requests to the heartbeat URL at regular intervals.
    // The frequency of these heartbeat messages is configurable and can be adjusted based on the desired level of responsiveness and fault tolerance.
    // When a consumer sends a heartbeat, it includes its consumer group ID and other relevant information, allowing the broker to track the consumer's status and
    // manage its membership in the group. If the broker does not receive heartbeats from a consumer within the specified timeout period, it considers the consumer dead and
    // triggers a rebalance of the consumer group, redistributing its partitions to other active consumers.
    // How to avoid heartbeat timeout in Kafka? To avoid heartbeat timeouts in Kafka, you can take the following steps:
    // 1. Increase the heartbeat interval: You can configure the heartbeat interval for consumers to be longer, allowing them more time to send heartbeats before being considered dead.
    // 2. Optimize consumer processing: Ensure that your consumer application is processing messages efficiently and not blocking for extended periods of time, which could delay heartbeat messages.
    // 3. Use a dedicated thread for heartbeats: Consider using a separate thread or executor to handle heartbeat messages, ensuring that they are sent on time even if the main processing thread is busy.
    // 4. Monitor consumer health: Implement monitoring and alerting for your consumer applications to detect any issues that may cause heartbeat timeouts, such as high CPU usage
    // or memory leaks.
    // 5. Adjust session timeout: You can configure the session timeout for consumers to be longer, allowing them more time to send heartbeats before being considered dead.
    // However, this may increase the time it takes to detect failed consumers.
    // 6. Use a load balancer: If you have multiple consumers in a group, consider using a load balancer to distribute the workload evenly and
    // prevent any single consumer from being overwhelmed and missing heartbeats.



    // Delivery Semantics in Kafka:
    // Kafka provides three delivery semantics: at-most-once, at-least-once, and exactly-once.
    // 1. At-Most-Once: In this mode, messages may be lost, but they will never be delivered more than once. This is achieved by not retrying message delivery in case of failures. It is suitable for scenarios where occasional message loss is acceptable, such as logging or monitoring.
    // 2. At-Least-Once: In this mode, messages are guaranteed to be delivered at least once, but they may be delivered multiple times in case of failures. This is achieved by retrying message delivery until it is acknowledged by the consumer. It is suitable for scenarios where message loss is unacceptable, such as financial transactions or order processing.
    // 3. Exactly-Once: In this mode, messages are guaranteed to be delivered exactly once, even in the presence of failures. This is achieved through idempotent producers, transactional messaging, and careful offset management. It is suitable for scenarios where both message loss and duplication are unacceptable, such as payment processing or inventory management.

    // Sample Consumer Code:
    // Properties props = new Properties();
    // props.put("bootstrap.servers", "localhost:9092");
    // props.put("group.id", "test");
    // props.put("enable.auto.commit", "true");
    // props.put("auto.commit.interval.ms", "1000");
    // props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    // props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

    // consumer.subscribe(Arrays.asList("my-topic"));

    // @KafkaListener(topics = "my-topic", groupId = "test")
    // public void listen(String message) {
    //     System.out.println("Received message: " + message);
    // }
    // In this example, the consumer is configured to automatically commit offsets after processing messages. The @KafkaListener annotation is used to define a method that will be called whenever a message is received on the specified topic. The message is passed as a parameter to the method, allowing for custom processing logic to be implemented.

    // What if I have to acknowledge and commit the offset manually? In that case, you can set enable.auto.commit to false and use the Acknowledgment object provided by the @KafkaListener method to manually acknowledge and commit the offset after processing the message. Here's an example:
    // @KafkaListener(topics = "my-topic", groupId = "test")
    // public void listen(String message, Acknowledgment acknowledgment) {
    //     System.out.println("Received message: " + message);
    //     // Process the message
    //     acknowledgment.acknowledge(); // Manually acknowledge and commit the offset
    // }

    // @KafkaProducer and @KafkaConsumer are annotations provided by the Spring Kafka framework to simplify the configuration and usage of Kafka producers and consumers in a Spring application. They allow developers to easily create and manage Kafka producers and consumers without having to deal with low-level Kafka APIs.
    // @KafkaProducer is used to define a Kafka producer bean that can be injected into other components of the application. It allows developers to send messages to Kafka topics using a simple method call, without having to manually configure the producer properties or handle serialization and deserialization of messages.
    // @KafkaConsumer is used to define a Kafka consumer bean that can be injected into other components of the application. It allows developers to receive messages from Kafka topics using a simple method call, without having to manually configure the consumer properties or handle serialization and deserialization of messages.
    // kafa producer example:
    // @KafkaProducer(topics = "my-topic", groupId = "test")
    // public class MyKafkaProducer {
    //     private final KafkaTemplate<String, String> kafkaTemplate;
    //     public MyKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    //         this.kafkaTemplate = kafkaTemplate;
    //     }
    //     public void sendMessage(String message) {
    //         kafkaTemplate.send("my-topic", message);
    //     }
    // }

    // Customizing Kafka Producer and Consumer Properties:
    // You can customize Kafka producer and consumer properties by defining a configuration class that extends KafkaProperties and overrides the default properties. Here's an example:
    // @Configuration
    // public class KafkaConfig extends KafkaProperties {
    //     @Bean
    //     public ProducerFactory<String, String> producerFactory() {
    //         Map<String, Object> props = new HashMap<>(super.buildProducerProperties());
    //         props.put(ProducerConfig.ACKS_CONFIG, "all");
    //         return new DefaultKafkaProducerFactory<>(props);
    //     }
    //     @Bean
    //     public ConsumerFactory<String, String> consumerFactory() {
    //         Map<String, Object> props = new HashMap<>(super.buildConsumerProperties());
    //         props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //         return new DefaultKafkaConsumerFactory<>(props);
    //     }

    // Custom Partitioning and Serialization:
    // You can implement custom partitioning and serialization by creating your own classes that implement the Partitioner and Serializer interfaces provided by Kafka. Here's an example of a custom partitioner:
    // public class CustomPartitioner implements Partitioner {
    //     @Override
    //     public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
    //         // Custom partitioning logic based on the key or value
    //         return Math.abs(key.hashCode()) % cluster.partitionCountForTopic(topic);
    //     }
    //     @Override
    //     public void close() {}
    //     @Override
    //     public void configure(Map<String, ?> configs) {}

    //    public class CustomSerializer implements Serializer<MyCustomObject> {
    //     @Override
    //     public byte[] serialize(String topic, MyCustomObject data) {
    //         // Custom serialization logic for MyCustomObject
    //         return ...; // Convert MyCustomObject to byte array
    //     }
    //     @Override
    //     public void close() {}
    //     @Override
    //     public void configure(Map<String, ?> configs) {}

    // Polling and Backpressure Handling:
    // You can implement polling and backpressure handling by using the KafkaConsumer's poll() method and managing the flow of messages based on the consumer's processing capacity. Here's an example:
    //
    // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    // while (true) {
    //     ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    //     for (ConsumerRecord<String, String> record : records) {
    //         // Process the record
    //         processRecord(record);
    //     }
    //     // Commit offsets after processing the records
    //     consumer.commitSync();
    // }

    // How to handle message ordering and deduplication in Kafka?
    // Kafka provides message ordering guarantees within a partition, meaning that messages sent to the same partition will be consumed in the order they were produced. To ensure message ordering, you can use a key when producing messages, which will determine the partition to which the message is sent. All messages with the same key will be sent to the same partition, preserving their order.
    // For deduplication, you can implement idempotent producers and transactional messaging, as mentioned earlier. By using unique identifiers for messages and managing offsets carefully, you can ensure that messages are processed exactly once, even in the presence of failures or retries. Additionally, you can implement custom logic in your consumer application to detect and discard duplicate messages based on their unique identifiers, further enhancing deduplication capabilities.

    // How to traverse back in Kafka?
    // Kafka does not provide a direct way to traverse back in the stream of messages. However, you can achieve this by managing offsets manually and seeking to specific positions in the topic. This approach requires careful handling of offsets and may impact performance.
    // To traverse back in Kafka, you can use the seek() method of the KafkaConsumer class to set the consumer's position to a specific offset within a partition. This allows you to re-read messages from that point onward. Here's an example:
    // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    // consumer.subscribe(Arrays.asList("my-topic"));
    // consumer.poll(Duration.ofMillis(100)); // Initial poll to join the group and get partition assignments
    // consumer.seek(new TopicPartition("my-topic", 0), 10);
    // In this example, the consumer seeks to offset 10 in partition 0 of the "my-topic" topic, allowing it to re-read messages starting from that offset. Note that seeking to a specific offset may result in reprocessing messages, so it should be used with caution and in scenarios where it is necessary to traverse back in the stream of messages. Additionally, seeking to an offset that has already been deleted due to the topic's retention policy will result in an error, so it is important to ensure that the desired offset is still available in the topic before seeking to it.

    // How to replay messages in Kafka using offset management and consumer groups?
    // To replay messages in Kafka, you can use offset management and consumer groups to control the position of the consumer in the topic. By resetting the consumer's offset to an earlier position, you can reprocess messages that have already been consumed. Here's how to do it:
    // 1. Identify the consumer group: Determine the consumer group that you want to replay messages for. Each consumer group maintains its own offsets for each partition, so you need to know which group you want to reset.
    // 2. Reset the offsets: You can reset the offsets for the consumer group using the Kafka command-line tools or programmatically using the Admin API. You can reset the offsets to a specific offset, to the earliest offset, or to the latest offset. For example, to reset the offsets to the earliest position, you can use the following command:
    // kafka-consumer-groups --bootstrap-server localhost:9092 --group my-consumer-group --reset-offsets --to-earliest --execute --all-topics
    // 3. Restart the consumer: After resetting the offsets, restart the consumer application. The consumer will start consuming messages from the new offset position, allowing it to reprocess messages that have already been consumed. Make sure to handle any potential duplicates or side effects that may arise from reprocessing messages, especially if the consumer application performs actions based on the consumed messages.
    //
    // Code example for replaying messages in Kafka using offset management and consumer groups:
    // Properties props = new Properties();
    // props.put("bootstrap.servers", "localhost:9092");
    // props.put("group.id", "my-consumer-group");
    // props.put("enable.auto.commit", "false");
    // props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    // props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    // consumer.subscribe(Arrays.asList("my-topic"));
    // // Reset offsets to the earliest position
    // consumer.seekToBeginning(consumer.assignment());

    //3. Spring Boot Code Samples
    //Here is a production-grade configuration and implementation showing how Spring Boot handles producing and consuming messages efficiently.
    //
    //A. Configuration (application.yml)
    //YAML
    //spring:
    //  kafka:
    //    bootstrap-servers: broker1:9092,broker2:9092
    //    producer:
    //      key-serializer: org.apache.kafka.common.serialization.StringSerializer
    //      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    //      acks: all # Ensures data safety across replicas
    //      retries: 3
    //    consumer:
    //      group-id: order-processing-group
    //      auto-offset-reset: earliest
    //      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    //      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
    //      properties:
    //        spring.json.trusted.packages: "com.example.dto"
    //B. Producer Component
    //Java
    //@Service
    //public class OrderProducer {
    //
    //    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
    //    private static final String TOPIC = "orders-topic";
    //
    //    public OrderProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
    //        this.kafkaTemplate = kafkaTemplate;
    //    }
    //
    //    public void sendOrder(OrderDto order) {
    //        // Using the order ID as the Kafka key ensures all messages
    //        // for the same order land on the exact same partition (maintains ordering)
    //        CompletableFuture<SendResult<String, OrderDto>> future =
    //            kafkaTemplate.send(TOPIC, order.getOrderId(), order);
    //
    //        future.whenComplete((result, ex) -> {
    //            if (ex == null) {
    //                System.out.println("Sent message=[ " + order + " ] with offset=[" +
    //                    result.getRecordMetadata().offset() + "] to partition " +
    //                    result.getRecordMetadata().partition());
    //            } else {
    //                System.err.println("Unable to send message=[" + order + "] due to : " + ex.getMessage());
    //            }
    //        });
    //    }
    //}
    //C. Consumer Component
    //Java
    //@Service
    //public class OrderConsumer {
    //
    //    @KafkaListener(topics = "orders-topic", groupId = "order-processing-group")
    //    public void consumeOrder(ConsumerRecord<String, OrderDto> record, Acknowledgment ack) {
    //        try {
    //            OrderDto order = record.value();
    //            System.out.printf("Consumed message: key = %s, value = %s, partition = %d, offset = %d%n",
    //                    record.key(), order, record.partition(), record.offset());
    //
    //            // Business logic processing here...
    //
    //            // If manual offset commiting is enabled (ack-mode: MANUAL), acknowledge success
    //            if (ack != null) {
    //                ack.acknowledge();
    //            }
    //        } catch (Exception e) {
    //            // Handle error, send to Dead Letter Queue (DLQ), or throw to trigger retry policy
    //            throw new RuntimeException("Failed to process order message", e);
    //        }
    //    }
    //}


}

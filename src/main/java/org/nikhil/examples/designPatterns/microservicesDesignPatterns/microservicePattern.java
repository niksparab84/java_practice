package org.nikhil.examples.designPatterns.microservicesDesignPatterns;

public class microservicePattern {
    //Here is a brief overview of common microservices design patterns:
    //
    // ** 1. Decomposition Patterns **
    //- **Decompose by Business Capability:** Split services based on business functions.
    //  This pattern focuses on breaking down the system into services that align with specific business capabilities.
    //  Each service is responsible for a distinct business function, allowing for better alignment with business goals.
    //  Example: An e-commerce application might have separate services for user management, product catalog, and order processing.
    //  Implementation: Each service can be developed, deployed, and scaled independently,
    //  allowing teams to work on different services concurrently without affecting each other.
    // Practical Example: In a ride-sharing application, you might have separate services for driver management, ride matching and payment processing.

    //- **Decompose by Subdomain:** Use domain-driven design to identify bounded contexts.
    //  This pattern applies domain-driven design principles to identify subdomains within the business domain.
    //  Each subdomain can be implemented as a separate service, allowing for better separation of concerns and autonomy.
    //  Example: In a banking application, you might have separate services for account management, transaction processing, and customer support.
    //  Implementation: Each service can have its own data model and business logic, allowing for flexibility in implementation and evolution over time.
    // Practical Example: In a healthcare application, you might have separate services for patient management, appointment scheduling, and medical records.
    // Will these services communicate with each other? Yes, services can communicate with each other using synchronous or asynchronous communication patterns, such as REST APIs, gRPC, or message queues.
    // How will they communicate? Services can communicate using various protocols and patterns, depending on the requirements of the system.
    // Synchronous communication can be achieved using REST APIs or gRPC, where one service makes a direct request to another service and waits for a response.
    // Asynchronous communication can be achieved using message queues or event-driven architectures, where services publish events or messages to a broker, and other services subscribe to those events or messages.
    // Will these services sit behind an API gateway? Yes, services can sit behind an API gateway, which acts as a single entry point for clients to access the services.
    // Interview Questions:
    // 1. What is the difference between decomposing by business capability and decomposing by subdomain?
    // 2. How do you handle communication between services in a microservices architecture?
    // 3. What are the advantages and disadvantages of using an API gateway in a microservices architecture?
    // 4. How do you ensure data consistency across services in a microservices architecture?


    // ** 2. Integration Patterns**
    //- **API Gateway:** Single entry point for clients, routing requests to appropriate services.
    //  This pattern provides a unified interface for clients to interact with multiple services.
    //  The API Gateway acts as a reverse proxy, routing requests to the appropriate service based on the request path or other criteria.
    //  It can also handle cross-cutting concerns like authentication, logging, and rate limiting.
    //  Example: An API Gateway can route requests for user profiles, product details, and order processing to their respective services.
    //  Implementation: The API Gateway can be implemented using tools like Spring Cloud Gateway, Kong, or Nginx.
    // Libraries like Spring Cloud Gateway provide built-in support for routing, filters and predicates, making it easier to implement an API Gateway in a microservices architecture.
    // Nginx can be configured as a reverse proxy to route requests to different services based on the request path or other criteria.
    // How Nginx can be configured to handle cross-cutting concerns like authentication, logging, and rate limiting using modules or third-party plugins ?
    // Answer: Nginx can be configured to handle cross-cutting concerns using various modules and third-party plugins. For authentication, you can use the `auth_request` module to delegate authentication
    // to an external service or use JWT (JSON Web Tokens) for token-based authentication. For logging, Nginx provides built-in logging capabilities that can be customized using the `log_format` directive,
    // allowing you to log specific request and response details. For rate limiting, you can use the `limit_req` and `limit_conn` modules to control the number of requests per second or concurrent connections
    // from a client. Additionally, third-party plugins like `nginx-lua` can be used to implement more complex logic for authentication, logging and rate limiting.

    //  Elaborate more implementation: The API Gateway can be configured to define routes, filters, and predicates to control how requests are handled. It can also provide features like request aggregation,
    //  where it combines responses from multiple services into a single response for the client.
    //  It can also provide features like request aggregation, where it combines responses from multiple services into a single response for the client.
    //  Example: An API Gateway can aggregate responses from user service, product service, and order service
    //  to provide a consolidated response to the client, reducing the number of round trips required.
    // Integration patterns are essential for enabling communication and coordination between microservices, ensuring that they can work together effectively to deliver the desired functionality.
    // Interview Questions:
    // 1. What is an API Gateway, and why is it important in a microservices architecture?
    // Answer: An API Gateway is a server that acts as an entry point for clients to access multiple microservices. It handles request routing, composition, and protocol translation, allowing clients
    // to interact with the system through a single endpoint. It is important because it simplifies client interactions, provides security features and enables cross-cutting concerns like logging and rate limiting.
    // 2. How does service discovery work, and what are some common tools used for it?
    // Is service discovery same as API Gateway? No, service discovery and API Gateway serve different purposes in a microservices architecture.
    // Service discovery is responsible for dynamically locating and managing the instances of services, allowing services to find each other without hardcoding their endpoints.
    // It enables load balancing and failover by providing a registry of available services and their instances.
    // Common tools for service discovery include Eureka, Consul and Zookeeper.
    // 3. Can you explain the difference between synchronous and asynchronous communication in microservices, and when to use each?
    // Implementing a Custom Global Filter (e.g., for JWT Validation / Security Logging)
    // Token Bucket algorithm is a rate-limiting algorithm that controls the rate of requests to a service. It works by maintaining a "bucket" of tokens, where each token represents a request that can be processed.
    // When a request is made, a token is removed from the bucket. If the bucket is empty, the request is rejected or delayed until a token becomes available.
    // The bucket is refilled at a fixed rate, allowing for a controlled flow of requests to the service.

    // Spring cloud components for microservices design patterns:
    // API Gateway can be implemented using Spring Cloud Gateway, which provides a simple way to route requests to different services.
    //  It allows you to define routes, filters, and predicates to control how requests are handled.
    //  Example: You can define a route in Spring Cloud Gateway to route requests for `/users/**` to the user service,
    //  `/products/**` to the product service, and `/orders/**` to the order service. But this can be achieved using Controller classes in Spring Boot as well.
    //  The API Gateway can also handle cross-cutting concerns like authentication, logging and rate limiting.
    //  Implementation: Spring Cloud Gateway can be configured using application properties or Java configuration,
    //  allowing you to define routes, filters, and predicates to control how requests are handled.
    // Service Registry can be implemented using Spring Cloud Netflix Eureka, which provides a service registry for microservices.
    //  It allows services to register themselves and discover other services dynamically.
    //  Example: When a new instance of a service starts, it registers itself with Eureka,
    //  allowing other services to discover it and route requests accordingly.
    //  Implementation: Spring Cloud Netflix Eureka can be configured using application properties or Java configuration,
    //  allowing you to define the service registry URL, instance metadata, and other properties.



    //- **Service Registry:** Keeps track of available services and their instances.
    //  This pattern maintains a registry of all available services and their instances.
    //  It allows services to discover each other dynamically, enabling load balancing and failover.
    //  The service registry can be implemented using tools like Eureka, Consul, or Zookeeper.
    //  Example: When a new instance of a service starts, it registers itself with the service registry,
    //  allowing other services to discover it and route requests accordingly.
    //  Implementation: The service registry can be implemented using tools like Eureka, Consul, or Zookeeper.

    // Feign Client: Simplifies HTTP client calls to other services.
    //  This pattern provides a declarative way to define HTTP clients for calling other services.
    //  It allows developers to define service interfaces with annotations, and Feign generates the necessary HTTP client code.
    //  Feign clients can be used in conjunction with service discovery to dynamically resolve service instances.
    //  Example: A Feign client can be used to call a user service to fetch user details or an order service to place an order.
    //  Implementation: Feign clients can be defined using annotations like `@FeignClient` in Spring Cloud,
    //  which automatically generates the necessary HTTP client code based on the defined interface.

    //- **Client-Side Load Balancing:** Clients choose which service instance to call.
    //  This pattern allows clients to select which instance of a service to call based on load balancing algorithms.
    //  It can be implemented using libraries like Ribbon or Spring Cloud LoadBalancer.
    //  The client retrieves the list of available service instances from the service registry and selects one based on the chosen algorithm.
    //  Example: A client can use round-robin or random selection to distribute requests across multiple instances of a service.

    //- **Server-Side Load Balancing:** Load balancer routes requests to service instances.
    //  This pattern uses a load balancer to distribute incoming requests across multiple instances of a service.
    //  The load balancer can be implemented using tools like Nginx, HAProxy, or cloud-based load balancers.
    //  It routes requests based on various algorithms like round-robin, least connections, or IP hash.
    //  Example: A load balancer can route requests to multiple instances of a service running on different servers,
    //  ensuring that no single instance is overwhelmed with requests.

    //- **Service Discovery:** Enables services to find each other dynamically.
    //  This pattern allows services to discover each other at runtime without hardcoding service endpoints.
    //  It can be implemented using a service registry or through client-side discovery mechanisms.
    //  Example: A service can query the service registry to find the available instances of another service
    //  and route requests to one of them based on load balancing algorithms.

    //- **Aggregator:** Combines responses from multiple services into one.
    //  This pattern aggregates data from multiple services into a single response for the client.
    //  It can be implemented using an API Gateway or a dedicated aggregator service.
    //  The aggregator service makes parallel calls to multiple services, collects their responses,
    //  and combines them into a single response before returning it to the client.
    //  Example: An aggregator service can fetch user profile data, order history, and product recommendations
    //  from their respective services and return a consolidated response to the client.
    //
    //** 3. Database Patterns**
    //- **Database per Service:** Each service manages its own database.
    //- **Shared Database:** Multiple services share a single database (generally discouraged).
    //  This pattern allows multiple services to access a common database, enabling data sharing and consistency.
    //  However, it can lead to tight coupling between services and is generally discouraged in microservices architectures.
    //  If a shared database is used, it is important to ensure that services do not interfere with each other's data models
    //  and that changes to the database schema are carefully managed to avoid breaking other services.
    //
    //** 4. Communication Patterns **
    //- **Synchronous (REST/gRPC):** Direct service-to-service calls.
    //- **Asynchronous (Messaging/Event-Driven):** Services communicate via message brokers (e.g., RabbitMQ, Kafka).
    //
    //** 5. Reliability Patterns **
    //- **Circuit Breaker:** Prevents cascading failures by stopping calls to a failing service.
    //  This pattern allows the system to recover gracefully.
    //  It monitors the health of service calls and opens the circuit when failures exceed a threshold.
    //  Once the circuit is open, calls to the service are not made until it is deemed healthy again.
    //  This helps maintain system stability and prevents overload on failing services.
    //  Example: If a payment service is down, the circuit breaker prevents further calls to it,
    //  allowing the system to return a fallback response or an error message to the client.
    // Circuit Breaker Integration is Combining Spring Cloud Gateway with Resilience4j (Resilience4JCircuitBreakerFilterFactory) to implement a circuit breaker pattern for service calls.
    // This integration allows you to define circuit breaker configurations, such as failure thresholds and wait durations and apply them to specific routes in the API Gateway.
    // Three States of a Circuit Breaker are Closed, Open, and Half-Open. In the Closed state, requests flow normally. In the Open state, requests are blocked to prevent further failures.
    // In the Half-Open state, a limited number of requests are allowed to test if the service has recovered. if the test requests succeed, the circuit breaker transitions back to the Closed state;
    // otherwise, it returns to the Open state.
    // Implementing Resilience4j in Spring Boot applications involves adding the Resilience4j dependency, configuring circuit breaker properties in application.yml or application.properties, and annotating service methods with @CircuitBreaker.
    // Step1: Add Resilience4j dependency to your Spring Boot project.
    // Code snippet for Maven:
    // <dependency>
    //     <groupId>io.github.resilience4j</groupId>
    //     <artifactId>resilience4j-spring-boot3</artifactId>
    // </dependency>
    //
    // Step2: Configure circuit breaker properties in application.yml or application.properties.
    // Example configuration in application.yml:
    // resilience4j:
    //   circuitbreaker:
    //     instances:
    //       myServiceCircuitBreaker:
    //         registerHealthIndicator: true
    //         waitDurationInOpenState: 10s
    //         slidingWindowType: COUNT_BASED
    //         slidingWindowSize: 10
    //         minimumNumberOfCalls: 5
    //         failureRateThreshold: 50
    //
    // Step3: Annotate service methods with @CircuitBreaker to apply the circuit breaker pattern.
    // Example:
    // @Service
    // public class MyService {
    //     @CircuitBreaker(name = "myServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    //     public String callExternalService() {
    //         // Call to external service
    //     }

    //- **Fallback:** Provides a default response when a service is unavailable.
    //  This pattern allows the system to return a predefined response or execute an alternative logic
    //  when a service call fails. It ensures that the system can still function, albeit with limited capabilities.
    //  Fallbacks can be implemented at the service level or using a circuit breaker pattern.
    //  Example: If a user profile service is down, the system can return a cached version of the user profile
    //  or a default response instead of failing the entire request.

    //- **Load Balancing:** Distributes requests across multiple service instances.
    //  This pattern helps to evenly distribute incoming requests across multiple instances of a service,
    //  improving performance and availability. It can be implemented using various algorithms like round-robin,
    //  least connections, or IP hash.
    //  Load balancing can be done at the network level (e.g., using a load balancer) or at the application level
    //  (e.g., using client-side load balancing).
    //  Example: If a service has multiple instances running, the load balancer will route requests to these instances
    //  based on the chosen algorithm, ensuring that no single instance is overwhelmed with requests.
    //  This helps to improve the overall performance and reliability of the service.

    //- **Service Mesh:** Manages service-to-service communication, providing features like load balancing,
    //  security, and observability. A service mesh is a dedicated infrastructure layer that handles
    //  communication between services. It abstracts the complexity of service interactions and provides
    //  features like traffic management, security (e.g., mutual TLS), and observability (e.g., tracing and metrics).
    //  This pattern is particularly useful in microservices architectures where services need to communicate
    //  with each other frequently and require robust management of those interactions.
    //  Example: Istio and Linkerd are popular service mesh implementations that provide these capabilities.

    //- **Rate Limiting:** Controls the number of requests a service can handle in a given time.
    //  This pattern helps to protect services from being overwhelmed by too many requests.
    //  It limits the number of requests from a client or across all clients to ensure fair usage and prevent abuse.
    //  Rate limiting can be implemented using various algorithms like token bucket, leaky bucket or fixed window.
    //  Example: If a service allows 100 requests per minute, any requests beyond that limit will be rejected
    //  or delayed, ensuring that the service remains responsive and available to all clients.
    // Here service means downstream service or upstream service? Service can refer to both downstream and upstream services, depending on the context.
    // In a microservices architecture, services can be categorized as either downstream or upstream based on their role in the communication flow.
    // So rate limiting can be applied to both downstream and upstream services to control the number of requests they can handle in a given time frame.
    // Semaphores are a concurrency control mechanism that can be used to implement rate limiting. They allow you to limit the number of concurrent requests to a service
    // by maintaining a count of available "permits" or "tokens". When a request is made, it acquires a permit from the semaphore.
    // If no permits are available, the request is blocked or rejected until a permit becomes available.

    //- **Timeout:** Sets a maximum time for service calls to prevent hanging requests.
    //  This pattern ensures that service calls do not block indefinitely, allowing the system to recover from slow or unresponsive services.
    //  It specifies a timeout duration for each service call, after which the call is aborted,
    //  and an error response is returned to the client or calling service.
    //  This helps to maintain responsiveness and prevents resource exhaustion in the system.
    //  Example: If a service call takes longer than the specified timeout, it will be aborted,
    //  and the system can return a fallback response or an error message to the client.

    //- **Retry:** Automatically retries failed requests.
    //  This pattern allows the system to attempt a service call multiple times before giving up.
    //  It is useful for handling transient failures, such as network issues or temporary service unavailability.
    //  The retry logic can be configured with exponential backoff to avoid overwhelming the service with retries.
    //  Exponential backoff is a strategy where the wait time between retries increases exponentially,
    //  which helps to reduce the load on the service and increases the chances of a successful response on subsequent attempts.
    //  Example: If a service call fails, it can be retried up to three times with increasing wait times between attempts.
    // Core Concept: Automatically re-invokes a failed operation, assuming the failure is transient (e.g., a momentary network blip or database lock timeout).
    //Configuration Best Practices:
    //Exponential Backoff: Instead of retrying instantly (which worsens downstream congestion), increase the wait time exponentially between attempts (e.g. 200ms -> 400ms -> 800ms).
    //Jitter: Add random variance (jitter) to backoff intervals to prevent synchronized "thundering herds" or retry storms where hundreds of microservice instances retry simultaneously at exact intervals.
    //Exception Filtering: Only retry transient exceptions (e.g., TimeoutException, ConnectException). Never retry client-side errors like HTTP 400 Bad Request or 401 Unauthorized.
    // Max Attempts: Limit the number of retries to avoid infinite loops and resource exhaustion. A common practice is to set a maximum of 3-5 attempts.
    // Example Configuration:
    // resilience4j.retry:
    //  instances:
    //    myServiceRetry:
    //      maxAttempts: 3
    //      waitDuration: 500ms
    //      retryExceptions:
    //        - java.io.IOException
    //        - java.net.SocketTimeoutException
    //      ignoreExceptions:
    //        - org.springframework.web.client.HttpClientErrorException$BadRequest
    //        - org.springframework.web.client.HttpClientErrorException$Unauthorized
    //      enableExponentialBackoff: true
    //      exponentialBackoffMultiplier: 2

    //- **Bulkhead:** Isolates failures to prevent them from affecting the whole system.
    //  This pattern divides the system into isolated components or "bulkheads" to contain failures.
    //  If one component fails, it does not impact the others, allowing the system to continue functioning.
    //  This is particularly useful in microservices architectures where services can fail independently.
    //  Example: If a payment service fails, it should not affect the user profile service.
    // Core Concept: Borrowed from ship design (where water-tight compartments prevent a single hull breach from sinking the entire ship), the Bulkhead pattern isolates resources (like thread pools or semaphore counts) per remote service.
    //Why We Need It: Without a bulkhead, if a downstream service hangs or becomes slow, all incoming threads in the caller's shared thread pool get blocked waiting for a response, starving other healthy parts of the application.
    //Resilience4j Implementation Flavors:
    //Semaphore Bulkhead: Limits the number of concurrent executions using a semaphore counter. It is lightweight, non-blocking, and ideal for reactive or event-driven applications (like WebFlux).
    //ThreadPool Bulkhead: Uses a dedicated thread pool and a bounded queue per remote service. If the thread pool is exhausted, subsequent requests fail fast. This provides full thread isolation.
    // Example Configuration:
    //resilience4j.bulkhead:
    //  instances:
    //    myServiceBulkhead:
    //      maxConcurrentCalls: 10

    //
    //**6. Observability Patterns**
    //- **Log Aggregation:** Centralizes logs from all services.
    //- **Distributed Tracing:** Tracks requests across service boundaries.
    //
    //**7. Deployment Patterns**
    //- **Service Discovery:** Dynamically locates service instances.
    //- **Sidecar:** Deploys helper components alongside services (e.g., for logging, monitoring).
    //
    //These patterns help build scalable, resilient, and maintainable microservices architectures.

    public static void main(String[] args) {
        System.out.println("Microservices Design Patterns Overview");
        // This is a placeholder for the main method.
        // You can implement specific patterns or examples here.
    }
}

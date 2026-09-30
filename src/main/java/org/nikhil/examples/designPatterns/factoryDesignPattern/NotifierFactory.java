package org.nikhil.examples.designPatterns.factoryDesignPattern;

import akka.io.SelectionHandler;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

interface Notifier {
    void sendNotification(String to, String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Sending Email to " + to + ": " + message);
    }
}

class SmsNotifier implements Notifier {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}

class PushNotifier implements Notifier {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Sending Push Notification to " + to + ": " + message);
    }
}

enum Channel {
    EMAIL,
    SMS,
    PUSH
}

public class NotifierFactory {
    private static final Map<Channel, Supplier<Notifier>> REGISTRY = new EnumMap<>(Channel.class);
    static {
        REGISTRY.put(Channel.EMAIL, EmailNotifier::new);
        REGISTRY.put(Channel.SMS, SmsNotifier::new);
        REGISTRY.put(Channel.PUSH, PushNotifier::new);
    }

    public static Notifier getNotifier(Channel channel) {
        Supplier<Notifier> notifierSupplier = REGISTRY.get(Objects.requireNonNull(channel, "Channel cannot be null"));
        if (notifierSupplier != null) {
            return notifierSupplier.get();
        }
        throw new IllegalArgumentException("No notifier found for channel: " + channel);
    }
}

// Example usage
class DemoFactory {
    public static void main(String[] args) {
        Notifier emailNotifier = NotifierFactory.getNotifier(Channel.EMAIL);
        emailNotifier.sendNotification("user@example.com", "Hello, this is an email notification!");
    }
}

// Factory Design Pattern is a creational design pattern that provides an interface for creating objects in a superclass,
// but allows subclasses to alter the type of objects that will be created. It promotes loose coupling by eliminating the need to bind application-specific classes
// into the code. The Factory Design Pattern is particularly useful when the exact types of objects to create are determined at runtime.
// In this example, the NotifierFactory class provides a centralized way to create different types of Notifier objects based on the specified Channel.
// The use of a registry with Suppliers allows for easy extension and maintenance of the factory without modifying existing code.
// The Notifier interface defines the contract for all notifier types and concrete implementations (EmailNotifier, SmsNotifier, PushNotifier) provide
// specific behavior for sending notifications. The DemoFactory class demonstrates how to use the NotifierFactory to obtain a notifier and send a notification.

// What is EnumMap ?
// EnumMap is a specialized implementation of the Map interface in Java that is designed to work with enum types as keys. It is part of the java.util package and provides a high-performance, type-safe way to map enum constants to values.
// EnumMap has several advantages over other Map implementations when used with enums:
// 1. Type Safety: EnumMap is type-safe, meaning that it only allows enum constants of a specific enum type to be used as keys. This helps prevent programming errors and ensures that only valid enum values are used.
// 2. Performance: EnumMap is implemented as an array internally, which makes it very fast for lookups and insertions. It is generally more efficient than other Map implementations like HashMap or TreeMap when used with enums, especially for small enum types.
// 3. Memory Efficiency: EnumMap is memory-efficient because it uses a compact representation for the keys (the enum constants) and does not require additional overhead for hashing or balancing like other Map implementations.
// 4. Iteration Order: EnumMap maintains the natural order of the enum constants, which means that when you iterate over the entries of an EnumMap, they will be returned in the order in which the enum constants are declared. This can be useful when you want to preserve the order of the enum values in your application logic.
// 5. Null Keys: EnumMap does not allow null keys, which helps prevent potential NullPointerExceptions and ensures that all keys are valid enum constants.
// 6. Null Values: EnumMap allows null values, so you can associate a null value with an enum constant if needed. However, you should be cautious when using null values, as they can lead to unexpected behavior if not handled properly.
// 7. Serialization: EnumMap is serializable, which means that you can easily save and restore its state using Java's built-in serialization mechanism. This can be useful when you need to persist the mapping of enum constants to values across application restarts or when transmitting the data over a network.
// 8. Thread Safety: EnumMap is not synchronized, so if you need to use it in a multi-threaded environment, you should consider wrapping it with Collections.synchronizedMap() or using other synchronization mechanisms to ensure thread safety.

// Syntax of EnumMap:
// EnumMap<EnumType, ValueType> enumMap = new EnumMap<>(EnumType.class);

// Example of EnumMap:
// enum Day {
//     MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
// }
// EnumMap<Day, String> dayMap = new EnumMap<>(Day.class);
// dayMap.put(Day.MONDAY, "Work");
// dayMap.put(Day.SATURDAY, "Relax");
// dayMap.put(Day.SUNDAY, "Family Time");


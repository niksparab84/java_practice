package org.nikhil.examples.designPatterns.abstractDesignPattern;

interface DownstreamApplication {
    void publish();
}

class AbstractDownstreamApplication implements DownstreamApplication {
    @Override
    public void publish() {
        // Default implementation (if any)
        System.out.println("Publishing to Unknown downstream Application");
    }
}

class InternalApplication extends AbstractDownstreamApplication {
    @Override
    public void publish() {
        System.out.println("Publishing to Internal Application");
    }
}

public class DownstreamApplicationFactory {
    public static DownstreamApplication getDownstreamApplication(String type) {
        switch (type) {
            case "Internal":
                return new InternalApplication();
            case "External":
                return new ExternalApplication();
            case "RegulatoryReporting":
                return new RegulatoryReportingApplication();
            default:
                return new AbstractDownstreamApplication(); // Default case
        }
    }

    public static void main(String[] args) {
        DownstreamApplication internalApp = DownstreamApplicationFactory.getDownstreamApplication("Internal");
        internalApp.publish();

        DownstreamApplication externalApp = DownstreamApplicationFactory.getDownstreamApplication("External");
        externalApp.publish();

        DownstreamApplication regulatoryApp = DownstreamApplicationFactory.getDownstreamApplication("RegulatoryReporting");
        regulatoryApp.publish();

        DownstreamApplication abstractApp = DownstreamApplicationFactory.getDownstreamApplication("UnknownType");
        abstractApp.publish();
    }

    // This is a Abstract Factory Design Pattern example
    // The SystemFactory class provides a way to create different system instances based on the type specified.
    // Each system type has its own implementation of the publish method, allowing polymorphic behavior.
    // The main method demonstrates how to use the SystemFactory to create instances of different system types
    // and call their publish methods. This design pattern allows for easy extensibility and maintainability,
    // as new system types can be added without modifying the existing code. The System interface defines the contract
    // for all system types, ensuring that they all implement the publish method.
    // The AbstractSystem class provides a default implementation of the publish method, which can be overridden by specific system types if needed.
    // This pattern is useful in scenarios where you need to create objects of different types that share a common interface
    // or base class, allowing for flexibility and code reuse.
}

class ExternalApplication extends AbstractDownstreamApplication {
    @Override
    public void publish() {
        System.out.println("Publishing to External Application");
    }
}

class RegulatoryReportingApplication extends AbstractDownstreamApplication {
    @Override
    public void publish() {
        System.out.println("Publishing to Regulatory Reporting Application");
    }
}

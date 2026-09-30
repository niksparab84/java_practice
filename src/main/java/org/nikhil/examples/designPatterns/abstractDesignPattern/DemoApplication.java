package org.nikhil.examples.designPatterns.abstractDesignPattern;

// buttons: first product hierarchy
interface Button {
    void paint();
}

class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("You have created MacOSButton..");
    }
}

class  WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("You have created WindowsButton..");
    }
}

//  checkboxes: Second product hierarchy
interface Checkbox {
    void paint();
}

class MacOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox..");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox..");
    }
}

// factories
// factories/GUIFactory.java: Abstract factory  => factory of factories
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// factories/MacOSFactory.java: Concrete factory (macOS)
class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

// factories/WindowsFactory.java: Concrete factory (Windows)
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// App
// app/Application.java: Client code
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

// Demo: App configuration
public class DemoApplication {
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}

// Create class diagram for the above code and explain the design pattern used in the above code.
// The code above implements the Abstract Factory design pattern. This pattern provides an interface for creating families of related or dependent objects
// without specifying their concrete classes. It allows for the creation of objects that belong to a particular family (in this case, GUI components like
// buttons and checkboxes) while ensuring that the client code remains decoupled from the specific implementations.
// The class diagram for the above code can be represented as follows:
//                     ```
//                     +-------------------+          +-------------------+
//                     |     GUIFactory    |<>--------|   MacOSFactory    |
//                     +-------------------+          +-------------------+
//                     | +createButton()   |          | +createButton()   |
//                     | +createCheckbox() |          | +createCheckbox() |
//                     +-------------------+          +-------------------+
//                               ^                             ^
//                               |                             |
//                     +-------------------+          +-------------------+
//                     |   WindowsFactory  |          |   Application     |
//                     +-------------------+          +-------------------+
//                     | +createButton()   |          | -button: Button   |
//                     | +createCheckbox() |          | -checkbox: Checkbox|
//                     +-------------------+          +-------------------+
//                               ^                             ^
//                               |                             |
//                     +-------------------+          +-------------------+
//                     |      Button       |          |     Checkbox      |
//                     +-------------------+          +-------------------+
//                     | +paint()          |          | +paint()          |
//                     +-------------------+          +-------------------+
//                               ^                             ^
//                               |                             |
//                     +-------------------+          +-------------------+
//                     |   MacOSButton     |          |   MacOSCheckbox   |
//                     +-------------------+          +-------------------+
//                     | +paint()          |          | +paint()          |
//                     +-------------------+          +-------------------+
//                               ^                             ^
//                               |                             |
//                     +-------------------+          +-------------------+
//                     |  WindowsButton    |          |  WindowsCheckbox  |
//                     +-------------------+          +-------------------+
//

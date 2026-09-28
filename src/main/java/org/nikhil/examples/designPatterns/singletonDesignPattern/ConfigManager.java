package org.nikhil.examples.designPatterns.singletonDesignPattern;

public class ConfigManager {

    private static volatile ConfigManager instance;

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if( instance == null ) {
            synchronized (ConfigManager.class) {
                if( instance == null ) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public void displayConfig() {
        System.out.println("Displaying configuration settings...");
    }
}

//Usage example
class demo {

    public static void main(String[] args) {
        ConfigManager configManager1 = ConfigManager.getInstance();
        ConfigManager configManager2 = ConfigManager.getInstance();

        System.out.println("Are both instances the same? " + (configManager1 == configManager2));
    }
}

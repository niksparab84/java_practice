package org.nikhil.examples.designPatterns.singletonDesignPattern;

public class SingletonConnection {

    // why volatile?
    // The volatile keyword ensures that the instance variable is always read from and written to the main memory,
    // rather than from a thread's local cache. This prevents the instance from being partially constructed
    // when another thread reads it, which can happen if the instance is not fully initialized yet.
    // This is important in a multi-threaded environment where multiple threads may try to access the instance
    // simultaneously. By declaring the instance variable as volatile, we ensure that all threads see the same value
    // of the instance variable, and that the instance is fully constructed before it is accessed.
    // This is important for thread safety and to prevent issues like the "half-constructed" object problem.
    private static volatile SingletonConnection instance;

    // Private constructor to prevent instantiation
    private SingletonConnection() {
        // Initialization code here
    }

    // 3rd version : double-checked locking pattern, where only a critical section of code is locked.
    // Why is it called Double-Checked Locking?
    // The term "double-checked locking" refers to the fact that the code checks whether the instance is null
    // twice: once before acquiring the lock and once after acquiring the lock.
    // This is done to avoid the overhead of acquiring the lock every time the getInstance() method is called.
    // The first check (before acquiring the lock) is a fast path that allows multiple threads to access the method
    // concurrently without blocking each other, as long as the instance is already initialized.
    // The second check (after acquiring the lock) ensures that only one thread can create the instance
    // when it is null, preventing multiple threads from creating multiple instances of the SingletonConnection class.
    // This pattern is used to improve performance in a multi-threaded environment by reducing the overhead
    // of acquiring locks while still ensuring thread safety.
    public static SingletonConnection getInstance() {
        // Lazy initialization
        //double-checked locking
        if (instance == null) {
            // Thread-safe check
            synchronized (SingletonConnection.class) {
                if (instance == null) {
                    instance = new SingletonConnection();
                }
            }
        }
        return instance;
    }

    // 1st version: creates multiple instances if two thread
    // access this method simultaneously
    public static SingletonConnection getInstance1()
    {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    // 2nd version : this is thread-safe and only
    // creates one instance of Singleton on concurrent
    // environment but it is unnecessarily expensive due to
    // cost of synchronization at every call.
    public static synchronized SingletonConnection getInstance2()
    {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }


    // Example method
    public void connect() {
        System.out.println("Connected to the database.");
    }
}

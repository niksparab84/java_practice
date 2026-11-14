package org.nikhil.examples.concurrency;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        Map<Integer, String> demoMap = new ConcurrentHashMap<>();
        demoMap.put(0, "Mumbai");
        demoMap.put(1, "Thane");
        demoMap.put(2, "Pune");
        System.out.println(demoMap);

        demoMap.put(0, "Nashik"); // This will replace the value for key 0
        System.out.println(demoMap);

        demoMap.putIfAbsent(2, "Nagpur"); // This will NOT replace the value for key 2
        System.out.println(demoMap);
        demoMap.putIfAbsent(4, "Nagpur"); // This will add a new key-value pair
        System.out.println(demoMap);

        String retValue = demoMap.remove(1); // This will remove the key-value pair for key 1
        System.out.println("Removed value: " + retValue);
        System.out.println(demoMap);

        Boolean isRemoved = demoMap.remove(2, "Mumbai"); // This will remove the key-value pair for key 2 only if the value is "Mumbai"
        System.out.println("Is key 2 removed? " + isRemoved);
        System.out.println(demoMap);

        boolean isReplaced = demoMap.replace(0, "Nashik", "Aurangabad"); // This will replace the value for key 0 only if the current value is "Nashik"
        System.out.println("Is key 0 replaced? " + isReplaced);
        System.out.println(demoMap);

        retValue = demoMap.replace(4, "Indore"); // This will replace the value for key 4 regardless of the current value
        System.out.println("Replaced value: " + retValue);
        System.out.println(demoMap);

        //Print all the key-value pairs in the map using forEach
        demoMap.forEach((key, value) -> System.out.println("Key: " +key + ", Value: " + value));

        //print all the key-value pairs in the map using entrySet and iterate over the entries
        Iterator<Map.Entry<Integer, String>> iterator = demoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}

package org.nikhil.examples.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public int compareTo(Person other) {
        //return Integer.compare(this.age, other.age);
        return getName().compareTo(other.getName());
    }
}

public class PersonUtilsDemo {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 30));
        persons.add(new Person("Bob", 25));
        persons.add(new Person("Charlie", 30));
        persons.add(new Person("Lara", 35));
        persons.add(new Person("Peter", 21));
        persons.add(new Person("Zach", 45));
        persons.add(new Person("Karl", 50));
        persons.add(new Person("Barbara", 17));
        persons.add(new Person("Luis", 16));

        //find minors
        List<Person> minors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());
        System.out.println("minors = " + minors);

        persons.stream()
                .sorted()
                .map(Person::getName)
                .forEach(System.out::println);

        //Sort using comparators // no need of actual comparators
        persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);

        //Sort as per Age
        persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);


    }
}

class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class PersonAgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}

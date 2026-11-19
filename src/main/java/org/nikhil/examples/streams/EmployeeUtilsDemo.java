package org.nikhil.examples.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;

public class EmployeeUtilsDemo {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ram", 30, "Admin", 25000));
        employees.add(new Employee("Tom", 40, "IT", 50000));
        employees.add(new Employee("Jim", 35, "Finance", 50000));
        employees.add(new Employee("Jack", 38, "HR", 35000));
        employees.add(new Employee("Jason", 28, "IT", 26000));
        employees.add(new Employee("Toby", 30, "Finance", 30000));
        employees.add(new Employee("Shiva", 32, "HR", 30000));

        //Employee count by dept, so I need a map with dept name as key and count as value
        Map<String, Long> countByDept = employees.stream()
                .collect(groupingBy(Employee::getDepartment, counting()));
        System.out.println("countByDept => " + countByDept);

        //Sort countByDept by count in descending order
        Map<String, Long> sortedCountByDept = countByDept.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("sortedCountByDept => " + sortedCountByDept);

        //Sort countByDept by dept name
        Map<String, Long> sortedCountByDeptAsPerDeptName = employees.stream()
                .collect(groupingBy(Employee::getDepartment, counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("sortedCountByDeptAsPerDeptName => " + sortedCountByDeptAsPerDeptName);

        //Average salary by dept
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(groupingBy(Employee::getDepartment, averagingDouble(Employee::getSalary)));
        System.out.println("avgSalaryByDept => " + avgSalaryByDept);

        //Names by dept
        Map<String, List<String>> namesByDept = employees.stream()
                .collect(groupingBy(Employee::getDepartment, mapping(Employee::getName, toList())));
        System.out.println("namesByDept => " + namesByDept);

        //Max/Top salary per dept
        Map<String, Optional<Employee>> topPaidEmployeePerDept = employees.stream()
                .collect(groupingBy(Employee::getDepartment, maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println("topPaidEmployeePerDept => " + topPaidEmployeePerDept );

        // Stream Grouping by is used to group the elements of a stream based on a classifier function. It returns a Map<K, List<T>> where K is the type of the key and T is the type of the elements in the stream.
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(groupingBy(Employee::getDepartment));
        System.out.println("employeesByDept => " + employeesByDept);

        // Nth highest
        Optional<Double> secondHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("secondHighestSalary => " + secondHighestSalary);
        // Stream sorted is used to sort the elements of a stream based on a comparator. It returns a new stream with the sorted elements.
        // and skip is used to skip the first n elements of a stream. It returns a new stream with the remaining elements.

        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

        // Stream partitioning is to be used when we want to divide the stream into two parts based on a predicate.
        // It returns a Map<Boolean, List<T>> where the key is true or false based on whether the element satisfies the
        // predicate or not.
        Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 30000));
        System.out.println("partitionedBySalary => " + partitionedBySalary);

        //toMap is used to convert a stream into a map. It takes two functions as arguments, one for the key and one for the value.
        //toMap with merge function is used to convert a stream into a map with a merge function. It takes three functions as arguments, one for the key, one for the value and one for the merge function.
        Map<String, Double> salaryTotalByDept = employees.stream()
                .collect(Collectors.toMap(Employee::getDepartment, Employee::getSalary, Double::sum));
        System.out.println("salaryTotalByDept => " + salaryTotalByDept);

        // Sorting with multiple keys
        employees.stream().sorted(Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, reverseOrder()))
                .forEach(System.out::println);
        //Here we are sorting the employees first by department and then by salary in descending order. The thenComparing method is used to sort by multiple keys.
        // The reverseOrder method is used to sort in descending order.

        //flatMap is used to convert a stream of collections into a stream of elements. It takes a function as an argument that returns a stream of elements for
        // each element in the original stream.
        List<String> allEmployeeNames = employees.stream()
                .flatMap(e -> List.of(e.getName()).stream())
                .collect(Collectors.toList());
        System.out.println("allEmployeeNames => " + allEmployeeNames);

        //String/char frequency count
        String inputStr = "hello world";
        Map<Character, Long> charFrequencyMap = inputStr.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, counting()));
        System.out.println("charFrequencyMap => " + charFrequencyMap);
        // This is a simple example of how to use the groupingBy collector to count the frequency of characters in a string. The LinkedHashMap is used to preserve
        // the order of the characters in the string.

        // Names of employees older than 30, sorted alphabetically, as a comma-separated string.
        String namesOfEmployeesOlderThan30 = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .sorted(Comparator.comparing(Employee::getName))
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("namesOfEmployeesOlderThan30 => " + namesOfEmployeesOlderThan30);
    }
}

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Double.compare(salary, employee.salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, department, salary);
    }
}

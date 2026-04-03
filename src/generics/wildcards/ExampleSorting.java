package generics.wildcards;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExampleSorting {

    public static void main(String[] args) {

        List<Manager> managers = Arrays.asList(
                new Manager("Bob", "IT"),
                new Manager("Carol", "IT"),
                new Manager("Alice", "HR")
        );

        managers.sort(new EmployeeComparator());

        System.out.println(managers);
    }

    static class EmployeeComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.name.compareTo(e2.name);
        }
    }

    static class Employee {

        public final String name;

        public Employee(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Manager extends Employee {

        public final String department;

        public Manager(String name, String dept) {
            super(name);
            this.department = dept;
        }
    }
}

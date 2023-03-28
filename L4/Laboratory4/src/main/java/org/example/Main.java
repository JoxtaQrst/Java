package org.example;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student>studentList=Arrays.asList(
                new Student("S0",Arrays.asList(new Project("P0"),new Project("P1"),new Project("P2"))),
                new Student("S1",Arrays.asList(new Project("P0"),new Project("P1"))),
                new Student("S2",Arrays.asList(new Project("P0")))
        );
        TreeSet<Project> projectTreeSet= studentList.stream()
                .flatMap(student -> student.getProjectList().stream())
                .collect(Collectors.toCollection(TreeSet::new));

        LinkedList<Student>sortedStudents = new LinkedList<>(studentList);
        sortedStudents.sort(Student::compareTo);
        System.out.println("Students sorted by name: ");
        sortedStudents.forEach(System.out::println);

        System.out.println("Project sorted by name: ");
        projectTreeSet.forEach(System.out::println);
    }
}
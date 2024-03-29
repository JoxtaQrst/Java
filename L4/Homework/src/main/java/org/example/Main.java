package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static Faker faker = new Faker();
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student(faker.name().fullName())).toArray(Student[]::new);
        var projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project(faker.leagueOfLegends().champion())).toArray(Project[]::new);


        projectAllocation finalProjects = new projectAllocation(List.of(students), List.of(projects));
        finalProjects.addStudent(students[0],Arrays.asList(projects[0],projects[1],projects[2]));
        finalProjects.addStudent(students[1],Arrays.asList(projects[0],projects[1]));
        finalProjects.addStudent(students[2],Arrays.asList(projects[0]));

        finalProjects.getAllStudents();
        List<Project> target = Arrays.asList(projects[1],projects[2]);
        finalProjects.getStudentsHaving(target);


        System.out.println("Students that have a number of preferences lower than the average number of preferences:");
        System.out.println();
        Map<Student, List<Project>> studentPreferences = finalProjects.getPrefMap();
        double avgPrefs = studentPreferences.values().stream()
                .mapToInt(List::size)
                .average()
                .orElse(0.0);
        studentPreferences.entrySet().stream()
                .filter(entry -> entry.getValue().size() < avgPrefs)
                .forEach(entry -> System.out.println(entry.getKey().getName()+" has "+entry.getValue().size()+" preferences. " + entry.getValue()));
        System.out.println();


        Map<Student,Project> doneProjects = new HashMap<>();
        doneProjects = finalProjects.assignProjects(projects);
        for(Map.Entry<Student, Project> liste : doneProjects.entrySet())
        {
            System.out.println(liste.getKey().getName()+" will play "+liste.getKey().getProjectName());
        }

    }
}
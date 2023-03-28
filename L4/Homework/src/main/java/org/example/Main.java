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
        System.out.println();
        
        Map<Student,List<Project>> doneProjects = new HashMap<>();
        doneProjects =finalProjects.assignProjects(projects);
        for(Map.Entry<Student, List<Project>> liste : doneProjects.entrySet())
        {
            System.out.println(liste.getKey().getName()+" will play "+liste.getKey().getProjectName());
        }

    }
}
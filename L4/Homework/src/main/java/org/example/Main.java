package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i) )
                .toArray(Student[]::new);
        var projects = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Project("P" + i) )
                .toArray(Project[]::new);

        List<Student> listOfStudents=new ArrayList<>();
        listOfStudents.addAll(Arrays.asList(students));

        List<Student>newSortedList=listOfStudents.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        Map<Student,List<Project>> prefMap = new HashMap<>();
        prefMap.put(students[0], Arrays.asList(projects[0],projects[1],projects[2]));
        prefMap.put(students[2], Arrays.asList(projects[0]));
        prefMap.put(students[1], Arrays.asList(projects[0],projects[1]));


        for(Student student:prefMap.keySet())
        {
            System.out.print(student.getName()+" prefers: ");
            List<Project> prefferedProjects = prefMap.get(student);
            for(Project project : prefferedProjects)
            {
                System.out.print("  " + project.getName());
            }
            System.out.println();
        }

        listOfStudents.stream().filter(s -> prefMap.get(s) != null && prefMap.get(s).contains(projects[0])).forEach(System.out::println);
        List<Project> target = Arrays.asList(projects[1],projects[2]);
        List<Student> result = listOfStudents.stream().filter(s->prefMap.get(s) != null && prefMap.get(s).containsAll(target)).collect(Collectors.toList());
        for(Student student:result)
        {
            System.out.println(student.getName());
        }
    }
}
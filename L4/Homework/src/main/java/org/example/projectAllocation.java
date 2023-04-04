package org.example;
import javax.sound.sampled.Port;
import java.util.*;
import java.util.stream.Collectors;

public class projectAllocation {

    private final List<Student> students;
    private final List<Project> projects;
    private final Map<Student,List<Project>> prefMap = new HashMap<>();

    public List<Student> getStudents() {
        return students;
    }

    public Map<Student, List<Project>> getPrefMap() {
        return prefMap;
    }

    public projectAllocation(List<Student> students, List<Project> projects)
    {
       this.students=students;
       this.projects=projects;
    }

    public void addStudent(Student s,List<Project> p)
    {
        prefMap.put(s, p);
    }

    public void getAllStudents()
    {
        for(Student student:prefMap.keySet())
        {
            System.out.println(student.getName()+" prefers: ");
            List<Project> prefferedProjects = prefMap.get(student);
            for(Project project : prefferedProjects)
            {
                System.out.println(" - " + project.getName());
            }
            System.out.println();
        }
    }

    public void getStudentsHaving(List<Project> p)
    {
        List<Student> listOfStudents=new ArrayList<>();
        listOfStudents.addAll(students);

        List<Student>newSortedList=listOfStudents.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        List<Student> result = listOfStudents.stream().filter(s->prefMap.get(s) != null && prefMap.get(s).containsAll(p)).collect(Collectors.toList());
        System.out.print("Students that have " + p.toString() + " : ");
        for(Student student:result)
        {
            System.out.println(student.getName());
        }
    }

    public Map<Student, Project> assignProjects (Project[] projects)
    {
        int projectNumber= projects.length;
        int i=0;
        Map<Project,Boolean> isAssigned = new HashMap<>();
        Map<Student,Project> finalAssignation = new HashMap<>();
        for(Project project : projects)
        {
            isAssigned.put(project,false);
        }

        List<Student> sortedStudents = new ArrayList<>(prefMap.keySet());
        sortedStudents.sort(Comparator.comparingInt(student -> prefMap.get(student).size()));
        // facem un map sa verificam daca proiectele sunt diponibile sau nu

        for (Student student : sortedStudents) {
            List<Project> availableProjects = prefMap.get(student);

            for (Project project : availableProjects) {
                if (!isAssigned.get(project)) {
                    student.setProject(project);
                    finalAssignation.put(student,project);
                    isAssigned.put(project, true);
                    break;
                }
            }
        }
        return finalAssignation;
    }


}

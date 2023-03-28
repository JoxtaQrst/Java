package org.example;

public class Student implements Comparable<Student> {
    private final String name;
    private Project project;
    public Student(String name){
        this.name=name;
    }
    public String getName()
    {
        return name;
    }

    public void setProject (Project project)
    {
        this.project=project;
    }

    public Project getProject() {
        return project;
    }

    public String getProjectName() {
        return project.getName();
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

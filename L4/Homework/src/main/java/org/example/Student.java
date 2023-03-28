package org.example;
import java.util.List;

public class Student implements Comparable<Student> {
    private final String name;
    //private final List<Project> projectList;
    public Student(String name){
        this.name=name;
    }
    public String getName()
    {
        return name;
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

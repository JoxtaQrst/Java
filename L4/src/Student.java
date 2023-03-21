import java.util.List;

public class Student implements Comparable<Student> {
    private final String name;
    private final List<Project> projectList;
    public Student(String name, List<Project> projectList){
        this.name=name;
        this.projectList=projectList;
    }
    public String getName()
    {
        return name;
    }

    public List<Project> getProjectList(){
        return projectList;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", projectList=" + projectList +
                '}';
    }
}

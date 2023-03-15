import java.util.*;

public class Company implements Node, Comparable<Company> {
    private String name;
    private final List<Person> employees;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<Person>();
    }



    public void addEmployee(Person person) {
        this.employees.add(person);
        person.setCompany(this);
    }

    public String getName() {
        return name;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }
}

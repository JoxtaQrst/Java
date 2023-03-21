import java.util.*;

public class Company implements Node, Comparable<Company> {
    private String name;
    public final Map<Person,String>employees;

    public Company(String name) {
        this.name = name;
        this.employees = new HashMap<>();
    }



    public void addEmployee(Person person) {
        employees.put(person, person.getClass().getName());
        person.setCompany(this);
    }

    public void getEmployees() {
        System.out.println("Employees of " + getName() + ":");
        for(Map.Entry<Person ,String> employee:employees.entrySet())
        {
            System.out.println("- "+employee.getKey().getName()+" as "+employee.getValue()+";");
        }

    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }
}

import java.util.*;
public class Person implements Node, Comparable <Person> {
    private final String name;
    private final String birthDate;
    private final Map<Node,String>relations;
    private Company company;

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.relations=new HashMap<>();
    }

    public void addRelations(Node nodeName,String realtions){
        relations.put(nodeName,realtions);
    }
    public Map<Node, String> getRelationships() {
        return relations;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

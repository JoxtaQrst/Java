import java.util.*;
public class Person implements Node, Comparable <Person> {
    private final String name;
    private final List<Person> friends;
    private Company company;

    public Person(String name) {
        this.name = name;
        this.friends = new ArrayList<Person>();
        this.company = null;
    }

    public void seeFriends() {
        for(Person prieteni:friends)
        {
            System.out.println(prieteni.getName());
        }
    }



    public void addFriend(Person person) {
        this.friends.add(person);
        person.friends.add(this);
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

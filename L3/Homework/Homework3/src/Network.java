import java.util.*;
public class Network {
    private final List<Object> nodes; // person and companies

    public Network(){
        this.nodes=new ArrayList<>();
    }
    public void addNode(Object node){
        nodes.add(node);
    }
    public int getImportance(Object node)
    {
        int importance=0;
        if(node instanceof Person person)
        {
            if(person.getCompany()!=null)
            {
                importance=1;
            }
            importance +=person.getRelationships().size();

        }
        else if (node instanceof Company company)
        {
            importance=company.employees.size();
        }
        return importance;
    }
    public List<Object> getNodesByImportance(){
        List<Object> sortedNodes = new ArrayList<>(nodes);
        sortedNodes.sort((node1,node2)->getImportance(node2) - getImportance(node1));
        return sortedNodes;
    }
}

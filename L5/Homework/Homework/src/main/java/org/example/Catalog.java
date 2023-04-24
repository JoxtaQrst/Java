package org.example;

import java.util.*;

public class Catalog {
    private String name;
    private List<Document> documents = new ArrayList<>();

    public Catalog(){

    }
    public Catalog(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void add(Document doc){
        documents.add(doc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catalog { name =");
        sb.append("Name");
        sb.append(", documents= [");
        for(Document document:documents){
            sb.append(document.toString());
            sb.append(", ");
        }
        if (!documents.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("] }");
        return sb.toString();
    }

    public Document findById(String id){
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }
}
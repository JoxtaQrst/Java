
package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.print.Doc;
import java.lang.annotation.Documented;
import java.util.*;

public class Document {
    private String id;
    private String title;
    private String location; // file name or web page

    private List<String> tags = new ArrayList<>();

    public Document(){

    }

    public Document(String id, String title, String location){
        this.id=id;
        this.title=title;
        this.location=location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document { id = ").append(id)
                .append(", title = ").append(title)
                .append(", location = ").append(location)
                .append(", tags = ").append(tags)
                .append(" }");
        return sb.toString();
    }
}

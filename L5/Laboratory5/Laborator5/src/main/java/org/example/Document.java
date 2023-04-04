package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.annotation.Documented;
import java.util.*;

public class Document {
    private String id;
    private String title;
    private String location; // file name or web page

    private Map<String,Object> tags = new HashMap<>();

    public Document( String id, String title, String location){
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

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void addTag(String key, Object obj){
        tags.put(key, obj);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document { id = ");
        sb.append(id);
        sb.append(", title = ");
        sb.append(title);
        sb.append(", location = ");
        sb.append(location);
        sb.append(", tags = {");
        for (Map.Entry<String, Object> entry : tags.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        if (!tags.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("} }");
        return sb.toString();
    }
}

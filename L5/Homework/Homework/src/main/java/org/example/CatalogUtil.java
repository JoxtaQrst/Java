package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void add(Catalog catalog,Document document)
    {
        catalog.add(document);
    }
    public static String catalogToString(Catalog catalog)
    {
        return catalog.toString();
    }

    public static void save(Catalog catalog,String fileName) throws IOException {
        OBJECT_MAPPER.writeValue(new File(fileName),catalog);
    }
    public static Catalog load(String filename) throws InvalidCatalogException, IOException {
        return OBJECT_MAPPER.readValue(new File(filename), Catalog.class);
    }

    public static void view(Document article1) {
    }
}
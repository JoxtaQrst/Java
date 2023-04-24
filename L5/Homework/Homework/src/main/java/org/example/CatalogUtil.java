package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

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

    public static void list(List<Document> documents) {
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    public static void report(Catalog catalog,String path) throws IOException, TemplateException {
        List<Document> documentList= catalog.getDocuments();

        Configuration config = new Configuration(Configuration.VERSION_2_3_32);
        config.setClassLoaderForTemplateLoading(CatalogUtil.class.getClassLoader(),"/");
        config.setDefaultEncoding("UTF-8");
        try{
            Template template = config.getTemplate("report.ftl");
            Map<String,Object> data = Map.of("documents",documentList);
            StringWriter stringWriter = new StringWriter();
            template.process(data,stringWriter);

            String html = stringWriter.toString();
            File temp = File.createTempFile("report",".html");
            FileWriter writer = new FileWriter(temp);
            writer.write(html);
            writer.close();
            Desktop.getDesktop().browse(temp.toURI());

        }catch (IOException | TemplateException e){
            System.out.println("Could not generate report: " + e.getMessage());
        }
    }

    public static void save(Catalog catalog,String fileName) throws IOException {
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        OBJECT_MAPPER.writeValue(new File(fileName),catalog);
    }
    public static Catalog load(String filename) throws InvalidCatalogException, IOException {
        return OBJECT_MAPPER.readValue(new File(filename), Catalog.class);
    }

    public static void view(Document document)throws IOException, URISyntaxException {
        //IOException -- file cannot be opened
        //URISyntaxException -- if the document path is not a valid URI

        Desktop desktop = Desktop.getDesktop();

        if(document.getLocation().startsWith("http")){
            desktop.browse(new URI(document.getLocation()));
        }

        else {
            File file = new File(document.getLocation());
            desktop.open(file);
        }
    }
}
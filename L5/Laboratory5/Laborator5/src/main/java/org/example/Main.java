package org.example;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {

        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.view(catalog.findById("article1"));
    }

    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("MyDocuments");
        var book = new Document("article1","Smecheru meu","aodgao");
        var article = new Document("book1","Saigjewo","oaskaogkasof");
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog,"catalog.json");
    }
}
package org.example;


import freemarker.template.TemplateException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException, URISyntaxException, TemplateException, InvalidInputException, InvalidCommandException {

        Main app = new Main();
        app.openMenu();
        /*app.testCreateSave();
        app.testLoadView();
        app.testList();
        app.testReport();*/

    }

    private void openMenu() throws IOException, InvalidCatalogException, URISyntaxException, TemplateException, InvalidInputException {
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.println("1. Create and save catalog");
            System.out.println("2. Add document");
            System.out.println("3. Load and view document");
            System.out.println("4. List documents");
            System.out.println("5. Generate report");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createCatalog(scanner,catalog);
                    break;
                case "2":
                    addDocument(scanner,catalog);
                    break;
                case "3":
                    if (catalog == null) {
                        throw new InvalidCatalogException(new NullPointerException("Catalog is null."));
                    }
                    loadDocument(scanner);
                    break;
                case "4":
                    if (catalog == null) {
                        throw new InvalidCatalogException(new NullPointerException("Catalog is null."));
                    }
                    listDocuments();
                    break;
                case "5":
                    if (catalog == null) {
                        throw new InvalidCatalogException(new NullPointerException("Catalog is null."));
                    }
                    createReport();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid command. Please enter a valid choice.");
                    choice = scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void createReport() throws TemplateException, IOException, InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.report(catalog,"catalog.html");
    }

    private void listDocuments() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.list(catalog.getDocuments());
    }

    private void loadDocument(Scanner scanner) throws InvalidCatalogException, IOException, URISyntaxException, InvalidInputException {
        try{
            Catalog catalog = CatalogUtil.load("catalog.json");
            listDocuments();

            System.out.println("Enter Document ID to view: ");
            String inputString = scanner.nextLine();
            if(catalog.findById(inputString)==null)
            {
                throw new InvalidInputException();
            }
            CatalogUtil.view(catalog.findById(inputString));
        }catch (InvalidInputException e){
            System.out.println("Invalid input: "+ e.getMessage());
        }


    }

    private void addDocument(Scanner scanner, Catalog catalog) throws InvalidInputException {
        //add the document
        System.out.println("Usage [id] [name] [path]");
        try{
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            //check if the usage is correct
            if (tokens.length < 3) {
                throw new InvalidInputException();
            }

            String id = tokens[0];
            String title = tokens[1];
            String location = tokens[2];

            var document = new Document(id,title,location);
            //add tags
            System.out.println("Enter tags:");
            String tags = scanner.nextLine();
            String[] tokenTags = tags.split(" ");
            // parse through tokenTags
            int i=0;
            while(i < tokenTags.length)
            {
                document.addTag(tokenTags[i]);
                i++;
            }
            // document.addTag(tokenTag[i]);
            catalog.add(document);
            CatalogUtil.save(catalog,"catalog.json");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: "+ e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void createCatalog(Scanner scanner,Catalog catalog) throws IOException {
        System.out.println("Choose a name for the catalog: ");
        String name = scanner.nextLine();
        catalog.setName(name);
        CatalogUtil.save(catalog,"catalog.json");
        System.out.println("Catalog Saved!");
    }
}

    /*private void testReport() throws InvalidCatalogException, IOException, TemplateException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.report(catalog,"catalog.html");

    }

    private void testList() throws InvalidCatalogException,IOException{
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.list(catalog.getDocuments());
    }

    private void testLoadView() throws InvalidCatalogException, IOException, URISyntaxException {
        Catalog catalog = CatalogUtil.load("catalog.json");
        CatalogUtil.view(catalog.findById("article2"));
    }

    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("MyDocuments");
        var book = new Document("book1","Saigjewo","/path/to/myfile.txt");
        book.addTag("PDF");
        book.addTag("Book");
        catalog.add(book);

        var book2 = new Document("book2","Ruination","D:\\FacultateRandom\\Ruination_by_Anthony_Reynolds.pdf");
        book2.addTag("League of Legends");
        book2.addTag("Fantasy");
        catalog.add(book2);

        var book3 = new Document("book3","Oferta Absolvire","C:\\Users\\pinte\\Downloads\\oferta_absolvire_2024-1.pdf");
        book3.addTag("Absolvire");
        book3.addTag("Scoala");
        catalog.add(book3);

        var article = new Document("article1","Smecheru meu","/path/to/myfile.pdf");
        article.addTag("Text");
        article.addTag("Article");
        catalog.add(article);

        var article2 = new Document("article2","Minecraft Java Project","C:\\Users\\pinte\\Desktop\\ProjectMinecraft.txt");
        article2.addTag("Minecraft");
        article2.addTag("Text");
        catalog.add(article2);

        CatalogUtil.save(catalog,"catalog.json");
    }*/
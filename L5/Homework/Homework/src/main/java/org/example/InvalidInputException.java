package org.example;

public class InvalidInputException extends  Exception{
    public InvalidInputException(){
        super("Invalid input format");
    }
}
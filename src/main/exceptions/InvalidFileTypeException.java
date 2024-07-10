package main.exceptions;

public class InvalidFileTypeException extends Exception{

    public InvalidFileTypeException(String message){
        super(message);
    }
}
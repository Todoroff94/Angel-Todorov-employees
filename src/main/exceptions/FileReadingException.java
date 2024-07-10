package main.exceptions;

public class FileReadingException extends Exception{

    public FileReadingException(String message, Throwable cause){
        super(message,cause);
    }
}
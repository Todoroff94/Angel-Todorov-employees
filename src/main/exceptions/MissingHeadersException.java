package main.exceptions;

public class MissingHeadersException extends RuntimeException{

    public MissingHeadersException(String message){
        super(message);
    }
}
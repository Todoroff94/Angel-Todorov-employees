package main.readers;

import main.exceptions.FileReadingException;
import main.exceptions.InvalidFileTypeException;
import main.projects.EmployeeProject;

import java.util.List;

public interface FileReader {

    List<EmployeeProject> readFile(String filename) throws InvalidFileTypeException, FileReadingException;
}
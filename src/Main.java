import main.exceptions.FileReadingException;
import main.exceptions.InvalidFileTypeException;
import main.projects.EmployeeProject;
import main.readers.EmployeeProjectCSVReader;
import main.calculators.LongestCollaborationCalculator;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidFileTypeException, FileReadingException {

        EmployeeProjectCSVReader employeeProjectCsvReader = new EmployeeProjectCSVReader();
        List<EmployeeProject> employeeProjectList = employeeProjectCsvReader.readFile("src/main/resources/employee_projects.csv");

        LongestCollaborationCalculator projectCollaborationCalculator = new LongestCollaborationCalculator();
        projectCollaborationCalculator.calculate(employeeProjectList);
    }
}
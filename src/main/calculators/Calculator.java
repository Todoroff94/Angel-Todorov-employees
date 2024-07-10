package main.calculators;

import main.projects.EmployeeProject;
import java.util.List;

public interface Calculator {

    void calculate(List<EmployeeProject> employeeProjects);
}
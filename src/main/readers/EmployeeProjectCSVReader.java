package main.readers;

import main.exceptions.FileReadingException;
import main.exceptions.InvalidFieldDataException;
import main.exceptions.InvalidFileTypeException;
import main.exceptions.MissingHeadersException;
import main.projects.EmployeeProject;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProjectCSVReader implements FileReader {

    private static final String CSV_SPLITTER = ",";

    @Override
    public List<EmployeeProject> readFile(String csvFile) throws InvalidFileTypeException, FileReadingException {

        String line;
        List<EmployeeProject> employeeProjects = new ArrayList<>();

        if (!csvFile.endsWith(".csv")) {
            throw new InvalidFileTypeException("You can only read from a CSV file.");
        }

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(csvFile));
            String[] requiredHeaders = {"EmpID", "ProjectID", "DateFrom", "DateTo"};

            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new MissingHeadersException("CSV file is missing headers.");
            }
            String[] headers = headerLine.split(CSV_SPLITTER, -1);

            for (String requiredHeader : requiredHeaders) {
                boolean headerFound = false;
                for (String header : headers) {
                    if (header.equalsIgnoreCase(requiredHeader)) {
                        headerFound = true;
                        break;
                    }
                }
                if (!headerFound) {
                    throw new MissingHeadersException("Missing required header: " + requiredHeader);
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(CSV_SPLITTER, -1);

                for (int i = 0; i < data.length; i++) {
                    if (!headers[i].equals("DateTo") && (data[i] == null || data[i].isEmpty())) {
                        throw new InvalidFieldDataException("NULL or EMPTY value cannot be passed for " + headers[i]);
                    }
                }

                int empId = Integer.parseInt(data[0].trim());
                int projectId = Integer.parseInt(data[1].trim());
                String dateFrom = data[2].trim();

                String dateTo;
                if (data[3].trim().isEmpty() || data[3].trim().equalsIgnoreCase("NULL")) {
                    dateTo = LocalDate.now().toString();
                } else {
                    dateTo = data[3].trim();
                }

                employeeProjects.add(new EmployeeProject(empId, projectId, dateFrom, dateTo));
            }
        } catch (IOException | InvalidFieldDataException e) {
            throw new FileReadingException(e.getMessage(), e);
        }
        return employeeProjects;
    }
}
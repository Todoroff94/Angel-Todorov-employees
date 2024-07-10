package main.projects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeProject {

    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeProject(int empId, int projectId, String dateFrom, String dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = parseDate(dateFrom);
        this.dateTo = parseDate(dateTo, LocalDate.now());
    }

    private static final Map<String, DateTimeFormatter> FORMATTERS = new HashMap<>();

    static {
        FORMATTERS.put("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        FORMATTERS.put("yyyy-MM-dd'T'HH:mm:ss", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        FORMATTERS.put("yyyy-MM-dd'T'HH:mm:ssXXX", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
        FORMATTERS.put("yyyyMMdd", DateTimeFormatter.BASIC_ISO_DATE);
        FORMATTERS.put("dd-MM-yyyy HH:mm:ss", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    private LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, null);
    }

    private LocalDate parseDate(String dateStr, LocalDate defaultValue) {
        for (DateTimeFormatter formatter : FORMATTERS.values()) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Swallow the exception and try the next formatter
            }
        }
        return defaultValue;
    }

    public int getEmpId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
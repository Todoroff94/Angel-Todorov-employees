package main.calculators;

import main.projects.EmployeeProject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCollaborationCalculator implements Calculator {

    @Override
    public void calculate(List<EmployeeProject> employeeProjects) {

        Map<Integer, List<EmployeeProject>> projectMap = new HashMap<>();
        Map<String, Long> collaborationDurations = new HashMap<>();

        for (EmployeeProject ep : employeeProjects) {
            projectMap.computeIfAbsent(ep.getProjectId(), k -> new ArrayList<>()).add(ep);
        }

        for (List<EmployeeProject> projectList : projectMap.values()) {
            int size = projectList.size();

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    EmployeeProject ep1 = projectList.get(i);
                    EmployeeProject ep2 = projectList.get(j);
                    long overlap = calculateOverlap(ep1, ep2);

                    String pairKey = null;
                    if (overlap > 0) {
                        pairKey = ep1.getEmpId() + "-" + ep2.getEmpId();
                        collaborationDurations.put(pairKey, collaborationDurations.getOrDefault(pairKey, 0L) + overlap);

                    }
                }
            }
        }

        String longestCollaborationPair = null;
        long longestDuration = 0;

        for (Map.Entry<String, Long> entry : collaborationDurations.entrySet()) {
            if (entry.getValue() > longestDuration) {
                longestDuration = entry.getValue();
                longestCollaborationPair = entry.getKey();
            }
        }
        System.out.println("The pair of employees who have worked together for the longest period is: " + longestCollaborationPair);
        System.out.println("They worked together for " + longestDuration + " days.");
    }

    private long calculateOverlap(EmployeeProject ep1, EmployeeProject ep2) {
        LocalDate latestStart = ep1.getDateFrom().isAfter(ep2.getDateFrom()) ? ep1.getDateFrom() : ep2.getDateFrom();
        LocalDate earliestEnd = ep1.getDateTo().isBefore(ep2.getDateTo()) ? ep1.getDateTo() : ep2.getDateTo();
        if ((ep1.getEmpId() != ep2.getEmpId()) && (latestStart.isBefore(earliestEnd) || latestStart.isEqual(earliestEnd))) {
            return ChronoUnit.DAYS.between(latestStart, earliestEnd);
        }
        return 0;
    }
}
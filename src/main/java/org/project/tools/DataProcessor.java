package org.project.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Main;
import org.project.entities.Employee;
import org.project.entities.Manager;
import org.project.entities.Person;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class DataProcessor {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void processData(List<Manager> managers, List<Employee> employees,
                                   List<String> incorrectData, MyCommandLineOptions options) {
        if (options.getSortingType().equals("name")){
            if(options.getSortingOrder().equals("asc")){
                employees.sort(Comparator.comparing(Person::getName));
            } else {
                employees.sort(Comparator.comparing(Employee::getName, Comparator.reverseOrder()));
            }
        } else if (options.getSortingType().equals("salary")){
            if(options.getSortingOrder().equals("asc")){
                employees.sort(Comparator.comparing(Person::getSalary));
            } else {
                employees.sort(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()));
            }
        }

        managers.sort(Comparator.comparing(Manager::getDepartmentName));
        List <Integer> departments = managers.stream().map(Manager::getId).toList();

        if (options.getOutputType().equals("console")){
            printToConsole(managers, employees, departments, incorrectData, options);
        } else {
            printToFile(managers, employees, departments, incorrectData, options);
        }

    }

    private static void printToConsole(List<Manager> managers, List<Employee> employees, List <Integer> departments,
                                       List<String> incorrectData, MyCommandLineOptions options) {
        logger.debug("Printing to console...");
        for (Manager manager : managers){
            System.out.println(manager.getDepartmentName());
            System.out.println(manager);
            List <Employee> subordinates = employees.stream().filter(e -> e.getDepartmentId() == manager.getId()).toList();
            subordinates.forEach(System.out::println);
            int count = subordinates.size() + 1;
            double salary = (subordinates.stream().mapToDouble(Person::getSalary).sum()+ manager.getSalary()) / count;
            BigDecimal roundedSalary = BigDecimal.valueOf(salary).setScale(2, RoundingMode.HALF_UP);
            System.out.println(count + ", " + roundedSalary);
        }
        System.out.println("Invalid data:");
        employees.stream().filter(e -> !departments.contains(e.getDepartmentId()))
                .forEach(e -> System.out.println(e + ", " + e.getDepartmentId()));
        incorrectData.forEach(System.out::println);
        logger.debug("Printing to console finished.");
    }

    private static void printToFile(List<Manager> managers, List<Employee> employees, List <Integer> departments,
                                    List<String> incorrectData, MyCommandLineOptions options) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(options.getOutputFile(), true))) {
            logger.debug("Printing to file...");
            for (Manager manager : managers){
                writer.write(manager.getDepartmentName());
                writer.newLine();
                writer.write(manager.toString());
                writer.newLine();
                List <Employee> subordinates = employees.stream().filter(e -> e.getDepartmentId() == manager.getId()).toList();
                subordinates.forEach(s -> {
                    try {
                        writer.write(s.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                int count = subordinates.size() + 1;
                double salary = (subordinates.stream().mapToDouble(Person::getSalary).sum() + manager.getSalary()) / count;
                BigDecimal roundedSalary = BigDecimal.valueOf(salary).setScale(2, RoundingMode.HALF_UP);
                writer.write(count + ", " + roundedSalary);
                writer.newLine();
            }
            writer.write("Invalid data:");
            writer.newLine();
            employees.stream().filter(e -> !departments.contains(e.getDepartmentId())).forEach(employee -> {
                try {
                    writer.write(employee.toString() + ", " + employee.getDepartmentId());
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            incorrectData.forEach(i -> {
                try {
                    writer.write(i);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            logger.debug("Printing to file finished.");
        } catch (IOException e){
            logger.error("IOException while writing to file", e);
        }
    }
}

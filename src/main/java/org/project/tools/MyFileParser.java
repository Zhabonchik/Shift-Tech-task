package org.project.tools;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Main;
import org.project.entities.Employee;
import org.project.entities.Manager;
import org.project.entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MyFileParser {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private List<String> incorrectData = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();

    public void parsePeopleFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try{
                    Person person = parsePerson(line);
                    if (person.isManager()) {
                        managers.add((Manager) person);
                    } else {
                        employees.add((Employee) person);
                    }
                } catch (IllegalArgumentException e){
                    logger.error("Exception while parsing from file: {}", e.getMessage());
                    incorrectData.add(line);
                }
            }
        } catch (IOException e) {
            logger.error("Cannot read file: {}", e.getMessage());
        }
    }

    public Person parsePerson(String line) {
        String[] fields = line.split(",");

        if (fields.length != 5) {
            throw new IllegalArgumentException("Wrong number of fields in line:" + line);
        }

        String type = fields[0].trim();
        int id = parseInt(fields[1], "ID", line);
        String name = fields[2].trim();
        double salary = parseDouble(fields[3], "Salary", line);
        int departmentId;
        String departmentName;

        if(id <= 0){
            throw new IllegalArgumentException("Wrong id in line:" + line);
        }
        if(name.isEmpty()){
            throw new IllegalArgumentException("Empty name in line:" + line);
        }
        if(salary <= 0.00){
            throw new IllegalArgumentException("Wrong salary in line:" + line);
        }

        if (type.equals("Manager")) {
            departmentName = fields[4].trim();
            if (departmentName.isEmpty()) {
                throw new IllegalArgumentException("Empty name in line:" + line);
            }
            return new Manager(id, name, salary, true, departmentName);
        } else if (type.equals("Employee")) {
            departmentId = parseInt(fields[4], "ID", line);
            return new Employee(id, name, salary, false, departmentId);
        } else {
            throw new IllegalArgumentException("Invalid " + line);
        }
    }

    private int parseInt(String value, String fieldName, String line) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer: " + value+ "\nin line: " + line);
        }
    }

    private double parseDouble(String value, String fieldName, String line) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number: " + value + "\nin line: " + line);
        }
    }
}

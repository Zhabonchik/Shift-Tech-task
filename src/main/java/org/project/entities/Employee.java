package org.project.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Employee extends Person{
    private int departmentId;
    public Employee(int id, String name, double salary, boolean isManager, int departmentId) {
        super(id, name, salary, isManager);
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee, " + super.toString()/* + ", " + departmentId*/;
    }
}

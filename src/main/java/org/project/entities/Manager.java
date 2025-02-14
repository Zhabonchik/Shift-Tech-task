package org.project.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Manager extends Person{

    private String departmentName;

    public Manager(int id, String name, double salary, boolean isManager, String departmentName) {
        super(id, name, salary, isManager);
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Manager, " + super.toString()/* + ", " + departmentName*/;
    }
}

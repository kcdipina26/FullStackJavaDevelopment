package com.petelevator;

import com.petelevator.Person;
import com.petelevator.hr.Department;

public class Employee extends Person {
        private String employeeId;
        private Department department;

        public Employee(String firstName, String lastName, String employeeId, Department department) {
            super(firstName, lastName);
            this.employeeId = employeeId;
            this.department = department;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String getFullName() {
            return getLastName() + " " + getFirstName();
        }
    }



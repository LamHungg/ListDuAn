package org.example.Bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee(1,"LE","Tu","Tulee@gmail.com");
        employeeService.add(employee);
    }
}

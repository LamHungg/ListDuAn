package org.example.Bai3;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<Employee> employees = new ArrayList<Employee>();

    public void add(Employee emp) {
        if (emp.getId()==0){
            System.out.println("Lỗi Id == 0");
            return;
        } else if (employees.contains(emp.getId())) {
            System.out.println("ID trùng");
            return;
        } else if (emp.getFirstName().trim().length() == 0 || emp.LastName.trim().length() == 0) {
            System.out.println("FirstName không được để trống");
            return;
        } else if (emp.getLastName().trim().length() == 0 || emp.LastName.trim().length() == 0) {
            System.out.println("LastName không được để trống");
            return;
        } else if (emp.getEmail().trim().length() == 0 || emp.LastName.trim().length() == 0) {
            System.out.println("Email không được để trống");
            return;
        }
        employees.add(emp);
    }
    public void update(Employee emp ,int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (emp.getId() == id) {
                employees.get(i).setFirstName(emp.getFirstName());
                employees.get(i).setLastName(emp.getLastName());
                employees.get(i).setEmail(emp.getEmail());
                employees.get(i).setId(id);
                return;
            }
        }
    }
    public void delete(int id) {
        Boolean remove = employees.removeIf(emp -> emp.getId() == id);
        if (!remove) {
            throw new IllegalArgumentException("Employee không tồn tại");
        }
    }
}

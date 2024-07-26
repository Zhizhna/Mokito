package com.sky.mokito.service;

import com.sky.mokito.Employee.Employee;
import com.sky.mokito.exeption.employee.EmployeeAlreadyAddedException;
import com.sky.mokito.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeIsAlreadyExisted() {

        Employee employee = new Employee("Ivan", "Petrov", 100_000, 1);
        employeeService.addEmployee(
                employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
        );

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee(
                    employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
            );
        });
    }

    @Test
    public void shouldCorrectlyFindEmployee() {

        Employee employee = new Employee("Ivan", "Petrov", 100_000, 1);
        employeeService.addEmployee(
                employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment()
        );

        Employee actualEmployee = employeeService.findEmployee(employee.getFirstName(), employee.getLastName());

        Assertions.assertEquals(employee, actualEmployee);
    }
}
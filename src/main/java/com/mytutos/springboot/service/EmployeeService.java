package com.mytutos.springboot.service;

import com.mytutos.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findAllSortedByLastName();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);



}

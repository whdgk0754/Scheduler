package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.domain.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

//    public EmployeeService(EmployeeRepository employeeRepository){
//        this.employeeRepository = employeeRepository;
//    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();

    }




}

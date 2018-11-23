package com.njindal.examples.objectpool.service;


import com.njindal.examples.objectpool.entity.Employee;

public class EmployeeObjectPool extends AbstractObjectPool<Employee> {
    @Override
    protected Employee getInstance() {
        return Employee.instance();
    }

    public EmployeeObjectPool() {
        init();
    }
}

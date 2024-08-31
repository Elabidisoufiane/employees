package com.fstg.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.employee.model.Employee;
public interface employeedao extends JpaRepository <Employee,Integer>{

	Employee findByMail(String mail);

}

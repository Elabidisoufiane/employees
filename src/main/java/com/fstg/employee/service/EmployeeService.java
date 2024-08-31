package com.fstg.employee.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.fstg.employee.dto.EmployeeRequestDto;
import com.fstg.employee.dto.EmployeeResponseDto;

public interface EmployeeService {

	
	EmployeeResponseDto save(EmployeeRequestDto Dto);
	
	EmployeeResponseDto findById(Integer id);
	
	EmployeeResponseDto findByMail(String title);
	
	void delete(Integer id);
	
	List<EmployeeResponseDto>  findAll();

	EmployeeResponseDto update(EmployeeRequestDto dto,Integer id) throws NotFoundException;


}

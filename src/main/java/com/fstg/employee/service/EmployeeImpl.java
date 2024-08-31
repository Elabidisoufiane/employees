package com.fstg.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fstg.employee.dao.employeedao;
import com.fstg.employee.dto.EmployeeRequestDto;
import com.fstg.employee.dto.EmployeeResponseDto;
import com.fstg.employee.exception.EntityNotFoundException;
import com.fstg.employee.model.Employee;

@Service()
public class EmployeeImpl implements EmployeeService {
	private employeedao dao;
	private ModelMapper mapper ;
	private final JavaMailSender emailSender;

	public EmployeeImpl(employeedao dao ,ModelMapper mapper,JavaMailSender emailSender) {
		this.dao=dao;
		this.mapper = mapper ;
		this.emailSender=emailSender;
	}
	@Override 
    public EmployeeResponseDto save(EmployeeRequestDto Dto) {
		Employee employee = mapper.map( Dto , Employee.class );
		Employee saved = dao.save(employee);
		        // Send email after saving the employee
				sendEmail(saved.getMail(), saved.getCode());
		return mapper.map(saved,EmployeeResponseDto.class);
	}
	
	@Override
	public EmployeeResponseDto findById(Integer id) {
		
		Employee offer = dao.findById(id).orElseThrow(()-> new EntityNotFoundException("offer not found"));
		
		return mapper.map(offer,EmployeeResponseDto.class);

	}
	
	@Override
	public EmployeeResponseDto findByMail(String mail) {
		Employee offer = dao.findByMail(mail);
		return mapper.map(offer,EmployeeResponseDto.class);

	}
	
/*
	@Override
	public JobOfferResponseDto findByRecruiter(Integer Rec) {
		JobOffer offer = dao.findByRecruiter(Rec);
		return mapper.map(offer,JobOfferResponseDto.class);
	}
	*/
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	@Override
	public EmployeeResponseDto update(EmployeeRequestDto dto , Integer id) throws  NotFoundException{
		Optional<Employee> offer = dao.findById(id);
		if(offer.isPresent()) {
			Employee joboffer = mapper.map(dto, Employee.class);
			joboffer.setId(id);
			Employee updated = dao.save(joboffer);
			return mapper.map(updated, EmployeeResponseDto.class);
		}else {
			throw new EntityNotFoundException("offer not found");
		}

	}
	
	@Override
	public List<EmployeeResponseDto> findAll(){
		return dao.findAll().stream()
				.map(el -> mapper
				.map(el, EmployeeResponseDto.class))
				.collect(Collectors.toList());
	}

	private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Employee Code");
        message.setText("Hello, \n\nYour code is: " + code);
        emailSender.send(message);
    }
	

}

package com.fstg.employee.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fstg.employee.dto.EmployeeRequestDto;
import com.fstg.employee.dto.EmployeeResponseDto;
import com.fstg.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
private EmployeeService service ;
	
	public EmployeeController(EmployeeService service) {
		this.service= service ;
	}
	
	@GetMapping("/employees")
	public ResponseEntity< List<EmployeeResponseDto> > getJobOffers(){
		//return service.findAll();	
		return new ResponseEntity<> (service.findAll(),HttpStatus.OK) ;
	}
	@PostMapping("/AddEmployee")
	public ResponseEntity<EmployeeResponseDto> save(@Valid @RequestBody() EmployeeRequestDto request) {
		EmployeeResponseDto dto = service.save(request) ;
		return new ResponseEntity <> (dto,HttpStatus.CREATED) ;
	}
	
	@PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@Valid @RequestBody EmployeeRequestDto produitDto,@PathVariable Integer id) throws NotFoundException {
		EmployeeResponseDto dto = service.update(produitDto, id);
        return ResponseEntity.accepted().body(dto);
    }
	
	@GetMapping("/employee/id/{id}")
	public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Integer id) {
		EmployeeResponseDto dto = service.findById(id) ;
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/employee/mail/{mail}")
	public EmployeeResponseDto findByTitle(@PathVariable String mail) {
		return service.findByMail(mail);
	}
	
	@DeleteMapping("/employee/id/{id}")
	public ResponseEntity<?> delet(@PathVariable Integer id) {
		service.delete(id);
        return ResponseEntity.noContent().build();
	}

}

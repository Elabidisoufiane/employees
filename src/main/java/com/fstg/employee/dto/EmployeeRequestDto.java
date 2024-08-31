package com.fstg.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
	@NotBlank(message = "firstName est obligatoire !!")
    @Size(min = 5, message = "firstName doit avoir au moins 3 characteres")
    @Size(max = 20, message = "firstName ne doit pas depasser 20 characteres")
	private String firstName;
	
	@NotBlank(message = "lastName est obligatoire !!")
    @Size(min = 3, message = "lastName doit avoir au moins 3 characteres")
    @Size(max = 20, message = "lastName ne doit pas depasser 20 characteres")
	private String lastName;
	
	@NotBlank(message = "L'email est obligatoire !")
    @Email(message = "L'adresse email doit être valide !")
	private String mail ;

	@NotBlank(message = "lastName est obligatoire !!")
    @Size(min = 8, message = "lastName doit avoir  5 characteres")
    @Size(max = 8, message = "lastName doit avoir  5 characteres")
	private String code;

}

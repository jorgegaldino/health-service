package uk.co.mycompany.healthservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mycompany.healthservice.domain.dto.DoctorDto;
import uk.co.mycompany.healthservice.service.HealthService;

import java.util.List;

@RestController
@RequestMapping("/doctor-title")
public class DoctorTitleController {
	
	@Autowired
	private HealthService service;

	@GetMapping(value = "/all")
	public ResponseEntity<List<String>> listDoctorTitleAll() {
		
		try {
			List<String> list = service.listAllDoctorsTitles();

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}



}

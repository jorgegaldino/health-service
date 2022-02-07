package uk.co.mycompany.healthservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.mycompany.healthservice.domain.dto.DoctorDto;
import uk.co.mycompany.healthservice.service.HealthService;

@RestController
@RequestMapping("/doctor-location")
public class DoctorLocationController {
	
	@Autowired
	private HealthService service;

	@GetMapping(value = "/{title}/{latitude}/{longitude}")
	public ResponseEntity<List<DoctorDto>> listByTitleAndLocation(
			@PathVariable Double latitude,
			@PathVariable Double longitude,
			@PathVariable String title) {
		
		try {
			List<DoctorDto> lista = service.listDoctorsByTitleAndLocation(title,latitude,longitude);
			
			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}

	@GetMapping(value = "/{title}/{address}")
	public ResponseEntity<List<DoctorDto>> listByTitleAndCurrentAddress(
			@PathVariable String address,
			@PathVariable String title) {

		try {
			List<DoctorDto> lista = service.listDoctorsByTitleAndLocation(title,address);

			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


	}


	@GetMapping(value = "/{title}")
	public ResponseEntity<List<DoctorDto>> listByTitle(
			@PathVariable String title) {

		try {

			List<DoctorDto> lista = service.listDoctorsByTitle(title);

			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


	}

	@GetMapping(value = "/teste")
	public ResponseEntity<List<DoctorDto>> teste() {
		List<DoctorDto> lista = service.listAllDoctors();

		return ResponseEntity.ok(lista);
	}


}

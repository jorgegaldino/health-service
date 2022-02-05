package uk.co.mycompany.health.service.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.mycompany.health.service.dto.HealthPartnerDTO;
import uk.co.mycompany.health.service.entity.HealthPartner;
import uk.co.mycompany.health.service.service.HealthPartnerService;

@RestController
@RequestMapping("/doctor-location")
public class HealthPartnerResource {
	
	@Autowired
	private HealthPartnerService service;

	@GetMapping(value = "/{especialidade}/{latitude}/{longitude}")
	public ResponseEntity<List<HealthPartnerDTO>> listarTodos(
			@PathVariable Double latitude,
			@PathVariable Double longitude,
			@PathVariable String especialidade) {
		
		try {
			List<HealthPartner> lista = service.listarTodos(latitude, longitude, especialidade);
			
			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			ModelMapper modelMapper = new ModelMapper();
			List<HealthPartnerDTO> dtos = 
					lista.stream()
					.map(prestador -> modelMapper.map(prestador, HealthPartnerDTO.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		

	}


	@GetMapping(value = "/{especialidade}")
	public ResponseEntity<List<HealthPartnerDTO>> listarTodos2(
			@PathVariable String especialidade) {

		try {
			List<HealthPartner> lista = service.listarTodos( especialidade);

			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			ModelMapper modelMapper = new ModelMapper();
			List<HealthPartnerDTO> dtos =
					lista.stream()
							.map(prestador -> modelMapper.map(prestador, HealthPartnerDTO.class))
							.collect(Collectors.toList());

			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


	}

	@GetMapping(value = "/teste")
	public ResponseEntity<List<HealthPartnerDTO>> teste() {
		ModelMapper modelMapper = new ModelMapper();
		List<HealthPartner> lista = service.listarTodos();
		List<HealthPartnerDTO> dtos =
				lista.stream()
						.map(prestador -> modelMapper.map(prestador, HealthPartnerDTO.class))
						.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}


}

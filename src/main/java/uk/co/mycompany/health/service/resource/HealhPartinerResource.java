package uk.co.mycompany.health.service.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.mycompany.health.service.dto.HealthPartnerDTO;
import uk.co.mycompany.health.service.entity.HealthPartner;
import uk.co.mycompany.health.service.service.HealthPartnerService;

@RestController
@RequestMapping("/health-service")
public class HealhPartinerResource {
	
	@Autowired
	private HealthPartnerService service;
	
	@GetMapping
	public ResponseEntity<List<HealthPartnerDTO>> listarTodos(
			@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude,
			@RequestParam("especialidade") String especialidade) {
		
		try {
			List<HealthPartner> lista = service.listarTodos(latitude, longitude, especialidade);
			
			if(lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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

}

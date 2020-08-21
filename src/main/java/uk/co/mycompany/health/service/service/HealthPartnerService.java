package uk.co.mycompany.health.service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uk.co.mycompany.health.service.entity.HealthPartner;
import uk.co.mycompany.health.service.util.Util;

@Service
public class HealthPartnerService {
	
	private static List<HealthPartner> prestadoresList = Arrays.asList(
			new HealthPartner("Uniodonto - Núcleo Aldeota", "Av. Santos Dumont, 1302 - Aldeota, Fortaleza - CE, 60150-160", "odontologica", -3.7320588, -38.5143321),
			new HealthPartner("Dr. Robson Carvalho", "Av. Pontes Vieira, 679 - Sala 2 - Dionísio Torres, Fortaleza - CE, 60130-240", "odontologica", -3.7527596, -38.5173191),
			new HealthPartner("Odontoart - Parangaba", "Av. Dr. Silas Munguba, 170 - Parangaba, Fortaleza - CE, 60740-002", "odontologica", -3.7770551, -38.5647601),
			new HealthPartner("Clínica Arcanjo", "R. Graciliano Ramos, 278 - Fátima, Fortaleza - CE, 60415-050", "Oftalmologista ", -3.7551131, -38.5307581),
			new HealthPartner("Edifício Santa Monica", "Av. Tristão Gonçalves, 1366 - Benfica, Fortaleza - CE, 60015-002", "Dermatologista", -3.7337552, -38.5374973),
			new HealthPartner("Clínica Pediatrica Alberto Lima", "R. Catão Mamede, 836 - Aldeota, Fortaleza - CE, 60140-110", "pediatra", -3.7396935, -38.5073618),
			new HealthPartner("Nacionalfisio", "Rua Luiza Miranda Coelho, 470 - Eng. Luciano Cavalcante, Fortaleza - CE, 60811-110", "fisioterapia", -3.773367, -38.4947307),
			new HealthPartner("Clínica de Fisioterapia Melfa", "R. Tibúrcio Cavalcante, 16 - Meireles, Fortaleza - CE, 60125-100", "fisioterapia", -3.725196, -38.5013709));

	public List<HealthPartner> listarTodos(Double latitude, Double longitude, String especialidade) throws Exception {
		List<HealthPartner> lista = 
				prestadoresList.stream()
				.filter(prestador -> especialidade.toUpperCase().equals(prestador.getEspecialidade().toUpperCase()))
				.collect(Collectors.toList());
		
		if(lista.isEmpty()) {
			return new ArrayList<>();
		}
				
		for (HealthPartner prestadorSaude : lista) {
			Long distancia = LocationService.consultarLocalizacao(latitude, longitude, prestadorSaude);
			
			if(distancia == 0) {
				return new ArrayList<>();
			}
			prestadorSaude.setDistanciaEmKm(Util.kmTomiles(distancia.doubleValue()));

		}
		
		return lista;
	}



}

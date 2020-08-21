package uk.co.mycompany.health.service.service;

import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.mycompany.health.service.entity.HealthPartner;
import uk.co.mycompany.health.service.entity.Location;
import uk.co.mycompany.health.service.entity.Location.Distance;
import uk.co.mycompany.health.service.entity.Location.Element;
import uk.co.mycompany.health.service.entity.Location.Row;

public class LocationService {
	
	private static String valueKeyGoogleMaps = "googleTokenId";

	
	public static Long consultarLocalizacao(Double latitude, Double longitude, HealthPartner prestadorSaude) throws Exception {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+ latitude + "," + longitude 
				+ "&destinations=" + prestadorSaude.getLongitude() +",%20" + prestadorSaude.getLatitude() +"&key=" + valueKeyGoogleMaps;
		
		try {
			HttpClient client = HttpClientBuilder.create().build();    
			HttpResponse response = client.execute(new HttpGet(url));
			String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
		    Location location = mapper.readValue(responseBody, Location.class);
		    for (Row row : location.getRows()) {
				for (Element element : row.getElements()) {
					if(element.getStatus().equals("ZERO_RESULTS")) {
						return 0L;
					}
					Distance distance = element.getDistance();
					return distance.getValue();
				}
			}
		    return 0L;
		}catch (Exception e) {
			throw new Exception("Erro ao consultar a api", e);
		}
	}

}

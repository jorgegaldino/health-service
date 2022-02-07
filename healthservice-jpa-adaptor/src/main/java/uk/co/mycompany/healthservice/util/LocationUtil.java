package uk.co.mycompany.healthservice.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.co.mycompany.healthservice.domain.DoctorModel;
import uk.co.mycompany.healthservice.entity.Location;
import uk.co.mycompany.healthservice.entity.Location.Distance;
import uk.co.mycompany.healthservice.entity.Location.Element;
import uk.co.mycompany.healthservice.entity.Location.Row;
import uk.co.mycompany.healthservice.exception.HealthServiceException;

@Component
public class LocationUtil {

	@Value("${distanceMatrixApi.key}")
	private String valueKeyGoogleMaps;

	
	public Double calcDistance(Double latitude, Double longitude, DoctorModel doctorModel)
			throws HealthServiceException {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+ latitude + "," + longitude 
				+ "&destinations=" + doctorModel.getLatitude() +",%20" + doctorModel.getLongitude() +"&key=" + valueKeyGoogleMaps;
		
		try {
			HttpClient client = HttpClientBuilder.create().build();    
			HttpResponse response = client.execute(new HttpGet(url));
			String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
		    Location location = mapper.readValue(responseBody, Location.class);
		    for (Row row : location.getRows()) {
				for (Element element : row.getElements()) {
					if(element.getStatus().equals("ZERO_RESULTS")) {
						//log
						return 0.0;
					}
					Distance distance = element.getDistance();

					return distance.getValue() * 0.621371/1000; //Miles
				}
			}
			return 0.0;
		}catch (Exception e) {
			//log
			throw new HealthServiceException(String.format("Error Location API - %s", e.getMessage()));
		}
	}

	public Double calcDistance(String address, DoctorModel doctorModel)
			throws HealthServiceException {
		String baseUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";

		try {
			StringBuilder url = new StringBuilder();

			url.append(baseUrl);
			url.append(URLEncoder.encode(address, StandardCharsets.UTF_8.toString()));
			url.append("&destinations=");
			url.append(URLEncoder.encode(doctorModel.getAddress(), StandardCharsets.UTF_8.toString()));
			url.append("&key=");
			url.append(valueKeyGoogleMaps);

			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = client.execute(new HttpGet(url.toString()));
			String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			Location location = mapper.readValue(responseBody, Location.class);
			for (Row row : location.getRows()) {
				for (Element element : row.getElements()) {
					if(element.getStatus().equals("ZERO_RESULTS")) {
						//log
						return 0.0;
					}
					Distance distance = element.getDistance();

					return distance.getValue() * 0.621371/1000; //Miles
				}
			}
			return 0.0;
		} catch (UnsupportedEncodingException e){
			//log
			throw new HealthServiceException(String.format("Error Location API - %s", e.getMessage()));
		} catch (Exception e) {
			//log
			throw new HealthServiceException(String.format("Error Location API - %s", e.getMessage()));
		}
	}

}

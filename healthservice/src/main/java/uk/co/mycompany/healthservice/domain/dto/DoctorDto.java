package uk.co.mycompany.healthservice.domain.dto;

import lombok.Data;

@Data
public class DoctorDto {

	private String name;

	private String address;

	private Double distance;

	private String title;

	public String getFormattedDistance() {
		if (distance != null) {
			return String.format("%,.4f Miles", distance);
		} else {
			return null;
		}

	}

}

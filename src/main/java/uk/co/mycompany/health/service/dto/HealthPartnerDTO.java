package uk.co.mycompany.health.service.dto;

import lombok.Data;

@Data
public class HealthPartnerDTO {

	private String nome;

	private String endereco;

	private Double longitude;

	private Double latitude;

	private Double distanciaEmKm;

}

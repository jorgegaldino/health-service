package uk.co.mycompany.health.service.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HealthPartner {

	@NonNull
	private String nome;

	@NonNull
	private String especialidade;

	@NonNull
	private String endereco;

	@NonNull
	private Double longitude;

	@NonNull
	private Double latitude;

	private Double distanciaEmKm;

}

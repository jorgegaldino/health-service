package uk.co.mycompany.health.service.dto;

public class HealthPartnerDTO {

	private String nome;
	private String endereco;
	private Double longitude;
	private Double latitude;
	private Double distanciaEmKm;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getDistanciaEmKm() {
		return distanciaEmKm;
	}

	public void setDistanciaEmKm(Double distanciaEmKm) {
		this.distanciaEmKm = distanciaEmKm;
	}

}

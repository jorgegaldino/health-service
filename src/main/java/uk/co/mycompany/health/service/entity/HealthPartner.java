package uk.co.mycompany.health.service.entity;

public class HealthPartner {

	private String nome;
	private String especialidade;
	private String endereco;
	private Double longitude;
	private Double latitude;
	private Double distanciaEmKm;

	public HealthPartner() {
		super();
	}

	public HealthPartner(String nome, String endereco, String especialidade, Double longitude, Double latitude) {
		super();
		this.nome = nome;
		this.especialidade = especialidade;
		this.endereco = endereco;
		this.longitude = longitude;
		this.latitude = latitude;
	}

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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

}

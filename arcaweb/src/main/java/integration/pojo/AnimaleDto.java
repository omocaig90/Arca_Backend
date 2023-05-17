package integration.pojo;

import integration.enumeratori.EnumSpecie;

public class AnimaleDto {
	private Integer id;
	private Integer peso;
	private EnumSpecie specie;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public EnumSpecie getSpecie() {
		return specie;
	}

	public void setSpecie(EnumSpecie specie) {
		this.specie = specie;
	}
}

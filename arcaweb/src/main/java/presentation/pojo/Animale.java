package presentation.pojo;

import integration.enumeratori.EnumSpecie;

public class Animale {
	private Integer id;
	private Integer peso;
	private EnumSpecie specie;

//metodi get

	public void setId(int id) {
		this.id = id;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setSpecie(EnumSpecie specie) {
		this.specie = specie;
	}

	public Integer getPeso() {
		return peso;
	}

	public EnumSpecie getSpecie() {
		return specie;
	}

	public Integer getId() {
		return id;
	}
}

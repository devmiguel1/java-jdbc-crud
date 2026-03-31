package com.meuapp.model;

public class Veterinario {

	private String crmv;
	private String nomeVeterinario;
	private String especialidade;
	private String telefone;

	public Veterinario(String crmv, String nomeVeterinario, String especialidade, String telefone) {
		this.crmv = crmv;
		this.nomeVeterinario = nomeVeterinario;
		this.especialidade = especialidade;
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "|" + crmv + "|" + nomeVeterinario + "|" + especialidade + "|" + telefone + "|";
	}

	public String getCrmv() {
		return crmv;
	}

	public String getNomeVeterinario() {
		return nomeVeterinario;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public void setNomeVeterinario(String nomeVeterinario) {
		this.nomeVeterinario = nomeVeterinario;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

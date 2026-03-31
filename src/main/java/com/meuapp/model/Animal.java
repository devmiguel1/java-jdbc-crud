package com.meuapp.model;

import java.time.LocalDate;

public class Animal {

	private int idAnimal;
	private String cpfProprietario;
	private String nomeAnimal;
	private String especie;
	private String raca;
	private LocalDate dataNascimento;
	private double peso;

	public Animal(int idAnimal, String cpfProprietario, String nomeAnimal, String especie, String raca,
			LocalDate dataNascimento, double peso) {
		this.idAnimal = idAnimal;
		this.cpfProprietario = cpfProprietario;
		this.nomeAnimal = nomeAnimal;
		this.especie = especie;
		this.raca = raca;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
	}

	public Animal(String cpfProprietario, String nomeAnimal, String especie, String raca, LocalDate dataNascimento,
			double peso) {
		this.cpfProprietario = cpfProprietario;
		this.nomeAnimal = nomeAnimal;
		this.especie = especie;
		this.raca = raca;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "|" + idAnimal + "|" + cpfProprietario + "|" + nomeAnimal + "|" + especie + "|" + raca + "|"
				+ dataNascimento + "|" + peso + "|";
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public String getCpfProprietario() {
		return cpfProprietario;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public String getEspecie() {
		return especie;
	}

	public String getRaca() {
		return raca;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public double getPeso() {
		return peso;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

}
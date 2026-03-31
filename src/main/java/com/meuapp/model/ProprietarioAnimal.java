package com.meuapp.model;

public class ProprietarioAnimal {

	private String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;

	public ProprietarioAnimal(String cpf, String nome, String telefone, String endereco, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	@Override
	public String toString() {
		return "|" + cpf + "|" + nome + "|" + telefone + "|" + endereco + "|" + email + "|";
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
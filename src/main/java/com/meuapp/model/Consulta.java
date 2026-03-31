package com.meuapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

	private int idConsulta;
	private int idAnimal;
	private String crmv;
	private LocalDate data_consulta;
	private LocalTime hora_consulta;
	private String diagnostico;
	private double valor;

	public Consulta(int idConsulta, int idAnimal, String crmv, LocalDate data_consulta, LocalTime hora_consulta,
			String diagnostico, double valor) {
		this.idConsulta = idConsulta;
		this.idAnimal = idAnimal;
		this.crmv = crmv;
		this.data_consulta = data_consulta;
		this.hora_consulta = hora_consulta;
		this.diagnostico = diagnostico;
		this.valor = valor;
	}

	public Consulta(int idAnimal, String crmv, LocalDate data_consulta, LocalTime hora_consulta, String diagnostico,
			double valor) {
		this.idAnimal = idAnimal;
		this.crmv = crmv;
		this.data_consulta = data_consulta;
		this.hora_consulta = hora_consulta;
		this.diagnostico = diagnostico;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "|" + idConsulta + "|" + idAnimal + "|" + crmv + "|" + data_consulta + "|" + hora_consulta + "|"
				+ diagnostico + "|" + valor + "|";
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public String getCrmv() {
		return crmv;
	}

	public LocalDate getData_consulta() {
		return data_consulta;
	}

	public LocalTime getHora_consulta() {
		return hora_consulta;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public double getValor() {
		return valor;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public void setData_consulta(LocalDate data_consulta) {
		this.data_consulta = data_consulta;
	}

	public void setHora_consulta(LocalTime hora_consulta) {
		this.hora_consulta = hora_consulta;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
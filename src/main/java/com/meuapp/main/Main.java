package com.meuapp.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.meuapp.dao.BancoDAO;
import com.meuapp.model.Animal;
import com.meuapp.model.Consulta;
import com.meuapp.model.ProprietarioAnimal;
import com.meuapp.model.Veterinario;

public class Main {

	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private static ProprietarioAnimal proprietarioAnimal;
	private static Animal animal;
	private static Veterinario veterinario;
	private static Consulta consulta;
	private static BancoDAO dao;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int codigo1;
		int codigo2;
		System.out.println("CRUD Clinica Veterinaria");
		do {

			codigo1 = lerInt("\nEscolha o que deseja fazer: " + "\n  (0) Sair" + "\n  (1) Inserir" + "\n  (2) Consultar"
					+ "\n  (3) Atualizar" + "\n  (4) Deletar\n");

			if (codigo1 == 0) {
				break;
			}

			codigo2 = lerInt("\nQual tabela voce quer mexer: " + "\n  (1) Proprietario do Animal" + "\n  (2) Animal"
					+ "\n  (3) Veterinario" + "\n  (4) Consulta\n");

			switchTabela(codigo1, codigo2);

		} while (codigo1 != 0);

	}

	public static void switchTabela(int codigo1, int codigo2) {
		switch (codigo1) {
//		Inserir
		case 1:
			switch (codigo2) {
//			Proprietario do Animal
			case 1:
				inserirProprietarioAnimal();
				break;
//			Animal
			case 2:
				inserirAnimal();
				break;
//			Veterinario
			case 3:
				inserirVeterinario();
				break;
//			Consulta
			case 4:
				inserirConsulta();
				break;
			}
			break;
//		Consultar
		case 2:
			switch (codigo2) {
//			Proprietario do Animal
			case 1:
				consultarProprietarioAnimal();
				break;
//			Animal
			case 2:
				consultarAnimal();
				break;
//			Veterinario
			case 3:
				consultarVeterinario();
				break;
//			Consulta
			case 4:
				consultarConsulta();
				break;
			}
			break;
//		Atualizar
		case 3:
			switch (codigo2) {
//			Proprietario do Animal
			case 1:
				atualizarProprietarioAnimal();
				break;
//			Animal
			case 2:
				atualizarAnimal();
				break;
//			Veterinario
			case 3:
				atualizarVeterinario();
				break;
//			Consulta
			case 4:
				atualizarConsulta();
				break;
			}
			break;
//		Deletar
		case 4:
			switch (codigo2) {
//			Proprietario do Animal
			case 1:
				deletarProprietarioAnimal();
				break;
//			Animal
			case 2:
				deletarAnimal();
				break;
//			Veterinario
			case 3:
				deletarVeterinario();
				break;
//			Consulta
			case 4:
				deletarConsulta();
				break;

			}
			break;
		}
	}

//	tabela ProprietarioAnimal
	private static void inserirProprietarioAnimal() {
		dao = new BancoDAO();
		proprietarioAnimal = retornarProprietarioAnimal();
		dao.inserirProprietario(proprietarioAnimal);
	}

	private static ProprietarioAnimal retornarProprietarioAnimal() {
		System.out.println("Informe seu CPF: ");
		String cpf = sc.nextLine();

		System.out.println("Informe seu nome: ");
		String nome = sc.nextLine();

		System.out.println("Informe seu telefone: ");
		String telefone = sc.nextLine();

		System.out.println("Informe seu endereco: ");
		String endereco = sc.nextLine();

		System.out.println("Informe seu email: ");
		String email = sc.nextLine();

		proprietarioAnimal = new ProprietarioAnimal(cpf, nome, telefone, endereco, email);
		return proprietarioAnimal;
	}

	private static void consultarProprietarioAnimal() {
		dao = new BancoDAO();

		List<ProprietarioAnimal> lista = dao.consultarTodosProprietarios();
		System.out.println("|    CPF    |  Nome  |   Telefone   |   Endereco   |   Email   |");
		for (ProprietarioAnimal p : lista) {
			System.out.println(p);
		}
	}

	private static void atualizarProprietarioAnimal() {
		dao = new BancoDAO();

		System.out.println("CPF do proprietário que deseja atualizar:");
		String cpf = sc.nextLine();

		System.out.println("Novo nome:");
		String nome = sc.nextLine();

		System.out.println("Novo telefone:");
		String telefone = sc.nextLine();

		System.out.println("Novo endereço:");
		String endereco = sc.nextLine();

		System.out.println("Novo e-mail:");
		String email = sc.nextLine();

		ProprietarioAnimal p = new ProprietarioAnimal(cpf, nome, telefone, endereco, email);

		dao.atualizarProprietario(p);
	}

	private static void deletarProprietarioAnimal() {
		dao = new BancoDAO();
		System.out.println("Digite o CPF do proprietário que deseja deletar:");
		String cpf = sc.nextLine();

		System.out.print("Tem certeza que deseja deletar? (S/N): ");
		String confirmacao = sc.nextLine();

		if (confirmacao.equalsIgnoreCase("S")) {
			dao.deletarProprietario(cpf);
			System.out.println("Proprietário deletado com sucesso!");
		} else {
			System.out.println("Operação cancelada!");
		}
	}

//	tabela ProprietarioAnimal

//	tabela Animal
	private static void inserirAnimal() {
		dao = new BancoDAO();
		animal = retornarAnimal();
		dao.inserirAnimal(animal);
	}

	private static Animal retornarAnimal() {
		System.out.println("Informe o CPF do proprietario: ");
		String cpfProprietario = sc.nextLine();

		System.out.println("Informe o nome do animal: ");
		String nomeAnimal = sc.nextLine();

		System.out.println("Informe a especie do animal: ");
		String especie = sc.nextLine();

		System.out.println("Informe a raca do animal: ");
		String raca = sc.nextLine();

		LocalDate dataNascimento = lerData("Informe a data de nascimento do animal (dd/MM/yyyy): ");

		double peso = lerDouble("Informe o peso do animal: ");

		animal = new Animal(cpfProprietario, nomeAnimal, especie, raca, dataNascimento, peso);
		return animal;
	}

	private static void consultarAnimal() {
		dao = new BancoDAO();

		List<Animal> lista = dao.consultarAnimal();
		System.out.println("| ID |  CPF Dono  |  Nome  |   Especie   |   Raca   |  Data de Nascimento | Peso |");
		for (Animal a : lista) {
			System.out.println(a);
		}
	}

	private static void atualizarAnimal() {
		dao = new BancoDAO();

		int idAnimal = lerInt("ID do animal que deseja atualizar: ");

		System.out.println("Novo CPF do proprietario: ");
		String cpfProprietario = sc.nextLine();

		System.out.println("Novo nome do animal: ");
		String nomeAnimal = sc.nextLine();

		System.out.println("Novo especie do animal: ");
		String especie = sc.nextLine();

		System.out.println("Nova raca do animal: ");
		String raca = sc.nextLine();

		System.out.println("Nova data de nascimento do animal: ");
		LocalDate dataNascimento = LocalDate.parse(sc.nextLine(), FORMATO_DATA);

		double peso = lerDouble("Informe o peso do animal: ");

		Animal a = new Animal(idAnimal, cpfProprietario, nomeAnimal, especie, raca, dataNascimento, peso);

		dao.atualizarAnimal(a);
	}

	private static void deletarAnimal() {
		dao = new BancoDAO();
		int id = lerInt("Digite o ID do animal que deseja deletar: ");

		System.out.print("Tem certeza que deseja deletar? (S/N): ");
		String confirmacao = sc.nextLine();

		if (confirmacao.equalsIgnoreCase("S")) {
			dao.deletarAnimal(id);
			System.out.println("Animal deletado com sucesso!");
		} else {
			System.out.println("Operação cancelada!");
		}
	}

//	tabela Animal
//	tabela Veterinario
	private static void inserirVeterinario() {
		dao = new BancoDAO();
		veterinario = retornarVeterinario();
		dao.inserirVeterinario(veterinario);
	}

	private static Veterinario retornarVeterinario() {
		System.out.println("Informe o CRMV do veterinario: ");
		String crmv = sc.nextLine();

		System.out.println("Informe o nome do veterinario: ");
		String nomeVeterinario = sc.nextLine();

		System.out.println("Informe a especialidade do veterinario: ");
		String especialidade = sc.nextLine();

		System.out.println("Informe o telefone do veterinario: ");
		String telefone = sc.nextLine();

		veterinario = new Veterinario(crmv, nomeVeterinario, especialidade, telefone);
		return veterinario;
	}

	private static void consultarVeterinario() {
		dao = new BancoDAO();

		List<Veterinario> lista = dao.consultarVeterinario();
		System.out.println("|  CRMV  |  Nome  |  Especialidade  |  Telefone  |");
		for (Veterinario v : lista) {
			System.out.println(v);
		}
	}

	private static void atualizarVeterinario() {
		dao = new BancoDAO();

		System.out.println("CRMV do veterinario que deseja atualizar: ");
		String crmv = sc.nextLine();

		System.out.println("Novo nome do veterinario: ");
		String nomeVeterinario = sc.nextLine();

		System.out.println("Nova especialidade do veterinario: ");
		String especialidade = sc.nextLine();

		System.out.println("Novo telefone do veterinario: ");
		String telefone = sc.nextLine();

		Veterinario v = new Veterinario(crmv, nomeVeterinario, especialidade, telefone);

		dao.atualizarVeterinario(v);
	}

	private static void deletarVeterinario() {
		dao = new BancoDAO();
		System.out.println("Digite o CRMV do veterinário que deseja deletar:");
		String crmv = sc.nextLine();

		System.out.print("Tem certeza que deseja deletar? (S/N): ");
		String confirmacao = sc.nextLine();

		if (confirmacao.equalsIgnoreCase("S")) {
			dao.deletarVeterinario(crmv);
			System.out.println("Veterinário deletado com sucesso!");
		} else {
			System.out.println("Operação cancelada!");
		}
	}

// tabela Veterinario
//	tabela Consulta
	private static void inserirConsulta() {
		dao = new BancoDAO();
		consulta = retornarConsulta();
		dao.inserirConsulta(consulta);
	}

	private static Consulta retornarConsulta() {
		int idAnimal = lerInt("Informe o id do animal: ");

		System.out.println("Informe o CRMV do veterinario: ");
		String crmv = sc.nextLine();

		LocalDate data_consulta = lerData("Informe a data da consulta: ");

		LocalTime hora_consulta = lerHora("Informe a hora da consulta: ");

		System.out.println("Informe o diagnostico: ");
		String diagnostico = sc.nextLine();

		double valor = lerDouble("Informe o valor da consulta: ");

		consulta = new Consulta(idAnimal, crmv, data_consulta, hora_consulta, diagnostico, valor);
		return consulta;
	}

	private static void consultarConsulta() {
		dao = new BancoDAO();

		List<Consulta> lista = dao.consultarConsulta();
		System.out.println("| ID Consulta |  ID animal  |  crmv  |  Data  | Hora | Diagnostico | Valor |");
		for (Consulta c : lista) {
			System.out.println(c);
		}
	}

	private static void atualizarConsulta() {
		dao = new BancoDAO();

		int idConsulta = lerInt("ID da consulta que deseja atualizar: ");

		int idAnimal = lerInt("Novo ID do animal: ");

		System.out.println("Novo CRMV do veterinario: ");
		String crmv = sc.nextLine();

		LocalDate data_consulta = lerData("Nova data da consulta: ");

		LocalTime hora_consulta = lerHora("Nova hora da consulta: ");

		System.out.println("Novo diagnostico: ");
		String diagnostico = sc.nextLine();

		double valor = lerDouble("Novo valor da consulta: ");
		sc.nextLine();

		Consulta c = new Consulta(idConsulta, idAnimal, crmv, data_consulta, hora_consulta, diagnostico, valor);

		dao.atualizarConsulta(c);
	}

	private static void deletarConsulta() {
		dao = new BancoDAO();
		int id = lerInt("Digite o ID da consulta que deseja deletar:");

		System.out.print("Tem certeza que deseja deletar? (S/N): ");
		String confirmacao = sc.nextLine();

		if (confirmacao.equalsIgnoreCase("S")) {
			dao.deletarConsulta(id);
			System.out.println("Consulta deletada com sucesso!");
		} else {
			System.out.println("Operação cancelada!");
		}
	}

//	tabela Consulta
	// Ler número inteiro com tratamento
	public static int lerInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				int num = Integer.parseInt(sc.nextLine());
				return num;
			} catch (Exception e) {
				System.out.println("❌ Entrada inválida! Digite um número inteiro.");
			}
		}
	}

	// Ler número (double) com tratamento
	public static double lerDouble(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				double num = Double.parseDouble(sc.nextLine());
				return num;
			} catch (Exception e) {
				System.out.println("❌ Entrada inválida! Digite um número válido (ex: 120.50)");
			}
		}
	}

	// Ler data com formato correto
	public static LocalDate lerData(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return LocalDate.parse(sc.nextLine(), FORMATO_DATA);
			} catch (Exception e) {
				System.out.println("❌ Formato inválido! Use (dd/MM/yyyy) — Ex: 25/12/2025");
			}
		}
	}

	// Ler hora com formato correto
	public static LocalTime lerHora(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return LocalTime.parse(sc.nextLine(), FORMATO_HORA);
			} catch (Exception e) {
				System.out.println("❌ Formato inválido! Use (HH:mm) — Ex: 14:30");
			}
		}
	}
}
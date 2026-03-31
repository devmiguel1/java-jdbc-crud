package com.meuapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.meuapp.connection.ConexaoBanco;
import com.meuapp.model.Animal;
import com.meuapp.model.Consulta;
import com.meuapp.model.ProprietarioAnimal;
import com.meuapp.model.Veterinario;

public class BancoDAO {

	private String sql;
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet rs;

//    private List<Pessoa> pessoas;

	private PreparedStatement prepararSentenca(String sql) {
		try {
			this.conn = ConexaoBanco.getConexao();
			statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("Sentença executada !!!");
			return statement;
		} catch (Exception e) {
			System.err.println("Problema na execução da sentença");
			throw new RuntimeException(e);
		}
	}

//	tabela ProprietarioAnimal
	public void inserirProprietario(ProprietarioAnimal proprietarioAnimal) {

		sql = "INSERT INTO proprietario_animal(cpf_proprietario, nome, telefone, endereco, email) VALUES (?,?,?,?,?)";

		try {
			statement = prepararSentenca(sql);
			statement.setString(1, proprietarioAnimal.getCpf());
			statement.setString(2, proprietarioAnimal.getNome());
			statement.setString(3, proprietarioAnimal.getTelefone());
			statement.setString(4, proprietarioAnimal.getEndereco());
			statement.setString(5, proprietarioAnimal.getEmail());
			statement.executeUpdate();
			System.out.println("Proprietario inserido !!!");
		} catch (Exception e) {
			System.err.println("Problema ao inserir o Proprietario");
			throw new RuntimeException(e);
		}
	}

	public List<ProprietarioAnimal> consultarTodosProprietarios() {

		sql = "SELECT * FROM proprietario_animal";
		List<ProprietarioAnimal> lista = new ArrayList<>();

		try {
			statement = prepararSentenca(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				ProprietarioAnimal p = new ProprietarioAnimal(rs.getString("cpf_proprietario"), rs.getString("nome"),
						rs.getString("telefone"), rs.getString("endereco"), rs.getString("email"));
				lista.add(p);
			}

			return lista;

		} catch (Exception e) {
			System.err.println("Erro ao consultar proprietários");
			throw new RuntimeException(e);
		}
	}

	public void atualizarProprietario(ProprietarioAnimal p) {

		sql = "UPDATE proprietario_animal " + "SET nome = ?, telefone = ?, endereco = ?, email = ? "
				+ "WHERE cpf_proprietario = ?";

		try {
			statement = prepararSentenca(sql);

			statement.setString(1, p.getNome());
			statement.setString(2, p.getTelefone());
			statement.setString(3, p.getEndereco());
			statement.setString(4, p.getEmail());
			statement.setString(5, p.getCpf());

			int linhas = statement.executeUpdate();

			if (linhas > 0) {
				System.out.println("Proprietário atualizado com sucesso!");
			} else {
				System.out.println("Nenhum proprietário encontrado com esse CPF.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar proprietario", e);
		}
	}

	public void deletarProprietario(String cpf) {
		sql = "DELETE FROM proprietario_animal WHERE cpf_proprietario = ?";

		try {
			statement = prepararSentenca(sql);
			statement.setString(1, cpf);

			int linhas = statement.executeUpdate();

			if (linhas > 0) {
				System.out.println("Proprietário deletado com sucesso!");
			} else {
				System.out.println("Nenhum proprietário encontrado com esse CPF.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar proprietário", e);
		} finally {
			fecharRecursos(); // Boa prática!
		}
	}

	public boolean existeProprietario(String cpf) {
		sql = "SELECT 1 FROM proprietario_animal WHERE cpf_proprietario = ?";
		try {
			statement = prepararSentenca(sql);
			statement.setString(1, cpf);
			rs = statement.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar proprietário", e);
		}
	}

//	tabela ProprietarioAnimal
//	tabela Animal
	public void inserirAnimal(Animal animal) {

		if (!existeProprietario(animal.getCpfProprietario())) {
			System.err.println("Proprietário não cadastrado! Cadastre primeiro.");
			return; // impede a inserção
		}

		sql = "INSERT INTO animal(cpf_proprietario, nome_animal, especie, raca, data_nascimento, peso) VALUES (?,?,?,?,?,?)";

		try {
			statement = prepararSentenca(sql);

			statement.setString(1, animal.getCpfProprietario());
			statement.setString(2, animal.getNomeAnimal());
			statement.setString(3, animal.getEspecie());
			statement.setString(4, animal.getRaca());
//			transfoma LocalDate em Date
			statement.setDate(5, java.sql.Date.valueOf(animal.getDataNascimento()));
			statement.setDouble(6, animal.getPeso());

			statement.executeUpdate();
			System.out.println("Animal inserido !!!");

		} catch (Exception e) {
			System.err.println("Problema ao inserir o Animal");
			throw new RuntimeException(e);
		}
	}

	public List<Animal> consultarAnimal() {

		sql = "SELECT * FROM animal";
		List<Animal> lista = new ArrayList<>();

		try {
			statement = prepararSentenca(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				Animal a = new Animal(rs.getInt("id_animal"), rs.getString("cpf_proprietario"),
						rs.getString("nome_animal"), rs.getString("especie"), rs.getString("raca"),
						rs.getDate("data_nascimento").toLocalDate(), rs.getDouble("peso"));
				lista.add(a);
			}

			return lista;

		} catch (Exception e) {
			System.err.println("Erro ao consultar animais");
			throw new RuntimeException(e);
		}
	}

	public void atualizarAnimal(Animal a) {

		sql = "UPDATE animal "
				+ "SET cpf_proprietario = ?, nome_animal = ?, especie = ?, raca = ?, data_nascimento = ?, peso = ? "
				+ "WHERE id_animal = ?";

		try {
			statement = prepararSentenca(sql);

			statement.setString(1, a.getCpfProprietario());
			statement.setString(2, a.getNomeAnimal());
			statement.setString(3, a.getEspecie());
			statement.setString(4, a.getRaca());
			statement.setDate(5, java.sql.Date.valueOf(a.getDataNascimento()));
			statement.setDouble(6, a.getPeso());
			statement.setInt(7, a.getIdAnimal());

			int linhas = statement.executeUpdate();

			if (linhas > 0) {
				System.out.println("Animal atualizado com sucesso!");
			} else {
				System.out.println("Nenhum Animal encontrado com esse ID.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar Animal", e);
		}
	}

	public void deletarAnimal(int id) {

		String sql = "DELETE FROM animal WHERE id_animal = ?";

		try (PreparedStatement stmt = prepararSentenca(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("✔️ Animal removido com sucesso!");
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar animal.", e);
		}
	}

	public boolean existeAnimal(int id) {
		sql = "SELECT 1 FROM animal WHERE id_animal = ?";
		try {
			statement = prepararSentenca(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar animal", e);
		}
	}

//	tabela Animal
//	tabela Veterinario
	public void inserirVeterinario(Veterinario veterinario) {

		sql = "INSERT INTO veterinario(crmv, nome_veterinario, especialidade, telefone) VALUES (?,?,?,?)";

		try {
			statement = prepararSentenca(sql);

			statement.setString(1, veterinario.getCrmv());
			statement.setString(2, veterinario.getNomeVeterinario());
			statement.setString(3, veterinario.getEspecialidade());
			statement.setString(4, veterinario.getTelefone());

			statement.executeUpdate();
			System.out.println("Veterinario inserido !!!");

		} catch (Exception e) {
			System.err.println("Problema ao inserir o Veterinario");
			throw new RuntimeException(e);
		}
	}

	public List<Veterinario> consultarVeterinario() {

		sql = "SELECT * FROM veterinario";
		List<Veterinario> lista = new ArrayList<>();

		try {
			statement = prepararSentenca(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				Veterinario v = new Veterinario(rs.getString("crmv"), rs.getString("nome_veterinario"),
						rs.getString("especialidade"), rs.getString("telefone"));
				lista.add(v);
			}

			return lista;

		} catch (Exception e) {
			System.err.println("Erro ao consultar Veterinarios");
			throw new RuntimeException(e);
		}
	}

	public void atualizarVeterinario(Veterinario v) {

		sql = "UPDATE veterinario " + "SET nome_veterinario = ?, especialidade = ?, telefone = ? " + "WHERE crmv = ?";

		try {
			statement = prepararSentenca(sql);

			statement.setString(1, v.getNomeVeterinario());
			statement.setString(2, v.getEspecialidade());
			statement.setString(3, v.getTelefone());
			statement.setString(4, v.getCrmv());

			int linhas = statement.executeUpdate();

			if (linhas > 0) {
				System.out.println("Veterinario atualizado com sucesso!");
			} else {
				System.out.println("Nenhum veterinario encontrado com esse CRMV.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar veterinario", e);
		}
	}

	public void deletarVeterinario(String crmv) {
		sql = "DELETE FROM veterinario WHERE crmv = ?";

		try {
			statement = prepararSentenca(sql);
			statement.setString(1, crmv);

			int linhas = statement.executeUpdate();
			if (linhas > 0) {
				System.out.println("Veterinário deletado com sucesso!");
			} else {
				System.out.println("Nenhum veterinário encontrado com esse CRMV.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar veterinário", e);
		} finally {
			fecharRecursos();
		}
	}

	public boolean existeVeterinario(String crmv) {
		sql = "SELECT 1 FROM veterinario WHERE crmv = ?";
		try {
			statement = prepararSentenca(sql);
			statement.setString(1, crmv);
			rs = statement.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar veterinário", e);
		}
	}
//	tabela Veterinario

//	tabela Consulta

	public void inserirConsulta(Consulta consulta) {

		if (!existeAnimal(consulta.getIdAnimal())) {
			System.err.println("❌ ERRO: O animal informado não existe! Cadastre o animal antes.");
		}

		if (!existeVeterinario(consulta.getCrmv())) {
			System.err.println("❌ ERRO: CRMV não encontrado! Cadastre o veterinário antes.");
			return;
		}

		sql = "INSERT INTO consulta(id_animal, crmv, data_consulta, hora_consulta, diagnostico, valor) VALUES (?,?,?,?,?,?)";

		try {
			statement = prepararSentenca(sql);

			statement.setInt(1, consulta.getIdAnimal());
			statement.setString(2, consulta.getCrmv());
			statement.setDate(3, java.sql.Date.valueOf(consulta.getData_consulta()));
			statement.setTime(4, java.sql.Time.valueOf(consulta.getHora_consulta()));
			statement.setString(5, consulta.getDiagnostico());
			statement.setDouble(6, consulta.getValor());

			statement.executeUpdate();
			System.out.println("Consulta inserida !!!");

		} catch (Exception e) {
			throw new RuntimeException("Problema ao inserir a consulta", e);
		}
	}

	public List<Consulta> consultarConsulta() {

		sql = "SELECT * FROM consulta";
		List<Consulta> lista = new ArrayList<>();

		try {
			statement = prepararSentenca(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				Consulta c = new Consulta(rs.getInt("id_consulta"), rs.getInt("id_animal"), rs.getString("crmv"),
						rs.getDate("data_consulta").toLocalDate(), rs.getTime("hora_consulta").toLocalTime(),
						rs.getString("diagnostico"), rs.getDouble("valor"));
				lista.add(c);
			}

			return lista;

		} catch (Exception e) {
			System.err.println("Erro ao consultar Consultas Medicas");
			throw new RuntimeException(e);
		}
	}

	public void atualizarConsulta(Consulta c) {

		sql = "UPDATE consulta "
				+ "SET id_animal = ?, crmv = ?, data_consulta = ?, hora_consulta = ?, diagnostico = ?, valor = ? "
				+ "WHERE id_consulta = ?";

		try {
			statement = prepararSentenca(sql);

			statement.setInt(1, c.getIdAnimal());
			statement.setString(2, c.getCrmv());
			statement.setDate(3, java.sql.Date.valueOf(c.getData_consulta()));
			statement.setTime(4, java.sql.Time.valueOf(c.getHora_consulta()));
			statement.setString(5, c.getDiagnostico());
			statement.setDouble(6, c.getValor());
			statement.setInt(7, c.getIdConsulta());

			int linhas = statement.executeUpdate();

			if (linhas > 0) {
				System.out.println("Consulta atualizada com sucesso!");
			} else {
				System.out.println("Nenhuma Consulta encontrada com esse ID.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar Consulta", e);
		}
	}

	public void deletarConsulta(int id) {
		sql = "DELETE FROM consulta WHERE id_consulta = ?";

		try {
			statement = prepararSentenca(sql);
			statement.setInt(1, id);

			int linhas = statement.executeUpdate();
			if (linhas > 0) {
				System.out.println("Consulta deletada com sucesso!");
			} else {
				System.out.println("Nenhuma consulta encontrada com esse ID.");
			}

		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar consulta", e);
		} finally {
			fecharRecursos();
		}
	}

	public boolean existeConsulta(int id) {
		sql = "SELECT 1 FROM consulta WHERE id_consulta = ?";
		try {
			statement = prepararSentenca(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar consulta", e);
		}
	}
//	tabela Consulta

// testar isso
	private void fecharRecursos() {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			System.err.println("Erro ao fechar recursos");
		}
	}

}
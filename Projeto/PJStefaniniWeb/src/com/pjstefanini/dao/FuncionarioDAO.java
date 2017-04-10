package com.pjstefanini.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.ChartSeries;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetImpl;
import com.pjstefanini.entity.Funcionario;
import com.pjstefanini.exception.SistemaException;
import com.pjstefanini.util.FabricaConexao;

public class FuncionarioDAO {

	private final static String insert = " INSERT INTO tbf_funcionario(tbf_nome, tbf_cpf, tbf_endereco, tbf_telefone, tbf_idcargo, tbf_idempresa)  VALUES( ?,?,?,?,?,?) ";
	private final static String update = " UPDATE tbf_funcionario SET tbf_nome = ?, tbf_cpf = ?, tbf_endereco = ?, tbf_telefone = ?, tbf_idcargo = ?, tbf_idempresa = ? WHERE tbf_id = ? ";
	private final static String select = " SELECT * FROM tbf_funcionario ";
	private final static String delete = " DELETE FROM tbf_funcionario WHERE tbf_id = ? ";
	private final static String funcionarioEmpresa = " SELECT tbe_nome_fantasia, count(tbf_id) qtd FROM tbe_empresa LEFT JOIN tbf_funcionario ON tbe_id = tbf_idempresa GROUP BY tbe_nome_fantasia ";

	private static Connection conexao;
	private static PreparedStatement ps;

	public FuncionarioDAO() {

	}

	public static void salvar(Funcionario f) throws SistemaException {

		System.out.println(" nome:" + f.getNome() + " CPF " + f.getCpf() + " endereco: " + f.getEndereco()
				+ " telefone:" + f.getTelefone() + " cargo:" + f.getIdCargo() + " empresa:" + f.getIdEmpresa());

		try {
			conexao = FabricaConexao.getConexao();

			if (f.getId() == null) {
				ps = (PreparedStatement) conexao.prepareStatement(insert);
			} else {
				ps = (PreparedStatement) conexao.prepareStatement(update);
				ps.setInt(7, f.getId());
			}

			ps.setString(1, f.getNome());
			ps.setLong(2, f.getCpf());
			ps.setString(3, f.getEndereco());
			ps.setLong(4, f.getTelefone());
			ps.setInt(5, f.getIdCargo());
			ps.setInt(6, f.getIdEmpresa());
			ps.execute();

			FabricaConexao.fechaConexao();

		} catch (SQLException e) {
			throw new SistemaException(" Não foi possivel Cadastrar um novo Funcionario. ", e);
		}
	}

	public static List<Funcionario> listar() throws SistemaException {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		conexao = FabricaConexao.getConexao();

		try {
			ps = (PreparedStatement) conexao.prepareStatement(select);
			ResultSetImpl rs = (ResultSetImpl) ps.executeQuery();

			while (rs.next()) {
				Funcionario f = new Funcionario();

				f.setId(rs.getInt("tbf_id"));
				f.setNome(rs.getString("tbf_nome"));
				f.setCpf(rs.getLong("tbf_cpf"));
				f.setEndereco(rs.getString("tbf_endereco"));
				f.setTelefone(rs.getLong("tbf_telefone"));
				f.setIdCargo(rs.getInt("tbf_idcargo"));
				f.setIdEmpresa(rs.getInt("tbf_idempresa"));

				funcionarios.add(f);

			}

			FabricaConexao.fechaConexao();
			return funcionarios;

		} catch (SQLException e) {
			throw new SistemaException(" Não Foi possivel listar os Funcionarios. ", e);
		}

	}

	public static ChartSeries listarFuncionarioEmpresa() throws SistemaException {

		ChartSeries funcionarios = new ChartSeries();
		funcionarios.setLabel("Funcionarios");
		
		conexao = FabricaConexao.getConexao();

		try {
			ps = (PreparedStatement) conexao.prepareStatement(funcionarioEmpresa);
			ResultSetImpl rs = (ResultSetImpl) ps.executeQuery();

			while (rs.next()) {
		        funcionarios.set(rs.getString("tbe_nome_fantasia"), rs.getInt("qtd"));
			}

			FabricaConexao.fechaConexao();
			return funcionarios;

		} catch (SQLException e) {
			throw new SistemaException(" Não Foi possivel listar os Funcionarios. ", e);
		}

	}

	public static void excluir(Funcionario f) throws SistemaException {
		conexao = FabricaConexao.getConexao();

		try {
			ps = (PreparedStatement) conexao.prepareStatement(delete);
			ps.setInt(1, f.getId());

			ps.execute();

			FabricaConexao.fechaConexao();

		} catch (SQLException e) {
			throw new SistemaException(" Não foi possivel excluir o Funcionario. ", e);
		}
	}
}

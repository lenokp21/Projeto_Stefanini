package com.pjstefanini.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.ChartSeries;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetImpl;
import com.pjstefanini.entity.Cargo;
import com.pjstefanini.exception.SistemaException;
import com.pjstefanini.util.FabricaConexao;

public class CargoDAO {

	private final static String insert = " INSERT INTO tbc_cargo(tbc_cargo, tbc_descricao, tbc_valorHora)  VALUES( ?,?,?) ";
	private final static String update = " UPDATE tbc_cargo SET tbc_cargo = ?, tbc_descricao = ?, tbc_valorHora = ? WHERE tbc_id = ? ";
	private final static String select = " SELECT * FROM tbc_cargo ";
	private final static String delete = " DELETE FROM tbc_cargo WHERE tbc_id = ? ";
	
	private final static String empresaCargos = " SELECT tbe_nome_fantasia, count(tbc_id) qtd FROM tbe_empresa, tbf_funcionario, tbc_cargo WHERE tbe_id = tbf_idempresa AND tbf_idcargo = tbc_id GROUP BY tbe_nome_fantasia ";

	private static Connection conexao;
	private static PreparedStatement ps;

	public static void salvar(Cargo cargo) throws SistemaException {
		conexao = FabricaConexao.getConexao();

		try {

			if (cargo.getId() == null) {
				ps = (PreparedStatement) conexao.prepareStatement(insert);
			} else {
				ps = (PreparedStatement) conexao.prepareStatement(update);
				ps.setInt(4, cargo.getId());
			}
			
			ps.setString(1, cargo.getCargo());
			ps.setString(2, cargo.getDescricao());
			ps.setDouble(3, cargo.getValorHora());
			
			ps.execute();
			
			FabricaConexao.fechaConexao();

		} catch (SQLException e) {
			throw new SistemaException(" Não foi possivel salvar o novo Cargo. ", e);
		}
	}

	public static List<Cargo> listar() throws SistemaException {
		List<Cargo> cargos = new ArrayList<Cargo>();
		conexao = FabricaConexao.getConexao();
		try {
			ps = (PreparedStatement) conexao.prepareStatement(select);
			ResultSetImpl rs = (ResultSetImpl) ps.executeQuery();
			
			while(rs.next()){
				Cargo c = new Cargo();
				c.setId(rs.getInt("tbc_id"));
				c.setCargo(rs.getString("tbc_cargo"));
				c.setDescricao(rs.getString("tbc_descricao"));
				c.setValorHora(rs.getDouble("tbc_valorHora"));
				
				cargos.add(c);
			}
			
			FabricaConexao.fechaConexao();
			return cargos;
			
		} catch (SQLException e) {
			throw new SistemaException(" Não foi Possivel listar os Cargos. ", e);
		}

	}
	
	public static ChartSeries listarEmpresaCargos() throws SistemaException {
		
		ChartSeries cargos = new ChartSeries();
		cargos.setLabel("Cargos");
		
		conexao = FabricaConexao.getConexao();
		try {
			ps = (PreparedStatement) conexao.prepareStatement(empresaCargos);
			ResultSetImpl rs = (ResultSetImpl) ps.executeQuery();
			
			while(rs.next()){
				cargos.set(rs.getString("tbe_nome_fantasia"), rs.getInt("qtd"));
			}
			
			FabricaConexao.fechaConexao();
			return cargos;
			
		} catch (SQLException e) {
			throw new SistemaException(" Não foi Possivel listar os Cargos. ", e);
		}

	}

	public static void excluir(Cargo cargo) throws SistemaException {
		
		conexao = FabricaConexao.getConexao();
		
		System.out.println(cargo.getId());
		
		try {
			ps = (PreparedStatement) conexao.prepareStatement(delete);
			ps.setInt(1, cargo.getId());
			
			ps.execute();
			
			FabricaConexao.fechaConexao();
			
		} catch (SQLException e) {
			throw new SistemaException(" Ocorreu algum problema ao excluir o cargo: "+ cargo.getCargo(), e);
		}

	}

}

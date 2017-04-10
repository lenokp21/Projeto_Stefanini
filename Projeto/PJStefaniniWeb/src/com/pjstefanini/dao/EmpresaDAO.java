package com.pjstefanini.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetImpl;
import com.pjstefanini.entity.Empresa;
import com.pjstefanini.exception.SistemaException;
import com.pjstefanini.util.FabricaConexao;

public class EmpresaDAO {

	private final static String insert = " INSERT INTO tbe_empresa( tbe_nome_fantasia, tbe_nome_empresarial, tbe_cnpj, tbe_endereco, tbe_telefone) VALUES( ?, ?, ?, ?, ?) ";
	private final static String update = " UPDATE tbe_empresa SET tbe_nome_fantasia = ?, tbe_nome_empresarial = ?, tbe_cnpj = ?, tbe_endereco = ?, tbe_telefone = ? WHERE tbe_id = ? ";
	private final static String select = " SELECT * FROM tbe_empresa ";
	private final static String delete = " DELETE FROM tbe_empresa WHERE tbe_id = ? ";

	private static Connection conexao;
	private static PreparedStatement ps;
	
	public static void salvar(Empresa empresa) throws SistemaException{
		conexao = FabricaConexao.getConexao();
		
		try {
			
			if(empresa.getId() == null){
				ps = (PreparedStatement) conexao.prepareStatement(insert);
			}else{
				ps = (PreparedStatement) conexao.prepareStatement(update);
				ps.setInt(6, empresa.getId());
			}
			
			ps.setString(1, empresa.getNomeFantasia());
			ps.setString(2, empresa.getNomeEmpresarial());
			ps.setLong(3, empresa.getCnpj());
			ps.setString(4, empresa.getEndereco());
			ps.setLong(5, empresa.getTelefone());
			
			ps.execute();
			
			FabricaConexao.fechaConexao();
			
		} catch (SQLException e) {
			throw new SistemaException(" Não Foi possivel cadastrar a empresa: "+empresa.getNomeFantasia(), e);
		}
		
	}
	
	public static List<Empresa> listar() throws SistemaException{
		
		List<Empresa> empresas = new ArrayList<Empresa>();
		conexao = FabricaConexao.getConexao();
		
		try {
			
			ps = (PreparedStatement) conexao.prepareStatement(select);
			ResultSetImpl rs = (ResultSetImpl) ps.executeQuery();
			
			while(rs.next()){
				Empresa e = new Empresa();
			e.setId(rs.getInt("tbe_id"));
			e.setNomeFantasia(rs.getString("tbe_nome_fantasia"));
			e.setNomeEmpresarial(rs.getString("tbe_nome_empresarial"));
			e.setCnpj(rs.getLong("tbe_cnpj"));
			e.setEndereco(rs.getString("tbe_endereco"));
			e.setTelefone(rs.getLong("tbe_telefone"));
			
			empresas.add(e);
				
			}
			
			FabricaConexao.fechaConexao();
			return empresas;
			
		} catch (SQLException e) {
			throw new SistemaException(" Ocorreu um problea com a Listagem de Empresas. ", e);
		}
	}
	
	public static void excluir(Empresa empresa){
		conexao = FabricaConexao.getConexao();
		
		try {
			ps = (PreparedStatement) conexao.prepareStatement(delete);
			ps.setInt(1, empresa.getId());
			
			ps.execute();
			
			FabricaConexao.fechaConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

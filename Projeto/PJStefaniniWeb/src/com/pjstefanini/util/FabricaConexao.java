package com.pjstefanini.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class FabricaConexao {

	private final static String URL = "jdbc:mysql://localhost:3306/db_stefanini";
	private final static String USER = "root";
	private final static String PASSWORD = "123456";
	
	private static Connection conexao;
	
	public static Connection getConexao(){
		
		if(conexao == null){
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Conexao estabelecida.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexao;
	}
	
	public static void fechaConexao(){
		if(conexao != null){
			try {
				conexao.close();
				System.out.println("Conexao fechada.");
				conexao = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

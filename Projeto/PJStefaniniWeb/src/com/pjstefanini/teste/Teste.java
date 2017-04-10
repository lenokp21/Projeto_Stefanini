package com.pjstefanini.teste;

import java.util.ArrayList;
import java.util.List;

import com.pjstefanini.dao.EmpresaDAO;
import com.pjstefanini.dao.FuncionarioDAO;
import com.pjstefanini.entity.Empresa;
import com.pjstefanini.entity.Funcionario;
import com.pjstefanini.exception.SistemaException;
import com.pjstefanini.util.FabricaConexao;

public class Teste {

	public static void main(String[] args) throws SistemaException {

		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = EmpresaDAO.listar();
		
		for(Empresa e: empresas){
			System.out.println(e.getNomeEmpresarial());
		}
		
		
	}

}

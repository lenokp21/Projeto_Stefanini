package com.pjstefanini.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pjstefanini.dao.CargoDAO;
import com.pjstefanini.dao.FuncionarioDAO;
import com.pjstefanini.entity.Cargo;
import com.pjstefanini.entity.Funcionario;
import com.pjstefanini.exception.SistemaException;

@ManagedBean(name="funcionarioBean")
@SessionScoped
public class FuncionarioBean {
	
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	
	public FuncionarioBean() throws SistemaException {
		funcionario = new Funcionario();
		funcionarios = new ArrayList<Funcionario>();
		
		this.listar();
	}
	
	public void salvar() throws SistemaException{
		
		if(funcionario.getNome().isEmpty()){
			throw new SistemaException(" PorFaver. Preencher os dados do Funcionario. ");
		}else{
			FuncionarioDAO.salvar(funcionario);
			this.novo();
			this.listar();
		}

	}
	
	public void editar(Funcionario funcionario) throws SistemaException{
		this.funcionario = funcionario;
		this.listar();
		
	}
	
	public void excluir(Funcionario funcionario) throws SistemaException{
		FuncionarioDAO.excluir(funcionario);
		this.listar();
	}
	
	public void novo(){
		funcionario = new Funcionario();
	}
	
	public void listar() throws SistemaException{
		funcionarios = FuncionarioDAO.listar();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	

	
	
	
	

}

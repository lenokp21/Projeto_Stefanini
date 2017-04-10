package com.pjstefanini.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pjstefanini.dao.EmpresaDAO;
import com.pjstefanini.entity.Empresa;
import com.pjstefanini.exception.SistemaException;

@ManagedBean
@SessionScoped
public class EmpresaBean {
	
	private Empresa empresa;
	private List<Empresa> empresas;
	
	public EmpresaBean() throws SistemaException {
		empresa = new Empresa();
		empresas = new ArrayList<Empresa>();
		
		this.listar();
	}
	
	public void salvar() throws SistemaException{
		EmpresaDAO.salvar(empresa);
		this.novo();
		this.listar();
	}
	
	public void listar() throws SistemaException{
		empresas = EmpresaDAO.listar();
	}
	
	public void excluir(Empresa empresa) throws SistemaException{
		EmpresaDAO.excluir(empresa);
		this.novo();
		this.listar();
	}
	
	public void editar(Empresa empresa) throws SistemaException{
		this.empresa = empresa;
		this.listar();
	}
	
	public void novo(){
		empresa = new Empresa();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	

}

package com.pjstefanini.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pjstefanini.dao.CargoDAO;
import com.pjstefanini.entity.Cargo;
import com.pjstefanini.exception.SistemaException;

@ManagedBean
@SessionScoped
public class CargoBean {

	private Cargo cargo;
	private List<Cargo> cargos;

	public CargoBean() throws SistemaException {
		cargo = new Cargo();
		cargos = new ArrayList<Cargo>();
		
		this.listar();
	}

	public void salvar() throws SistemaException {
		CargoDAO.salvar(cargo);
		this.novo();
		this.listar();
	}

	public void listar() throws SistemaException {
		cargos = CargoDAO.listar();
	}

	public void excluir(Cargo cargo) throws SistemaException {
		CargoDAO.excluir(cargo);
		this.listar();
	}
	
	public void editar(Cargo cargo){
		this.cargo = cargo;
	}
	
	public void novo(){
		cargo = new Cargo();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
	

}

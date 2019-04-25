package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.exception.ResponseException;
import br.com.fiap.repository.EmpresaRepository;
import br.com.fiap.to.Empresa;

@ManagedBean
public class EmpresaBean {

	private Empresa empresa;
	
	private EmpresaRepository rep;
	
	@PostConstruct
	public void init() {
		empresa = new Empresa();
		rep = new EmpresaRepository();
	}
	
	public List<Empresa> listar(){
		try {
			return rep.listar();
		} catch (ResponseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void cadastrar() {
		FacesMessage msg;
		try {
			rep.cadastrar(empresa);
			msg = new FacesMessage("Cadastrado!");
		} catch (ResponseException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro..");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}

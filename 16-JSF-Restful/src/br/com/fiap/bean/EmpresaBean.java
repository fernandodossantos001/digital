package br.com.fiap.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.exception.ResponseException;
import br.com.fiap.repository.EmpresaRepository;
import br.com.fiap.to.Empresa;

@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	
	private EmpresaRepository rep;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
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
		
		try {
			if(empresa.getCodigo()== 0 ) {
				rep.cadastrar(empresa);
				exibirMensagem("criado");
			}else {
				rep.atualizar(empresa);
				exibirMensagem("atualizado");
			}
			
			
		} catch (ResponseException e) {
			e.printStackTrace();
			exibirMensagem("erro");
		}
		
	}

	public void excluir(int codigo) {
		try {
			rep.remover(codigo);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
}

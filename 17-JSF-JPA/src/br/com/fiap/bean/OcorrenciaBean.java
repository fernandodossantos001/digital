package br.com.fiap.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.fiap.dao.OcorrenciaDAO;
import br.com.fiap.dao.impl.OcorrenciaDAOImpl;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;
import br.cpom.fiap.entity.Ocorrencia;

@ManagedBean
@ViewScoped
public class OcorrenciaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Ocorrencia ocorrencia;
	
	private OcorrenciaDAO ocorrenciaRepository ;
	
	
	@PostConstruct
	public void init() {
		ocorrencia = new Ocorrencia();
		ocorrencia.setData(Calendar.getInstance());
		ocorrenciaRepository = new OcorrenciaDAOImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());

	}
	
	public void cadastrar(){
		ocorrenciaRepository.cadastrar(ocorrencia);

		try {
			ocorrenciaRepository.commit();
			exibirMensagem("criado com sucesso");
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exibirMensagem("erro");
		}
	}
	
	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public List<Ocorrencia> listar(){
		return ocorrenciaRepository.listar();
	}
	
	
	
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	
	

}

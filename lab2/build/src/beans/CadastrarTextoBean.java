package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import excessoes.TextoException;


import sistema.Texto;
import sistema.Usuario;

@ManagedBean
@SessionScoped
public class CadastrarTextoBean {
	
	private Texto texto;
	private String conteudo;
	
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getTexto() {
		return texto.getConteudo();
	}

	public void setTexto(Texto texto) {
		this.texto = texto;
	}
	
	public String criaTexto() {
		try {
			this.texto = new Texto(conteudo);
			if (Usuario.getInstance().getTextos().contains(texto)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Falhou", "Texto Já Existe"));
			} else {
			cadastrarTexto();			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso", "Texto Salvo"));
			}
		}
		catch (TextoException e){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Falhou", e.getLocalizedMessage()));			
		}
		
		return "faces/inicial.xhtml";
	}
	
	private void cadastrarTexto() {		 	
		Usuario.getInstance().adicionaNovoTexto(texto);
		
	}
}

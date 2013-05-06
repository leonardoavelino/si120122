package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import excessoes.TextoException;

import sistema.Texto;
import sistema.Usuario;

@ManagedBean
@SessionScoped
public class TextosETipos {
	

	private List<Texto> textos;  

	private Texto textoSelecionado;
	private Texto textoModificado;

	

	public void TableBean() {  
		textos = new ArrayList<Texto>();
		populateTextos(textos);
	}  

	public Texto getTextoSelecionado() {  
		return textoSelecionado;  
	}  

	public void setTextoSelecionado(Texto texto) {  
		this.textoSelecionado = texto;  
	}  

	private void populateTextos(List<Texto> lista) {  
		lista.addAll(Usuario.getInstance().getTextos());  
	}  

	private String valueNumber;

	public String getValueNumber() {
		return valueNumber;
	}

	public void setValueNumber(String valueNumber) {
		this.valueNumber = valueNumber;
	}
	
	public String setRecombinacao() throws TextoException {
		if(valueNumber.equals("1")) Usuario.getInstance().tipoDeRecombinacao("aleatorio com repeticao");
		else if(valueNumber.equals("2")) Usuario.getInstance().tipoDeRecombinacao("aleatorio sem repeticao");
		else if(valueNumber.equals("3")) Usuario.getInstance().tipoDeRecombinacao("inversa");
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro", "Voce Deve Selecionar Um Tipo"));
			return "faces/textosETipo.xhtml";
		}
		if(textoSelecionado==null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro", "Voce Deve Selecionar Um Texto"));
			return "faces/textosETipo.xhtml";
		}
		this.textoModificado = new Texto(textoSelecionado.getConteudo());
		return "faces/recombinar.xhtml";
	}

	public List<Texto> textos(){
		return inverteTextos();
	}

	private List<Texto> inverteTextos(){
		List<Texto> retorno = new ArrayList<Texto>();
		List<Texto> temp = Usuario.getInstance().getTextos();
		for (int i = temp.size()-1; i >=0; i--) {
			retorno.add(temp.get(i));
		}
		return retorno;
	}
	
	public String dozePrimeirasPalavras(Texto texto) {
		String [] temp = texto.getConteudo().split(" ");
		String retorno = "";
		for (int i = 0; i < temp.length; i++) {
			if(i>11) break;
			retorno = retorno + temp[i] + " ";
		}
		return retorno;
	}
	
	

	public void adicionarNovaLinha() {
		setTextoModificado(Usuario.getInstance().recombinaTexto(textoModificado));
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Pronto", "Linha Adicionada Com Sucesso"));
	}

	public Texto getTextoModificado() {
		return textoModificado;
	}

	public void setTextoModificado(Texto textoModificado) {
		this.textoModificado = textoModificado;
	}
}

package sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Usuario {
	
	private static Usuario usuario;
	int contador = 0;

	
	private List<Texto> textos;
	private TipoDeCombinacao tipoDeCombinacao;
	
	private final String tipo1 = "aleatorio com repeticao";
	private final String tipo2 = "aleatorio sem repeticao";
	private final String tipo3 = "inversa";
	
	private Usuario() {
		this.criaUsuario();
	}
	
	private void criaUsuario() {
		textos = new ArrayList<Texto>();
		
	}
	
	public static Usuario getInstance() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public List<Texto> getTextos() {
		return textos;
	}
	
	public void adicionaNovoTexto(Texto texto) {
		if(!textos.contains(texto)) textos.add(texto);
	}
	
	public void tipoDeRecombinacao(String tipo) {
		if(tipo.equals(tipo1)) tipoDeCombinacao = TipoDeCombinacao.ALEATORIO_COM_REPETICAO;
		if(tipo.equals(tipo2)) tipoDeCombinacao = TipoDeCombinacao.ALEATORIO_SEM_REPETICAO;
		if(tipo.equals(tipo3)) tipoDeCombinacao = TipoDeCombinacao.INVERSA;
		
	}
	
	public String dezPalavras(Texto texto) {
		String [] temp = texto.getConteudo().split(" ");
		String retorno = "";
		for (int i = 0; i < temp.length; i++) {
			if(this.contador > temp.length-1) break;
			retorno = retorno + temp[this.contador] + " ";
			this.contador++;
			if(i==9){
				break;
			}
		}
		return retorno;
	}
	
	private String recombinaTipoZero(Texto temp, List<String> linhas, String[] uso) {
		String retorno = "";
		while (this.contador < uso.length) {
			linhas.add(dezPalavras(temp));
		}
		Random rnd = new Random();
		int numeroRandomico = rnd.nextInt(linhas.size());
		retorno = temp.getConteudo() + " " + linhas.get(numeroRandomico);
		retorno = retorno.replaceAll("  ", " ");
		return retorno;
	}
	
	private String recombinaTipoUm(Texto temp, List<String> linhas, String[] uso) {
		String retorno = "";
		while (this.contador < uso.length) {
			linhas.add(dezPalavras(temp));
		}
		Random rnd = new Random();
		int numeroRandomico = rnd.nextInt(linhas.size()-1);
		while (linhas.get(linhas.size()-1).equals(linhas.get(numeroRandomico))){
			numeroRandomico = rnd.nextInt(linhas.size()-1);
		}
		retorno = temp.getConteudo() + " " + linhas.get(numeroRandomico);
		return retorno;
	}
	
	private String recombinaTipoTres(Texto temp, List<String> linhas, String[] uso) {
		String retorno = "";
		while (this.contador < uso.length) {
			linhas.add(dezPalavras(temp));
		}
		Collections.reverse(linhas);
		for (int i = 0; i < linhas.size(); i++) {
			retorno = retorno + linhas.get(i);
		}
		return retorno;
	}
	
	
	public Texto recombinaTexto(Texto texto) {
		Texto temp = texto;
		List<String> linhas = new ArrayList<String>();
		String [] uso = temp.getConteudo().split(" ");
		if(tipoDeCombinacao.ordinal()==0){
			temp.setConteudo(recombinaTipoZero(temp, linhas, uso));
		}else if(tipoDeCombinacao.ordinal()==1){
			temp.setConteudo(recombinaTipoUm(temp, linhas, uso));
		}else if(tipoDeCombinacao.ordinal()==2){
			temp.setConteudo(recombinaTipoTres(temp, linhas, uso));
		}
		this.contador = 0;
		return temp;
	}
}

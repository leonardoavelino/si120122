package sistema;

public class Texto {
	
	private String conteudo;
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Texto(String texto) {
		this.conteudo = texto;
	}
	
	public String getConteudo () {
		return conteudo;
	}
	
}

package sistema;

import excessoes.TextoException;

public class Texto {
	
	private String conteudo;
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Texto(String texto) throws TextoException {
		if(texto == null || texto.equals("")) throw new TextoException("Texto Inválido");
		this.conteudo = texto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conteudo == null) ? 0 : conteudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Texto other = (Texto) obj;
		if (conteudo == null) {
			if (other.conteudo != null)
				return false;
		} else if (!conteudo.equals(other.conteudo))
			return false;
		return true;
	}

	public String getConteudo () {
		return conteudo;
	}
	
}

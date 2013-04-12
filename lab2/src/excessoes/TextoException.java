package excessoes;

public class TextoException extends Exception {
	private static final long serialVersionUID = 1L;

	public TextoException(String mensagem) {
		super(mensagem);
	}
}

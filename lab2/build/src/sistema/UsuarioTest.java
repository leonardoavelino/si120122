package sistema;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excessoes.TextoException;

public class UsuarioTest {
	private Usuario usuario;
	
	
	@Before
	public void criaUsuario(){
		usuario = Usuario.getInstance();
	}

	@Test
	public void testAddNovoTexto() throws TextoException {
		Texto texto1 = new Texto("qweasdzxcqweasdzxc");
		Texto texto2 = new Texto("qweasdzxcqweasdzxc");
		Texto texto3 = new Texto("weasdzxc");
		assertTrue(usuario.getTextos().size() == 0);
		usuario.adicionaNovoTexto(texto1);
		assertTrue(usuario.getTextos().size() == 1);
		usuario.adicionaNovoTexto(texto2);
		assertTrue(usuario.getTextos().size() == 1);
		usuario.adicionaNovoTexto(texto3);
		assertTrue(usuario.getTextos().size() == 2);
		usuario.encerraUsuario();
	}
	
	@Test
	public void testSetTipoDeCombinacao(){
		
		usuario.tipoDeRecombinacao("inversa");
		assertTrue(usuario.getTipoDeCombinacao().equals(TipoDeCombinacao.INVERSA));
		usuario.tipoDeRecombinacao("aleatorio com repeticao");
		assertTrue(usuario.getTipoDeCombinacao().equals(TipoDeCombinacao.ALEATORIO_COM_REPETICAO));
		usuario.tipoDeRecombinacao("aleatorio sem repeticao");
		assertTrue(usuario.getTipoDeCombinacao().equals(TipoDeCombinacao.ALEATORIO_SEM_REPETICAO));
		usuario.encerraUsuario();
		
	}
	
	
	@Test
	public void testDezPalavras() throws TextoException{
		Texto texto = new Texto("1 2 3 4 5 6 7 8 9 10 11 12 13 14");
		usuario.adicionaNovoTexto(texto);
		String dez = usuario.dezPalavras(texto);
		assertEquals(10, dez.split(" ").length);
		usuario.encerraUsuario();
	}
	
	@Test
	public void testTipoZero() throws TextoException{
		Texto texto = new Texto("01 02 03 04 05 06 07 08 09 10");
		usuario.adicionaNovoTexto(texto);
		usuario.tipoDeRecombinacao("aleatorio com repeticao");
		Texto comRepeticao = usuario.recombinaTexto(texto);
		assertEquals("01 02 03 04 05 06 07 08 09 10 01 02 03 04 05 06 07 08 09 10 ", comRepeticao.getConteudo());
		usuario.encerraUsuario();
	}
	
	@Test
	public void testTipoUm() throws TextoException{
		Texto texto = new Texto("01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20");
		usuario.adicionaNovoTexto(texto);
		usuario.tipoDeRecombinacao("aleatorio sem repeticao");
		Texto semRepeticao = usuario.recombinaTexto(texto);
		assertEquals("01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 01 02 03 04 05 06 07 08 09 10 ", semRepeticao.getConteudo());
		usuario.encerraUsuario();
	}
	
	@Test
	public void testTipoDois() throws TextoException{
		Texto texto = new Texto("01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21");
		usuario.adicionaNovoTexto(texto);
		usuario.tipoDeRecombinacao("inversa");
		Texto invertido = usuario.recombinaTexto(texto);
		assertEquals("21 11 12 13 14 15 16 17 18 19 20 01 02 03 04 05 06 07 08 09 10 ", invertido.getConteudo());
		usuario.encerraUsuario();
	}
	
}

package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Dados extends Thread {

	private Map<String, Integer> mapaDePalavras;
	private List<String> listaParaProcessar;
	private String localDaBusca;
	private int arquivosProcessados = 0;

	public Dados(List<String> arquivos) {
		this.mapaDePalavras = new HashMap<String, Integer>();
		this.listaParaProcessar = arquivos;
		adicionaPalavras(mapaDePalavras);

	}

	public Map<String, Integer> getMapaDePalavras() {
		return mapaDePalavras;
	}

	public List<String> getListaParaProcessar() {
		return listaParaProcessar;
	}

	public Integer getArquivosProcessados() {
		return arquivosProcessados;
	}

	public String getLocalDaBusca() {
		return localDaBusca;
	}

	public void setLocalDaBusca(String localDaBusca) {
		this.localDaBusca = localDaBusca;
	}

	@Override
	public void run() {
		Iterator<String> iterador = listaParaProcessar.iterator();
		while (iterador.hasNext()) {
			leArquivosJava(iterador.next());
			arquivosProcessados++;

		}
	}

	private void adicionaPalavras(Map<String, Integer> mapaDePalavras) {
		mapaDePalavras.put("abstract", 0);
		mapaDePalavras.put("throw", 0);
		mapaDePalavras.put("protected", 0);
		mapaDePalavras.put("implements", 0);
		mapaDePalavras.put("double", 0);
		mapaDePalavras.put("break", 0);
		mapaDePalavras.put("this", 0);
		mapaDePalavras.put("private", 0);
		mapaDePalavras.put("goto", 0);
		mapaDePalavras.put("do", 0);
		mapaDePalavras.put("boolean ", 0);
		mapaDePalavras.put("synchronized", 0);
		mapaDePalavras.put("package", 0);
		mapaDePalavras.put("if", 0);
		mapaDePalavras.put("default", 0);
		mapaDePalavras.put("assert", 0);
		mapaDePalavras.put("switch", 0);
		mapaDePalavras.put("new", 0);
		mapaDePalavras.put("for", 0);
		mapaDePalavras.put("continue", 0);
		mapaDePalavras.put("final", 0);
		mapaDePalavras.put("char", 0);
		mapaDePalavras.put("try", 0);
		mapaDePalavras.put("short", 0);
		mapaDePalavras.put("int", 0);
		mapaDePalavras.put("extends", 0);
		mapaDePalavras.put("catch", 0);
		mapaDePalavras.put("transient", 0);
		mapaDePalavras.put("return", 0);
		mapaDePalavras.put("instanceof", 0);
		mapaDePalavras.put("enum", 0);
		mapaDePalavras.put("case", 0);
		mapaDePalavras.put("throws", 0);
		mapaDePalavras.put("public", 0);
		mapaDePalavras.put("import", 0);
		mapaDePalavras.put("else", 0);
		mapaDePalavras.put("byte", 0);
		mapaDePalavras.put("while", 0);
		mapaDePalavras.put("super", 0);
		mapaDePalavras.put("native", 0);
		mapaDePalavras.put("float", 0);
		mapaDePalavras.put("transient", 0);
		mapaDePalavras.put("const", 0);
		mapaDePalavras.put("volatile", 0);
		mapaDePalavras.put("strictfp", 0);
		mapaDePalavras.put("long", 0);
		mapaDePalavras.put("finally", 0);
		mapaDePalavras.put("class", 0);
		mapaDePalavras.put("void", 0);
		mapaDePalavras.put("static", 0);
		mapaDePalavras.put("interface", 0);
	}

	private void varreArquivo(String arquivo) {
		BufferedReader arquivoEmBuffer;
		try {
			arquivoEmBuffer = new BufferedReader(new FileReader(arquivo));
			while (arquivoEmBuffer.ready()) {
				String input = arquivoEmBuffer.readLine();
				StringTokenizer quebraFrase = new StringTokenizer(input);

				while (quebraFrase.hasMoreTokens()) {
					String palavra = quebraFrase.nextToken().toLowerCase();
					if (mapaDePalavras.containsKey(palavra)) {
						int novaQuantidade = mapaDePalavras.get(palavra) + 1;
						mapaDePalavras.put(palavra, novaQuantidade);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void leArquivosJava(String arquivo) {
		varreArquivo(arquivo);
		incrementaArquivosProcessados();
	}

	public void incrementaArquivosProcessados() {
		arquivosProcessados += 1;
	}

}

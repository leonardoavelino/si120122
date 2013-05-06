package intergraf;

import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import classes.Dados;

public class JanelaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> mapaDePalavras;
	JTextField threads = new JTextField(5);
	JButton botao1 = new JButton("Abrir");
	JButton botao2 = new JButton("Analisar");
	JLabel labelArquivosAchados = new JLabel("Arquivos Achados: " + 0);
	JLabel labelArquivosProcessados = new JLabel("Arquivos Processados: " + 0);
	JLabel labelThreads = new JLabel("Digite a quantidade de Threads desejada");
	JLabel tempoGasto = new JLabel("Tempo Gasto: " + 0);

	String[] colunas = { "1", "2", "3", "4", "5" };

	private JList<String> listEstados;
	private JScrollPane scrollpane;
	Container panel = this.getContentPane();
	private String localDaBusca;
	private int numeroDeThreads;
	private List<String> listaArquivosAchados;
	private int quantidadeDeArquivosPorThread;
	private double tempoTotal;

	String[] linhas = { "abstract", "continue", "for", "new", "switch",
			"assert", "default", "if", "package", "synchronized", "boolean",
			"do", "goto", "private", "this", "break", "double", "implements",
			"protected", "throw", "byte", "else", "import", "public", "throws",
			"case", "enum", "instanceof", "return", "transient", "catch",
			"extends", "int", "short", "try", "char", "final", "interface",
			"static", "void", "class", "finally", "long", "strictfp",
			"volatile", "const", "float", "native", "super", "while" };

	String[] linhasENumeros = { "abstract" + " " + 0, "continue" + " " + 0,
			"for" + " " + 0, "new" + " " + 0, "switch" + " " + 0,
			"assert" + " " + 0, "default" + " " + 0, "if" + " " + 0,
			"package" + " " + 0, "synchronized" + " " + 0, "boolean" + " " + 0,
			"do" + " " + 0, "goto" + " " + 0, "private" + " " + 0,
			"this" + " " + 0, "break" + " " + 0, "double" + " " + 0,
			"implements" + " " + 0, "protected" + " " + 0, "throw" + " " + 0,
			"byte" + " " + 0, "else" + " " + 0, "import" + " " + 0,
			"public" + " " + 0, "throws" + " " + 0, "case" + " " + 0,
			"enum" + " " + 0, "instanceof" + " " + 0, "return" + " " + 0,
			"transient" + " " + 0, "catch" + " " + 0, "extends" + " " + 0,
			"int", "short" + " " + 0, "try" + " " + 0, "char" + " " + 0,
			"final" + " " + 0, "interface" + " " + 0, "static" + " " + 0,
			"void" + " " + 0, "class" + " " + 0, "finally" + " " + 0,
			"long" + " " + 0, "strictfp" + " " + 0, "volatile" + " " + 0,
			"const" + " " + 0, "float" + " " + 0, "native" + " " + 0,
			"super" + " " + 0, "while" + " " + 0 };

	public JanelaPrincipal() {
		super("JLIST");
		mapaDePalavras = new HashMap<String, Integer>();
		listaArquivosAchados = new ArrayList<String>();
		adicionaPalavras(mapaDePalavras);
		listEstados = new JList<String>(linhas);
		listEstados.setListData(linhasENumeros);
		scrollpane = new JScrollPane(listEstados);
		scrollpane.setSize(800, 500);
		MigLayout layout = new MigLayout();
		panel.setLayout(layout);
		panel.add(labelThreads, "wrap");
		panel.add(threads, "wrap");
		panel.add(botao1, "wrap");
		panel.add(botao2, "wrap");
		panel.add(labelArquivosAchados, "wrap");
		panel.add(labelArquivosProcessados, "wrap");
		panel.add(tempoGasto, "wrap");
		panel.add(scrollpane, "wrap");

		botao1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botao1ActionPerformed(evt);
			}
		});
		botao2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botao2ActionPerformed(evt);
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 420);
		this.setVisible(true);
	}

	public String getLocalDaBusca() {
		return localDaBusca;
	}

	private void atualizaLinhas() {
		for (int i = 0; i < linhasENumeros.length; i++) {
			linhasENumeros[i] = linhas[i] + " " + mapaDePalavras.get(linhas[i]);
		}
	}

	public void setLocalDaBusca(String localDaBusca) {
		this.localDaBusca = localDaBusca;
	}

	public void listContent(File curDir) {
		try {
			if (curDir.isDirectory()) {
				for (String s : curDir.list()) {
					String subpath = curDir.getPath() + File.separator + s;
					if (!subpath.endsWith(".java")) {
						listContent(new File(subpath));
					} else {
						listaArquivosAchados.add(subpath);
					}
				}
			} else {
				if (curDir.getPath().endsWith(".java"))
					listaArquivosAchados.add(curDir.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void botao1ActionPerformed(java.awt.event.ActionEvent evt) {
		javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
		chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
		int returnValor = chooser.showOpenDialog(this);
		if (returnValor == javax.swing.JFileChooser.APPROVE_OPTION) {
			setLocalDaBusca(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	private void botao2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (localDaBusca != null) {
			adicionaPalavras(mapaDePalavras);
			File curDir = new File(localDaBusca);
			listContent(curDir);
			int arquivosAchados = listaArquivosAchados.size();
			double tempoInicial = System.currentTimeMillis();
			try {
				Integer.parseInt(threads.getText());
				setNumeroDeThreads(Integer.parseInt(threads.getText()));
			} catch (Exception e) {
				setNumeroDeThreads(1);
			}
			if (numeroDeThreads > listaArquivosAchados.size()) {
				numeroDeThreads = listaArquivosAchados.size();
				quantidadeDeArquivosPorThread = 1;
			} else {
				quantidadeDeArquivosPorThread = listaArquivosAchados.size()
						/ numeroDeThreads;
			}
			Dados[] threads = new Dados[numeroDeThreads];

			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Dados(calculaNumeroDeArquivosPorThread());
				threads[i].start();
			}
			for (Dados dados : threads) {
				try {
					dados.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (Dados dados : threads) {
				for (String palavra : dados.getMapaDePalavras().keySet()) {
					mapaDePalavras.put(palavra, mapaDePalavras.get(palavra)
							+ dados.getMapaDePalavras().get(palavra));
				}
			}
			double tempoFinal = System.currentTimeMillis();
			tempoTotal = tempoFinal - tempoInicial;
			atualizaLinhas();
			listEstados.setListData(linhasENumeros);
			adicionaPalavras(mapaDePalavras);
			labelArquivosAchados
					.setText("Arquivos Achados: " + arquivosAchados);
			labelArquivosProcessados.setText("Arquivos Processados: "
					+ arquivosAchados);
			tempoGasto.setText("Tempo Gasto: " + tempoTotal);
		}
	}

	private void setNumeroDeThreads(int numero) {
		numeroDeThreads = numero;
	}

	private List<String> calculaNumeroDeArquivosPorThread() {
		List<String> temp = new ArrayList<String>();
		if (quantidadeDeArquivosPorThread > listaArquivosAchados.size()) {
			temp = listaArquivosAchados.subList(0, listaArquivosAchados.size());
		} else {
			temp = listaArquivosAchados.subList(0,
					quantidadeDeArquivosPorThread);
		}
		List<String> retorno = new ArrayList<String>(temp);
		listaArquivosAchados.removeAll(temp);
		return retorno;
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

}

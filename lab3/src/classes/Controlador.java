package classes;

import intergraf.JanelaPrincipal;

public class Controlador {
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JanelaPrincipal janela = new JanelaPrincipal();
				janela.setVisible(true);
			}
		});
	}

}

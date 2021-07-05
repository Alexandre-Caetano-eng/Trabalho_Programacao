package janela;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Banco_de_Dados.BancoDeDados;

public class Principal {

	private JFrame frame;
	public static BancoDeDados bd = new BancoDeDados();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bd.IniciaConexao();
					//Principal window = new Principal();
					Torneira window = new Torneira();
					window.frame.setVisible(true);
					Caixa window2 = new Caixa();
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

package janela;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Banco_de_Dados.Produto_Selecionar;
import Banco_de_Dados.Torneira_Selecionar;
import Banco_de_Dados.Usuario_Selecionar;
import regra_Negocio.Transacao;
import regra_Negocio.Verifica_CPF;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Torneira {
	public String cpf_entrada, cpf_text="Não verificado.";
	public double mls=0, valor=0, valorTotal=0, permitido=0;
	public JFrame frame;
	public double lastTime=0, now=0;
	String[][] info_tor = new String[200][4];
	public int torneira_id=1, torneira_prod=0;
	
	public Usuario_Selecionar US = new Usuario_Selecionar();
	public Torneira_Selecionar TS = new Torneira_Selecionar();
	public Produto_Selecionar PS = new Produto_Selecionar();
	public Verifica_CPF ve = new Verifica_CPF();
	regra_Negocio.Torneira tor = new regra_Negocio.Torneira();
	public Transacao Tr = new Transacao();
	
	public JLabel Verificado_text = new JLabel(cpf_text);
	public JLabel Saldo_text = new JLabel("");
	public DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	public DecimalFormat centecimalFormat = new DecimalFormat("#0,000");
	public JTextField CPF_entrada;
	public JLabel ML = new JLabel("");
	public JLabel ValorT = new JLabel("");
	public JButton btnEncher = new JButton("Encher Copo");
	public JButton btnPagar = new JButton("Descontar");
	public JLabel Valor_produto = new JLabel("");
	private final JButton btnCancelar = new JButton("Cancelar");
	private final JLabel lblNewLabel = new JLabel("Valor Chop");

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Torneira window = new Torneira();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Torneira() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//String entrada;
		
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 400);
		frame.getContentPane().setLayout(null);
		
		CPF_entrada = new JTextField();
		CPF_entrada.setText("");
		CPF_entrada.setBounds(58, 43, 106, 20);
		frame.getContentPane().add(CPF_entrada);
		CPF_entrada.setColumns(10);
		
		JLabel CPF_text = new JLabel("CPF");
		CPF_text.setBounds(22, 46, 26, 14);
		frame.getContentPane().add(CPF_text);
		
		torneira_prod=TS.verificarTorneiraProduto(torneira_id);
		valor=PS.valorProduto(torneira_prod);
		Valor_produto.setText("R$ "+decimalFormat.format(valor));
		JButton btnLogar = new JButton("Verificar");
		btnLogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					cpf_entrada=CPF_entrada.getText().trim();
					if(cpf_entrada.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(btnLogar, "CPF vazio.");
					}else {
						if(ve.verifica(cpf_entrada)==true) {
							if(US.verificaUsuarioExiste(cpf_entrada.trim())==true) {
								Verificado_text.setText("Verificado.");
								Verificado_text.setForeground(Color.GREEN);
								Saldo_text.setText("R$ "+decimalFormat.format(US.saldoUsuario(cpf_entrada.trim())));
								btnEncher.setEnabled(true);
								btnPagar.setEnabled(true);
								btnCancelar.setEnabled(true);
								ML.setText(String.valueOf(mls)+" L");
								ValorT.setText("R$ "+decimalFormat.format(valorTotal));
								permitido=Tr.encherCopo(cpf_entrada.trim(), String.valueOf(torneira_prod));
							}else {
								JOptionPane.showMessageDialog(btnLogar, "CPF não cadastrado.");
							}
						}else {
							JOptionPane.showMessageDialog(btnLogar, "Formato do CPF incorreto.");
						}
					}
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnLogar, "Utilize apenas números.");
				}
			}
		});
		btnLogar.setBounds(285, 42, 89, 23);
		frame.getContentPane().add(btnLogar);

		if(cpf_text.equalsIgnoreCase("Não verificado.")) {
			Verificado_text.setForeground(Color.RED);
		}
		Verificado_text.setBounds(174, 46, 101, 14);
		frame.getContentPane().add(Verificado_text);
		Saldo_text.setBounds(285, 76, 89, 14);
		frame.getContentPane().add(Saldo_text);
		btnEncher.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lastTime=System.nanoTime();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				now=System.nanoTime();
				if(((mls+50*((now-lastTime)/1000000000)))<=permitido*100) {
					mls+=50*((now-lastTime)/1000000000);
				}else {
					mls=permitido*100;
				}
				ML.setText(String.valueOf(centecimalFormat.format(mls))+" L");
				valorTotal=mls/100;
				ValorT.setText("R$ "+decimalFormat.format(valorTotal));
			}
		});
		
		btnEncher.setEnabled(false);
		btnEncher.setBounds(257, 155, 117, 23);
		frame.getContentPane().add(btnEncher);
				
		ML.setBounds(285, 193, 89, 14);
		frame.getContentPane().add(ML);
			
		ValorT.setBounds(285, 218, 89, 14);
		frame.getContentPane().add(ValorT);
		btnPagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				JOptionPane.showMessageDialog(btnPagar, Tr.cadastrarTransacao(cpf_entrada.trim(), String.valueOf(torneira_id), String.valueOf(mls/1000)));
				mls=0;
				valorTotal=0;
				Verificado_text.setText("Não verificado.");
				CPF_entrada.setText("");
				Verificado_text.setForeground(Color.RED);
				Saldo_text.setText("");
				ML.setText("");
				ValorT.setText("");
				btnEncher.setEnabled(false);
				btnPagar.setEnabled(false);
				Valor_produto.setText("");
				btnCancelar.setEnabled(false);
			}
		});
		
		btnPagar.setEnabled(false);
		btnPagar.setBounds(281, 288, 93, 23);
		frame.getContentPane().add(btnPagar);
		Valor_produto.setBounds(281, 116, 93, 14);
		
		frame.getContentPane().add(Valor_produto);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mls=0;
				valorTotal=0;
				cpf_text="Não verificado.";
				CPF_entrada.setText("");
				Verificado_text.setForeground(Color.RED);
				Saldo_text.setText("");
				ML.setText("");
				ValorT.setText("");
				btnEncher.setEnabled(false);
				btnPagar.setEnabled(false);
				Valor_produto.setText("");
				btnCancelar.setEnabled(false);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(281, 322, 93, 23);
		
		frame.getContentPane().add(btnCancelar);
		lblNewLabel.setBounds(281, 101, 93, 14);
		
		frame.getContentPane().add(lblNewLabel);
	}
}

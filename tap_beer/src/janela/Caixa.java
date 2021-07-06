package janela;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import Banco_de_Dados.Produto_Selecionar;
import Banco_de_Dados.Usuario_Selecionar;
import regra_Negocio.Produto;
import regra_Negocio.Transacao;
import regra_Negocio.Usuario_Cartao;
import regra_Negocio.Verifica_CPF;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Caixa {

	public JFrame frame;
	DefaultTableModel tabela = new DefaultTableModel();
	DefaultTableModel tabela2 = new DefaultTableModel();
	DefaultTableModel tabela3 = new DefaultTableModel();
	DefaultTableModel tabela4 = new DefaultTableModel();
	JTable table = new JTable(tabela);
	JTable table2 = new JTable(tabela2);
	JTable table3 = new JTable(tabela3);
	JTable table4 = new JTable(tabela4);
	
	public String[][] tor_itens=new String[200][4];
	public String[][] Produto_itens=new String[200][3];
	public String[][] Usu_itens=new String[200][3];
	public String[][] Trans_itens=new String[200][6];
	public int cont=0, cont2=0, cont3=0, cont4=0;
	public String nulo;
	
	public regra_Negocio.Torneira tor = new regra_Negocio.Torneira();
	public Usuario_Cartao Usc = new Usuario_Cartao();
	public Transacao Tr = new Transacao();
	public Produto Pr = new Produto();
	public Usuario_Selecionar US = new Usuario_Selecionar();
	public Produto_Selecionar PS = new Produto_Selecionar();
	public Verifica_CPF ve = new Verifica_CPF();
	
	private JTextField CPF_Saldo;
	private JTextField Saldo_Adic;
	private JTextField CPF_Excluir;
	private JTextField CPF_Cadastrar;
	private JTextField Nome_Cadastrar;
	private JTextField Quant_Prod;
	private JTextField ID_Prod_Tor;
	private JTextField ID_Torneira;
	private JTextField Nome_ProdP;
	private JTextField Valor_ProdP;
	private JTextField Local;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caixa window = new Caixa();
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
	public Caixa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] tor_nome= {"ID Torneira","ID Produto","Localização","Quantidade"};
		tor_itens[0][0]="null";
		tor_itens[0][1]="null1";
		tor_itens[0][2]="null2";
		tor_itens[0][3]="null3";
		for(int i=1;i<200;i++) {
			tor_itens[i][0]="";
		}
		String[] prod_nome= {"ID Produto","Nome Produto","Preço"};
		Produto_itens[0][0]="null";
		Produto_itens[0][1]="null1";
		Produto_itens[0][2]="null2";
		for(int i=1;i<200;i++) {
			Produto_itens[i][0]="";
		}
		String[] usu_nome= {"CPF","Nome Usuário","Saldo"};
		Usu_itens[0][0]="null";
		Usu_itens[0][1]="null1";
		Usu_itens[0][2]="null2";
		for(int i=1;i<200;i++) {
			Usu_itens[i][0]="";
		}
		String[] trans_nome= {"ID","CPF do Usuário","Nome Produto","Quantidade","Valor Pago","Data"};
		Trans_itens[0][0]="null";
		Trans_itens[0][1]="null1";
		Trans_itens[0][2]="null2";
		Trans_itens[0][3]="null3";
		Trans_itens[0][4]="null4";
		Trans_itens[0][5]="null5";
		for(int i=1;i<200;i++) {
			Trans_itens[i][0]="";
		}
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tbPainel = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tbPainel);
		tbPainel.setBounds(22, 11, 490, 500);
		
		JPanel ProdutoP = new JPanel();
		tbPainel.addTab("Produto", null, ProdutoP, null);
		ProdutoP.setLayout(null);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i=1;i<200;i++) {
					Produto_itens[i][0]="";
				}
				Produto_itens=PS.todosProduto();
				nulo=null;
				while(tabela2.getRowCount()>1) {
					for(int i=1; i<=cont2+5;i++) {
						try {
							tabela2.removeRow(i);
						}catch(Exception erro) {
							
						}
					}
				}
				cont2=0;
				do {
					if(!(Produto_itens[cont2][0]==nulo)) {
						cont2++;
					}else {
						break;
					}
				}while(true);
				for(int i=0; i<cont2;i++) {
					tabela2.addRow(Produto_itens[i]);
				}
			}
		});
		
		ProdutoP.add(table2);
		table2.setBounds(27, 31, 249, 194);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(27, 249, 68, 14);
		ProdutoP.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(27, 274, 46, 14);
		ProdutoP.add(lblNewLabel_2);
		
		Nome_ProdP = new JTextField();
		Nome_ProdP.setBounds(79, 246, 121, 20);
		ProdutoP.add(Nome_ProdP);
		Nome_ProdP.setColumns(10);
		
		Valor_ProdP = new JTextField();
		Valor_ProdP.setBounds(79, 271, 121, 20);
		ProdutoP.add(Valor_ProdP);
		Valor_ProdP.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Cadastrar");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JOptionPane.showMessageDialog(btnNewButton_4, Pr.cadastrarProduto(Nome_ProdP.getText().trim(), Valor_ProdP.getText().trim()));
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton_4, "Erro ao excluir usuário, tente novamente.");
				}
			}
		});
		btnNewButton_4.setBounds(277, 245, 152, 43);
		ProdutoP.add(btnNewButton_4);
		
		JLabel lblNewLabel_3 = new JLabel("Cadastrar Produto");
		lblNewLabel_3.setBounds(311, 219, 118, 14);
		ProdutoP.add(lblNewLabel_3);
		tabela2.addColumn("ID");
		tabela2.addColumn("Nome");
		tabela2.addColumn("Preço");
		tabela2.addRow(prod_nome);
		nulo=null;
		cont2=0;
		do {
			if(cont2<200) {
				if(!(Produto_itens[cont2][0]==nulo)) {
					if(!(Produto_itens[cont2][0].equals(""))) {
						cont2++;
					}else {
						break;
					}
				}else {
					break;
				}
			}else {
				break;
			}
		}while(true);
		for(int i=0; i<1;i++) {
			tabela2.addRow(Produto_itens[i]);
		}
		
		
		
		JPanel UsuarioP = new JPanel();
		tbPainel.addTab("Usuário", null, UsuarioP, null);
		UsuarioP.setLayout(null);
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i=1;i<200;i++) {
					Usu_itens[i][0]="";
				}
				Usu_itens=US.selecionaUsuarios();
				nulo=null;
				while(tabela3.getRowCount()>1) {
					for(int i=1; i<=cont3+5;i++) {
						try {
							tabela3.removeRow(i);
						}catch(Exception erro) {
							
						}
					}
				}
				cont3=0;
				do {
					if(cont3<200) {
						if(!(Usu_itens[cont3][0]==nulo)) {
							cont3++;
						}else {
							break;
						}
					}else {
						break;
					}
				}while(true);
				for(int i=0; i<cont3;i++) {
					tabela3.addRow(Usu_itens[i]);
				}
			}
		});
		
		UsuarioP.add(table3);
		table3.setBounds(27, 31, 300, 194);
		
		JLabel lblAdcSal = new JLabel("Adicionar Saldo");
		lblAdcSal.setBounds(27, 251, 124, 14);
		UsuarioP.add(lblAdcSal);
		
		JLabel lblCPF3 = new JLabel("CPF");
		lblCPF3.setBounds(27, 276, 46, 14);
		UsuarioP.add(lblCPF3);
		
		JLabel lblSaldo3 = new JLabel("Saldo");
		lblSaldo3.setBounds(27, 301, 46, 14);
		UsuarioP.add(lblSaldo3);
		
		CPF_Saldo = new JTextField();
		CPF_Saldo.setBounds(69, 273, 124, 20);
		UsuarioP.add(CPF_Saldo);
		CPF_Saldo.setColumns(10);
		
		Saldo_Adic = new JTextField();
		Saldo_Adic.setBounds(69, 298, 124, 20);
		UsuarioP.add(Saldo_Adic);
		Saldo_Adic.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar Saldo");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(ve.verifica(CPF_Saldo.getText().trim())==true) {
						if(US.verificaUsuarioExiste(CPF_Saldo.getText().trim())==true) {
							if(Double.parseDouble(Saldo_Adic.getText().trim())>0) {
								JOptionPane.showMessageDialog(btnNewButton, Usc.adicionarSaldo(CPF_Saldo.getText().trim(), Saldo_Adic.getText().trim()));
								CPF_Saldo.setText("");
								Saldo_Adic.setText("");
							}else {
								JOptionPane.showMessageDialog(btnNewButton, "Saldo a ser inserido não pode ser menor que zero.");
							}
						}else {
							JOptionPane.showMessageDialog(btnNewButton, "CPF não cadastrado.");
						}
					}else {
						JOptionPane.showMessageDialog(btnNewButton, "CPF inválido.");
					}
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton, "Erro ao inserir saldo, tente novamente.");
				}
			}
		});
		btnNewButton.setBounds(228, 272, 124, 43);
		UsuarioP.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Excluir Usu\u00E1rio");
		lblNewLabel.setBounds(27, 333, 95, 14);
		UsuarioP.add(lblNewLabel);
		
		JLabel lblCPF31 = new JLabel("CPF");
		lblCPF31.setBounds(27, 358, 46, 14);
		UsuarioP.add(lblCPF31);
		
		CPF_Excluir = new JTextField();
		CPF_Excluir.setBounds(69, 355, 124, 20);
		UsuarioP.add(CPF_Excluir);
		CPF_Excluir.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Excluir Usu\u00E1rio");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(ve.verifica(CPF_Excluir.getText().trim())==true) {
						if(US.verificaUsuarioExiste(CPF_Excluir.getText().trim())==true) {
							JOptionPane.showMessageDialog(btnNewButton_1, Usc.excluirUsuario(CPF_Excluir.getText().trim()));
							CPF_Excluir.setText("");
						}else {
							JOptionPane.showMessageDialog(btnNewButton_1, "CPF não cadastrado.");
						}
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1, "CPF inválido.");
					}
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton_1, "Erro ao excluir usuário, tente novamente.");
				}
			}
		});
		btnNewButton_1.setBounds(228, 354, 124, 23);
		UsuarioP.add(btnNewButton_1);
		
		JLabel lblCadUsu = new JLabel("Cadastrar Usu\u00E1rio");
		lblCadUsu.setBounds(27, 394, 124, 14);
		UsuarioP.add(lblCadUsu);
		
		JLabel lblCPF32 = new JLabel("CPF");
		lblCPF32.setBounds(27, 419, 46, 14);
		UsuarioP.add(lblCPF32);
		
		JLabel lblNomeUsu = new JLabel("Nome");
		lblNomeUsu.setBounds(27, 444, 46, 14);
		UsuarioP.add(lblNomeUsu);
		
		CPF_Cadastrar = new JTextField();
		CPF_Cadastrar.setBounds(69, 416, 124, 20);
		UsuarioP.add(CPF_Cadastrar);
		CPF_Cadastrar.setColumns(10);
		
		Nome_Cadastrar = new JTextField();
		Nome_Cadastrar.setBounds(69, 441, 124, 20);
		UsuarioP.add(Nome_Cadastrar);
		Nome_Cadastrar.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Cadastrar Usu\u00E1rio");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JOptionPane.showMessageDialog(btnNewButton_1, Usc.cadastrarUsuario(CPF_Cadastrar.getText().trim(), Nome_Cadastrar.getText().trim()));
					CPF_Cadastrar.setText("");
					Nome_Cadastrar.setText("");
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton_1, "Erro ao Cadastrar.");
				}
			}
		});
		btnNewButton_2.setBounds(228, 415, 124, 43);
		UsuarioP.add(btnNewButton_2);
		tabela3.addColumn("ID");
		tabela3.addColumn("Nome");
		tabela3.addColumn("Saldo");
		tabela3.addRow(usu_nome);
		nulo=null;
		cont3=0;
		do {
			if(cont3<200) {
				if(!(Usu_itens[cont3][0]==nulo)) {
					if(!(Usu_itens[cont3][0].equals(""))) {
						cont3++;
					}else {
						break;
					}
				}else {
					break;
				}
			}else {
				break;
			}
		}while(true);
		for(int i=0; i<1;i++) {
			tabela3.addRow(Usu_itens[i]);
		}
		
		
		JPanel TorneiraP = new JPanel();
		tbPainel.addTab("Torneira", null, TorneiraP, null);
		TorneiraP.setLayout(null);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i=1;i<200;i++) {
					tor_itens[i][0]="";
				}
				tor_itens=tor.consultarTorneiras("todas", "", "", "0");
				nulo=null;
				while(tabela.getRowCount()>1) {
					for(int i=1; i<=cont+5;i++) {
						try {
							tabela.removeRow(i);
						}catch(Exception erro) {
							
						}
					}
				}
				cont=0;
				do {
					if(cont<200) {
						if(!(tor_itens[cont][0]==nulo)) {
							cont++;
						}else {
							break;
						}
					}else {
						break;
					}
				}while(true);
				for(int i=0; i<cont;i++) {
					tabela.addRow(tor_itens[i]);
				}
			}
		});
		table.setBounds(27, 31, 382, 194);
		tabela.addColumn("ID");
		tabela.addColumn("Nome");
		tabela.addColumn("Valor");
		tabela.addColumn("Quantidade");
		tabela.addRow(tor_nome);
		nulo=null;
		cont=0;
		do {
			if(cont<200) {
				if(!(tor_itens[cont][0]==nulo)) {
					if(!(tor_itens[cont][0].equals(""))) {
						cont++;
					}else {
						break;
					}
				}else {
					break;
				}
			}else {
				break;
			}
		}while(true);
		for(int i=0; i<1;i++) {
			tabela.addRow(tor_itens[i]);
		}
		TorneiraP.add(table);
		
		JLabel lblidtor = new JLabel("ID Torneira");
		lblidtor.setBounds(27, 251, 67, 14);
		TorneiraP.add(lblidtor);
		
		JLabel lblidprod = new JLabel("ID Produto");
		lblidprod.setBounds(27, 274, 67, 14);
		TorneiraP.add(lblidprod);
		
		JLabel lblquantprod = new JLabel("Quantidade");
		lblquantprod.setBounds(27, 296, 67, 14);
		TorneiraP.add(lblquantprod);
		
		Quant_Prod = new JTextField();
		Quant_Prod.setColumns(10);
		Quant_Prod.setBounds(98, 294, 106, 20);
		TorneiraP.add(Quant_Prod);
		
		ID_Prod_Tor = new JTextField();
		ID_Prod_Tor.setColumns(10);
		ID_Prod_Tor.setBounds(97, 271, 106, 20);
		TorneiraP.add(ID_Prod_Tor);
		
		ID_Torneira = new JTextField();
		ID_Torneira.setColumns(10);
		ID_Torneira.setBounds(97, 248, 106, 20);
		TorneiraP.add(ID_Torneira);
		
		JButton btnNewButton_3 = new JButton("Adicionar");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JOptionPane.showMessageDialog(btnNewButton_3, tor.adicionarProduto(ID_Torneira.getText().trim(), ID_Prod_Tor.getText().trim(), Quant_Prod.getText().trim()));
					ID_Torneira.setText("");
					ID_Prod_Tor.setText("");
					Quant_Prod.setText("");
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton_3, "Erro ao inserir Produto.");
				}
			}
		});
		btnNewButton_3.setBounds(308, 254, 129, 64);
		TorneiraP.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Adicionar produto na torneira");
		lblNewLabel_4.setBounds(223, 236, 214, 14);
		TorneiraP.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cadastro torneira");
		lblNewLabel_5.setBounds(223, 343, 98, 14);
		TorneiraP.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Localiza\u00E7\u00E3o");
		lblNewLabel_6.setBounds(27, 378, 67, 14);
		TorneiraP.add(lblNewLabel_6);
		
		Local = new JTextField();
		Local.setBounds(98, 375, 106, 20);
		TorneiraP.add(Local);
		Local.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Cadastro");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JOptionPane.showMessageDialog(btnNewButton_5, tor.cadastrarTorneira(Local.getText()));
					Local.setText("");
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(btnNewButton_5, "Erro ao inserir nova Torneira.");
				}
			}
		});
		btnNewButton_5.setBounds(308, 374, 89, 23);
		TorneiraP.add(btnNewButton_5);
		
		JPanel TransacaoP = new JPanel();
		tbPainel.addTab("Transação", null, TransacaoP, null);
		table4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i=0;i<200;i++) {
					Trans_itens[i][0]="";
				}
				Trans_itens=Tr.consultarTransacao("", "");
				nulo=null;
				while(tabela4.getRowCount()>1) {
					for(int i=1; i<=cont4+5;i++) {
						try {
							tabela4.removeRow(i);
						}catch(Exception erro) {
							
						}
					}
				}
				cont4=0;
				do {
					if(cont4<200) {
						if(!(Trans_itens[cont4][0]==nulo)) {
							cont4++;
						}else {
							break;
						}
					}else {
						break;
					}
				}while(true);
				for(int i=0; i<cont4;i++) {
					tabela4.addRow(Trans_itens[i]);
				}
			}
		});
		
		table4.setBounds(27, 31, 440, 400);
		tabela4.addColumn("ID");
		tabela4.addColumn("CPF Usuário");
		tabela4.addColumn("Nome Produto");
		tabela4.addColumn("Quantidade");
		tabela4.addColumn("Valor Pago");
		tabela4.addColumn("Data");
		tabela4.addRow(trans_nome);
		nulo=null;
		cont4=0;
		do {
			if(cont4<200) {
				if(!(tor_itens[cont4][0]==nulo)) {
					if(!(tor_itens[cont4][0].equals(""))) {
						cont4++;
					}else {
						break;
					}
				}else {
					break;
				}
			}else {
				break;
			}
		}while(true);
		for(int i=0; i<1;i++) {
			tabela4.addRow(Trans_itens[i]);
		}
		TransacaoP.setLayout(null);
		TransacaoP.add(table4);
	}
}

package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacao_Cadastrar {
	
	public boolean cadastrarTransacao(String cpf, String nome_Produto, double quant_Produto, double valor_P) {
		//cadastra uma torneira
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `transacao`(`cpf_Usuario`, `nome_Produto`, `quant_Prod`, `valor_Pago`) VALUES (?,?,?,?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,cpf);
				comando.setString(2,nome_Produto);
				comando.setDouble(3,quant_Produto);
				comando.setDouble(4,valor_P);
				if(comando.executeUpdate()>0) {
					retorno = true;
				}
			}
		}catch(SQLException e) {
			return false;
		}finally{
			try {
				comando.close();
			}catch(SQLException es) {
				return false;
			}
		}
		return retorno;
	}
	
	public Transacao_Cadastrar() {
		
	}
}

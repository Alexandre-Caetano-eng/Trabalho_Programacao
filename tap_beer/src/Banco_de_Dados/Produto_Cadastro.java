package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Produto_Cadastro {
	
	public boolean cadastroProduto(String nome, double valor) {
		//cadastra um usuario
		boolean retorno=false;
		PreparedStatement comando = null;
		ResultSet resultado = null;		
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `produto`(`nome`, `valor`) VALUES (?,?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,nome);
				comando.setDouble(2,valor);
				if(comando.executeUpdate()>0) {
					resultado = comando.getGeneratedKeys();
					if(resultado.next()) {
						retorno = true;
					}
				}
			}
		}catch(SQLException e) {
			return false;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException es) {
				return false;
			}
		}
		return retorno;
	}
	
	public Produto_Cadastro() {
		
	}
}

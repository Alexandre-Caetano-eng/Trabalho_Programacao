package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario_Cadastrar {
	
	public boolean cadastrarUsuario(String cpf, String nome) {
		//cadastra um usuario
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `usuario_cartao`(`cpf`,`nome`) VALUES (?,?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,cpf);
				comando.setString(2,nome);
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
	
	public Usuario_Cadastrar() {
		
	}
}

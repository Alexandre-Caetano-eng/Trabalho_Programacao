package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario_Excluir {
	
	public boolean excluirUsuario(String cpf) {
		boolean retorno=false;
		PreparedStatement comando = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="DELETE FROM `usuario_cartao` WHERE cpf=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setString(1, cpf);
				if(comando.executeUpdate()>0) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public Usuario_Excluir() {
		
	}
}

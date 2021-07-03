package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Torneira_Alterar {
	public boolean adicionarProdutoTorneira(int id_Torneira, int id_Produto, double quant) {
		//cadastra um produto em uma determinada torneira
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `torneira` SET `id_Produto`=? , `quant_Produto`=? WHERE `id_Torneira`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setInt(1,id_Produto);
				comando.setDouble(2,quant);
				comando.setInt(3,id_Torneira);
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
}

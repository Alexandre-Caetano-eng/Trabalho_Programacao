package regra_Negocio;

public class Verifica_CPF {

	
	public boolean verifica(String CPF) {
		String[] array=CPF.split("");
		int[] num=new int[11];
		int soma=0, numero;
		try{
			for(int i=0;i<11;i++) {
				num[i]=Integer.valueOf(array[i]);
			}
			for(int i=0;i<9;i++) {
				soma+=num[i]*(i+1);
			}
			numero=soma%11;
			numero=numero%10;
			if(numero!=num[9]) {
				return false;
			}
			soma=0;
			for(int i=1;i<10;i++) {
				soma+=num[i]*(i);
			}
			numero=soma%11;
			numero=numero%10;
			if(numero!=num[10]) {
				return false;
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Verifica_CPF() {

	}
}

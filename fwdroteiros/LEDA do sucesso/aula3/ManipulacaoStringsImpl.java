
public class ManipulacaoStringsImpl implements ManipulacaoStrings{
//C:/Users/Dinho/Desktop/LEDA/Aula 3/dados-alunos.txt
	
	@Override
	public int indexOf(int start, String original, String s) {
		int indiceS = 0;
		int indiceOriginal;
		
		if (s.length() > original.length() || start > (s.length()-1)){
			return -1;
		}
		
		char[] arrayOriginal = original.toCharArray();
		char[] arrayS = s.toCharArray();
		
		for(int i = start; i < original.length(); i ++){
			if(arrayOriginal[i] == arrayS[indiceS]){
				
				indiceOriginal = i;
				//for(int j = )
			}
		}
		return 2;
	}

	@Override
	public int firstIndexOf(String original, String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int occurrences(String original, String s) {
		// TODO Auto-generated method stub
		return 0;
	}

}

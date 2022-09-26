public interface ManipulacaoStrings {
	
	/**
	 * Retorna o primeiro indice da string s dentro da string original, 
	 * a partir da posicao start. Caso a string s nao ocorra na string 
	 * original a partir da posicao start, o método retorna -1;
	 * @param start
	 * @param original
	 * @param s
	 * @return o primeiro indice de s na string original ou -1, caso ela nao ocorra. 
	 */
	public abstract int indexOf(int start, String original, String s);
	
	/**
	 * Retorna -1 se a string s nao pertence a string original, ou o indice (posicao) 
	 * do primeiro caractere da string s na string original. Dica: tente usar 
	 * o metodo indexOf para definir firstIndexOf.
	 * @param original
	 * @param s
	 * @return o primeiro indice da string s em original ou -1, caso ela nao ocorra.
	 */
	public abstract int firstIndexOf(String original, String s);

	/**
	 * Retorna a quantidade de ocorrencias da string s na string original. Dica: 
	 * tente usar os métodos indexOf e firstIndexOf anteriores 
	 * para construir o método occurrences.
	 * @param original
	 * @param s
	 * @return a quantidade de ocorrencias da string s na string original.
	 */
	public abstract int occurrences(String original, String s);
	
	 
}

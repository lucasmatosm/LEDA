package leda;

public interface NoIF {

	int valor();

	NoIF esquerda();

	NoIF direita();
	
	void setEsquerda(NoIF node);
	
	void setDireita(NoIF node);

}

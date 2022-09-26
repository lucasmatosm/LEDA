package leda;

public class No implements NoIF {
	private int valor;
	private NoIF esquerda;
	private NoIF direita;
	
	public No(int valor) {
		this.valor = valor;
	}

	public int valor() {
		return valor;
	}

	public NoIF esquerda() {
		return esquerda;
	}

	public NoIF direita() {
		return direita;
	}
	
	public void setEsquerda(NoIF node) {
		esquerda = node;
	}

	public void setDireita(NoIF node) {
		direita = node;
	}

	@Override
	public String toString() {
		return "No [valor=" + valor + ", esquerda=" + esquerda.valor() + ", direita="
				+ direita.valor() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direita == null) ? 0 : direita.hashCode());
		result = prime * result
				+ ((esquerda == null) ? 0 : esquerda.hashCode());
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		No other = (No) obj;
		if (direita == null) {
			if (other.direita != null)
				return false;
		} else if (!direita.equals(other.direita))
			return false;
		if (esquerda == null) {
			if (other.esquerda != null)
				return false;
		} else if (!esquerda.equals(other.esquerda))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

}

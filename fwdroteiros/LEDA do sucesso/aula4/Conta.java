

public class Conta {
	private String numero;
	private double saldo;
	
	public Conta(String numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	public void creditar(double valor){
		this.saldo = saldo + valor;
	}
	public void debitar(double valor){
		this.saldo = saldo - valor;
	}
	public void transferir(Conta destino, double valor){
		this.debitar(valor);	
		destino.creditar(valor);
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNumero() {
		return numero;
	}
	
}

package ContaSimples;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Conta {
	private double Saldo = 0.0;
	private String Nome;
	private String CPF;
	private int Numero;
	private int QuantidadeExtrato = 20;
	private String[] arrayExtrato = new String[QuantidadeExtrato];

    public Conta(String nome, String cpf, int numero){
		this.Nome = nome;
		this.CPF = cpf;
		this.Numero = numero;
	}
	
	public void Depositar(double valor) throws Exception{
		if (valor < 0){
			throw new Exception("Valor do deposito tem que ser maior que zero.");
		}
		this.Saldo += valor;
		for(int i = 0; i <= arrayExtrato.length; i++){
			if (this.arrayExtrato[i] == null){
				this.arrayExtrato[i] = "Deposito R$: " + valor; 
				break;
			}
		}
	}
	
	public void Transferir(double valor, Conta objeto) throws Exception{
		if (valor < 0){
			throw new Exception("Valor da tranferencia tem que ser maior que zero.");
		}
		objeto.Depositar(valor);
		for(int i = 0; i <= arrayExtrato.length; i++){
			if (this.arrayExtrato[i] == null){
				this.arrayExtrato[i] = "Transferência R$: " + valor; 
				break;
			}
		}
	}
	
	public void Sacar(double valor) throws Exception{
		if (valor > this.Saldo){
			throw new Exception("Saldo insuficiente.");
		}
		this.Saldo -= valor;
		for(int i = 0; i <= arrayExtrato.length; i++){
			if (this.arrayExtrato[i] == null){
				this.arrayExtrato[i] = "Saque R$: " + valor;
				break;
			}
		}				
	}
	
	public double getSaldo(){
		return this.Saldo;
	}
	
	public void getExtrato(){
		Locale locale = new Locale("pt","BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd'/'MM'/'yyyy' - 'HH':'mm'h': ", locale);
		System.out.println("Extrato:");
		
		for(int i = 0; i < arrayExtrato.length; i++){
			if (arrayExtrato[i] != null){
				System.out.println(formatador.format(calendar.getTime()) + arrayExtrato[i]);
			}
		}
	}
	
	public String toString(){
		String Saida = "Titular: " + this.Nome + "\n" + "CPF: " + this.CPF + "\n" + "Numero da Conta: " + this.Numero + "\n" + "Saldo: " + "R$" + this.Saldo;
		return Saida;
	}

public static void main(String[] args) throws Exception{
	Conta UmaConta = new Conta("Julio Andherson", "083.877.064-93", 21021093);
	Conta OutraConta = new Conta("Daniel Carlos", "023.321.999.76", 21021051);
	
	System.out.println(UmaConta.getSaldo());
	UmaConta.Depositar(100);
	System.out.println(UmaConta.getSaldo());
	UmaConta.Depositar(1000);
	UmaConta.Sacar(240);
	System.out.println(UmaConta.getSaldo());
	System.out.println(UmaConta.toString());
	UmaConta.getExtrato();
	
	System.out.println(OutraConta.getSaldo());
	UmaConta.Transferir(100, OutraConta);
	System.out.println(OutraConta.getSaldo());
	UmaConta.getExtrato();
	
	}
}
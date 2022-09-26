import java.io.File;
import java.util.Scanner;


public class ScannerExample {
	
	/**
	 * O metodo contem o codigo necessario para uso da classe Scanner para ler dados do teclado.
	 * Note que os dados sao lidos como String. Entretanto, eles podem ser lidos como inte, double, float, etc.
	 * Consulte a documentacao da classe Scanner para mais detalhes. 
	 */
	public static void lerDoTeclado(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite um texto ou então SAIR para finalizar");
		String linhaAtual = "";
		do{
			linhaAtual = scanner.nextLine();
			System.out.println("Linha lida: " + linhaAtual);
			System.out.println("\n");
			
		}while(!linhaAtual.equals("SAIR"));
		System.out.println("Finalisando o programa");
	}
	
	/**
	 * Uso da classe Scanner para ler as linhas de um arquivo de texto.
	 * @param caminhoDoArquivo
	 * @throws Exception
	 */
	public static void lerDoArquivoTexto(String caminhoDoArquivo) throws Exception{
		Scanner scanner = new Scanner(new File(caminhoDoArquivo));
		System.out.println("Lendo dados do arquivo: " + caminhoDoArquivo);
		String linhaAtual = "";
		while(scanner.hasNext()){
			linhaAtual=scanner.nextLine();
			System.out.println(linhaAtual);
		}
		System.out.println("Finalisando o programa");
	}
	
	/**
	 * Exemplo de uso da classe Scanner para separar os valores vindos em uma so string.
	 * Por exemplo, suponha que a string tem formato "dd/mm/aaaa" e queremos imprimir 
	 * isso na forma: "Data: dd de mm de aaaa".
	 */
	public static void formatarLinha(String linha){
		Scanner formatador = new Scanner(linha);
		formatador.useDelimiter("/");
		String dia = formatador.next();
		String mes = formatador.next();
		String ano = formatador.next();
		
		System.out.println("Data: " + dia + " de " + mes + " de " + ano);
	}
	
	
	public static void main(String[] args) throws Exception{
		//ScannerExample.lerDoArquivoTexto("E:\\UAG\\2010.1\\disciplinas\\POO\\alunos.txt");
		ScannerExample.formatarLinha("14/02/2011");
	}
}

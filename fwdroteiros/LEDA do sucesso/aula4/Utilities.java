


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * Esta classe mostra um pouco do uso de reflections em Java e como informacoes 
 * sobre um próprio comonente podem ser pegas dinamicamente. As instancias da classe java.lang.Class<T> 
 * representam qualquer classe e interface rodando em um aplicativo Java. Por exemplo a 
 * class String.class é modelada como sendo Class<String>. A classe Class nao possui construtor publico. 
 * Instancias dessa classe sao criadas automaticamente pela JVM. Assim, para um objeto x (do tipo X) instanciado, 
 * pode-se invocar o método  x.getClass().getName() ou entao X.class.getName() para obter o nome to tipo, no caso X.
 */
public class Utilities {
	/**
	 * Mostra os atributos (campos) publicos de uma classe a qual obj pertence. 
	 */
	public static void showFields(Object obj){
		Class classe = obj.getClass();
		Field[] fields = classe.getFields();
		for (int i = 0; i < fields.length; i++) {
			//mostra os dados de cada campo
			System.out.println(fields[i].toGenericString());
		}
	}
	
	/**
	 * Mostra os atributos (campos) declarados na classe a qual obj Pertence. 
	 */
	public static void showDeclaredFields(Object obj){
		Class classe = obj.getClass();
		Field[] fields = classe.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			//mostra os dados de cada campo
			System.out.println(fields[i].toGenericString());
		}
	}	

	/**
	 * Mostra os metodos publicos definidos na classe a qual obj pertence, inclusive os herdados. 
	 */
	public static void showMethods(Object obj){
		Class classe = obj.getClass();
		Method[] methods = classe.getMethods();
		for (int i = 0; i < methods.length; i++) {
			//mostra os dados de cada campo
			Method m = methods[i];
			System.out.println("Metodo: " + m.getName());
			System.out.println("Tipo de retorno: " + m.getReturnType());
			System.out.println("Tipos dos parametros: ");
			Class[] tiposParametros = m.getParameterTypes();
			//TypeVariable<Method>[] = m.getTypeParameters();
			for (int j = 0; j < tiposParametros.length; j++) {
				System.out.println("   " + tiposParametros[j]);
				
			}
			System.out.println("Descricao generica: " + m.toGenericString());
			System.out.println("");
		}
	}
	
	/**
	 * Mostra informacoes sobre os construtores publicos de uma determinada classe. 
	 */
	public static void showConstructors(Object obj){
		Class classe = obj.getClass();
		Constructor[] construtores = classe.getConstructors();
		for (int i = 0; i < construtores.length; i++) {
			System.out.println(construtores[i].toGenericString());
		}
	}
	/**
	 * Metodo que permite instanciar um objeto de uma classe dado o seu nome.
	 * O nome tem que ser o full qualified name (incluindo o pacote) e o aquivo
	 * da classe deve estar acessivel no classpath. 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 */
	public static Object instantiate(String className) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class classe = Class.forName(className);
		//Constructor construtor = classe.getConstructors();
		//suponha que voce conhece o formato do construtor de sua classe
		//a classe Conta, por exemplo tem o construtor Conta(String,double).
		//vamos invoca-lo.
		//primeiro instancie um array de Class contendo as as classes representando os tipos dos parametros em ordem
		Class[] tiposParametros = {String.class,double.class};
		
		//depois obtenha o construtor especifico da classe
		Constructor c = classe.getConstructor(tiposParametros);
		
		//agora instancie um array de objetos com os valores para apssar ao construtor. Valores de 
		//tipos primitivos sao representados pelo respectivo wrapper. exemplo: int por Integer, etc.
		Object[] valoresParametros = {"222-2",new Double(700.0)};
		
		
		//agora execute o construtor e obtenha uma instancia e retorne ela
		Object instancia = c.newInstance(valoresParametros);
		
		return instancia;
	}
	
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Conta c = new Conta("111-1", 200.00);
		//Utilities.showFields(c);
		//Utilities.showDeclaredFields(c);
		//Utilities.showMethods(c);
		//Utilities.showConstructors(c);
		Conta c1 = (Conta) Utilities.instantiate("reflections.Conta");
		//note que o objeto c1 foi criado sem precisar usar new.
		System.out.println("Numero da conta: " + c1.getNumero());
		System.out.println("Saldo da conta: " + c1.getSaldo());
		//Conta c3 = reflections.Conta.class.newInstance();
		//System.out.println("Numero da conta: " + c3.getNumero());
		//System.out.println("Saldo da conta: " + c3.getSaldo());
	}
}

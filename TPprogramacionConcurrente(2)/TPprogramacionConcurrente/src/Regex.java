/*
public class Regex {
	private Log log;
	private String i1[] = { "16", "0", "1" };
	private String i2[] = { "2", "3" };
	private String i3[] = { "4", "5", "7" };
	private String i4[] = { "16", "8", "9" };
	private String i5[] = { "10", "11" };
	private String i6[] = { "12", "13", "15" };
	private int contador[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
						//    0, 1, 2, 3, 4, 5, 7, 8, 9,10,11,12,13,15,16		numero de transiciones
	
						//	  0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14		posiciones deltro del vector

	public Regex(Log log) {
		this.log = log;
	}

	public void chequeoInvariante1() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i1[k])) {
				k++;
				if (k > i1.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 1 = " + contador);
		this.contador[14] = this.contador[14] + contador;
		this.contador[0] = this.contador[0] + contador;
		this.contador[1] = this.contador[1] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante2() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i2[k])) {
				k++;
				if (k > i2.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 2 = " + contador);
		this.contador[2] = this.contador[2] + contador;
		this.contador[3] = this.contador[3] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante3() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i3[k])) {
				k++;
				if (k > i3.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 3 = " + contador);
		this.contador[4] = this.contador[4] + contador;
		this.contador[5] = this.contador[5] + contador;
		this.contador[6] = this.contador[6] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante4() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i4[k])) {
				k++;
				if (k > i4.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 4 = " + contador);
		this.contador[14] = this.contador[14] + contador;
		this.contador[7] = this.contador[7] + contador;
		this.contador[8] = this.contador[8] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante5() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i5[k])) {
				k++;
				if (k > i5.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 5 = " + contador);
		this.contador[9] = this.contador[9] + contador;
		this.contador[10] = this.contador[10] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante6() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i6[k])) {
				k++;
				if (k > i6.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 6 = " + contador);
		this.contador[11] = this.contador[11] + contador;
		this.contador[12] = this.contador[12] + contador;
		this.contador[13] = this.contador[13] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariantes() {
		chequeoInvariante1();
		chequeoInvariante2();
		chequeoInvariante3();
		chequeoInvariante4();
		chequeoInvariante5();
		chequeoInvariante6();
		log.eliminar(contador);
	}
}
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Regex {
	private Log log;
	private StringBuffer texto = new StringBuffer();
	private String invariante1 = "16 0 1";
	private String invariante1v2 = "(16 )(.*?)(0 )(.*?)(1 )"; 
	private String invariante2 = "2 3";
	private String invariante3 = "4 5 7 ";
	private String invariante4 = "16 8 9 ";
	private String invariante5 = "10 11 ";
	private String invariante6 = "12 13 15 ";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();
	public Regex (Log log) {
		this.log = log;
	}

	public void chequeoInvariantes() {
		//texto = log.sacarInfo();
		texto.append("16 0 1 3 6 7 3 16 0 1 3 6 2 16 3 2 0 3 7 1 3 16 0 1 3 6 7 3 16 0 1 3 6 7 3 2 3 16 0 1 3 6 7 3 16 0 1 3 6 7 3 16 0 1 3 6 7 3 16 0 1 3 6 7 3 2 3 2 3 2 3 8 4 5 6");
		Pattern pattern = Pattern.compile(invariante1, Pattern.LITERAL);

		Pattern pattern1v2 = Pattern.compile(invariante1v2, Pattern.LITERAL);

		Pattern pattern2 = Pattern.compile(invariante2, Pattern.LITERAL);
		Pattern pattern3 = Pattern.compile(invariante3, Pattern.LITERAL);
		Pattern pattern4 = Pattern.compile(invariante4, Pattern.LITERAL);
		Pattern pattern5 = Pattern.compile(invariante5, Pattern.LITERAL);
		Pattern pattern6 = Pattern.compile(invariante6, Pattern.LITERAL);

		Matcher matcher = pattern.matcher(texto);
		
		
		
		//boolean matchFound = matcher.find();
		int contador = 0;
		while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
			startIndexs.add(matcher.start());
			endIndexs.add(matcher.end());
			contador++;
		}
		System.out.println("invariante 1 " + contador);
		for (int j = contador; j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);

		Matcher matcher1v2 = pattern1v2.matcher(texto);

		contador = 0;
		while (matcher1v2.find()) {
            System.out.print("Start index: " + matcher1v2.start());
            System.out.print(" End index: " + matcher1v2.end() + " ");
			System.out.println(matcher1v2.group());
			startIndexs.add(matcher1v2.start());
			endIndexs.add(matcher1v2.end());
			contador++;
		}
		System.out.println("invariante 1 v2 " + contador);
		// for (int j = contador; j > 0; j--) {
		// 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		// }
		// System.out.println(texto);
		/*
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		Matcher matcher2 = pattern2.matcher(texto);
		while (matcher2.find()) {
            System.out.print("Start index: " + matcher2.start());
            System.out.print(" End index: " + matcher2.end() + " ");
		 	System.out.println(matcher2.group());
		 	startIndexs.add(matcher2.start());
		 	endIndexs.add(matcher2.end());
		 	contador++;
		}
		System.out.println("invariante 2 " + contador);
		for (int j = contador; j > 0; j--) {
		 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		Matcher matcher3 = pattern3.matcher(texto);
		while (matcher3.find()) {
            System.out.print("Start index: " + matcher3.start());
            System.out.print(" End index: " + matcher3.end() + " ");
		 	System.out.println(matcher3.group());
		 	startIndexs.add(matcher3.start());
		 	endIndexs.add(matcher3.end());
		 	contador++;
		}
		System.out.println("invariante 3 " + contador);
		for (int j = contador; j > 0; j--) {
		 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);
		Matcher matcher4 = pattern4.matcher(texto);
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		while (matcher4.find()) {
            System.out.print("Start index: " + matcher4.start());
            System.out.print(" End index: " + matcher4.end() + " ");
		 	System.out.println(matcher4.group());
		 	startIndexs.add(matcher4.start());
		 	endIndexs.add(matcher4.end());
		 	contador++;
		}
		System.out.println("invariante 4 " + contador);
		for (int j = contador; j > 0; j--) {
		 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);
		Matcher matcher5 = pattern5.matcher(texto);
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		while (matcher5.find()) {
            System.out.print("Start index: " + matcher5.start());
            System.out.print(" End index: " + matcher5.end() + " ");
		 	System.out.println(matcher5.group());
		 	startIndexs.add(matcher5.start());
		 	endIndexs.add(matcher5.end());
		 	contador++;
		}
		System.out.println("invariante 5 " + contador);
		for (int j = contador; j > 0; j--) {
		 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		Matcher matcher6 = pattern6.matcher(texto);
		while (matcher6.find()) {
            System.out.print("Start index: " + matcher6.start());
            System.out.print(" End index: " + matcher6.end() + " ");
		 	System.out.println(matcher6.group());
		 	startIndexs.add(matcher6.start());
		 	endIndexs.add(matcher6.end());
		 	contador++;
		}
		System.out.println("invariante 6 " + contador);
		for (int j = contador; j > 0; j--) {
		 	texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		System.out.println(texto);
		*/
	}
    
}

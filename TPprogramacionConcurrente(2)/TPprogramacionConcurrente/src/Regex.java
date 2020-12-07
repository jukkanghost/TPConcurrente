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


	private String invariante1 = "(16\\s)(.*?)((0\\s)(.*?)(1\\s)|(8\\s)(.*?)(9\\s))"; 
	private String invariante2 = "(2\\s)(.*?)(3\\s)";
	private String invariante3 = "(4\\s)(.*?)(5\\s)(.*?)(7\\s)";
	//private String invariante4 = "(16\\s)(.*?)(8\\s)(.*?)(9\\s)";
	private String invariante5 = "(10\\s)(.*?)(11\\s)";
	private String invariante6 = "(12\\s)(.*?)(13\\s)(.*?)(15\\s)";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();
	public Regex (Log log) {
		this.log = log;
	}

	public void chequeoInvariantes() {
		texto = log.sacarInfo();
		texto.append("16 0 1 14 2 6 3 4 14 5 6 6 7 14 16 6 8 9 6 10 14 11 14 12 13 15 ");
		
		Pattern pattern = Pattern.compile(invariante1);
		Pattern pattern2 = Pattern.compile(invariante2);
		Pattern pattern3 = Pattern.compile(invariante3);
		//Pattern pattern4 = Pattern.compile(invariante4);
		Pattern pattern5 = Pattern.compile(invariante5);
		Pattern pattern6 = Pattern.compile(invariante6);

		int contadorinv1 = 0;
		int contadorinv2 = 0;
		int contadorinv3 = 0;
		int contadorinv4 = 0;
		int contadorinv5 = 0;
		int contadorinv6 = 0;
		
		/*
		Matcher matcher4 = pattern4.matcher(texto);
		contador = 0;
		startIndexs.clear();
		endIndexs.clear();
		while (matcher4.find()) {
            System.out.print("Start index: " + matcher4.start());
            System.out.print(" End index: " + matcher4.end() + " ");
			for (int i = 0; i <= matcher4.groupCount(); i++) {
				System.out.println("Match " + i + " " + matcher4.group(i));	
			}
			startIndexs.add(matcher4.start(1));
			endIndexs.add(matcher4.end(1));
			startIndexs.add(matcher4.start(3));
			endIndexs.add(matcher4.end(3));
			startIndexs.add(matcher4.start(5));
			endIndexs.add(matcher4.end(5));
		 	contador++;
		}
		System.out.println("invariante 4 " + contador);

		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
*/
		Matcher matcher5 = pattern5.matcher(texto);

		startIndexs.clear();
		endIndexs.clear();
		while (matcher5.find()) {
            // System.out.print("Start index: " + matcher5.start());
            // System.out.print(" End index: " + matcher5.end() + " ");
			// for (int i = 0; i <= matcher5.groupCount(); i++) {
			// 	System.out.println("Match " + i + " " + matcher5.group(i));	
			// }
			startIndexs.add(matcher5.start(1));
			endIndexs.add(matcher5.end(1));
			startIndexs.add(matcher5.start(3));
			endIndexs.add(matcher5.end(3));
		 	contadorinv5++;
		}
		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}

		startIndexs.clear();
		endIndexs.clear();

		Matcher matcher6 = pattern6.matcher(texto);
		while (matcher6.find()) {
            // System.out.print("Start index: " + matcher6.start());
            // System.out.print(" End index: " + matcher6.end() + " ");
			// for (int i = 0; i <= matcher6.groupCount(); i++) {
			// 	System.out.println("Match " + i + " " + matcher6.group(i));	
			// }
			startIndexs.add(matcher6.start(1));
			endIndexs.add(matcher6.end(1));
			startIndexs.add(matcher6.start(3));
			endIndexs.add(matcher6.end(3));
			startIndexs.add(matcher6.start(5));
			endIndexs.add(matcher6.end(5));
		 	contadorinv6++;
		}
		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}		
		
		startIndexs.clear();
		endIndexs.clear();

		Matcher matcher2 = pattern2.matcher(texto);
		while (matcher2.find()) {
			// for (int i = 0; i <= matcher2.groupCount(); i++) {
			// 	System.out.println("Match " + i + " " + matcher2.group(i));
			// }
			startIndexs.add(matcher2.start(1));
			endIndexs.add(matcher2.end(1));
			startIndexs.add(matcher2.start(3));
			endIndexs.add(matcher2.end(3));
			contadorinv2++;
		}
		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}
		
		Matcher matcher = pattern.matcher(texto);
		
		startIndexs.clear();
		endIndexs.clear();

		while (matcher.find()) {
			// System.out.print("Start index: " + matcher.start());
			// System.out.print(" End index: " + matcher.end() + " ");
			// for (int i = 0; i <= matcher.groupCount(); i++) {
			// 	System.out.println("Match " + i + " " + matcher.group(i));
			// }
			startIndexs.add(matcher.start(1));
			endIndexs.add(matcher.end(1));
			if (matcher.group(4)==null) {
				startIndexs.add(matcher.start(7));
				endIndexs.add(matcher.end(7));
				startIndexs.add(matcher.start(9));
				endIndexs.add(matcher.end(9));
				contadorinv4++;
			}
			else {
				startIndexs.add(matcher.start(4));
				endIndexs.add(matcher.end(4));
				startIndexs.add(matcher.start(6));
				endIndexs.add(matcher.end(6));
				contadorinv1++;
			}
		}
		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}

		startIndexs.clear();
		endIndexs.clear();

		Matcher matcher3 = pattern3.matcher(texto);
		while (matcher3.find()) {
            // System.out.print("Start index: " + matcher3.start());
            // System.out.print(" End index: " + matcher3.end() + " ");
			// for (int i = 0; i <= matcher3.groupCount(); i++) {
			// 	System.out.println("Match " + i + " " + matcher3.group(i));
			// }
			startIndexs.add(matcher3.start(1));
			endIndexs.add(matcher3.end(1));
			startIndexs.add(matcher3.start(3));
			endIndexs.add(matcher3.end(3));
			startIndexs.add(matcher3.start(5));
			endIndexs.add(matcher3.end(5));
		 	contadorinv3++;
		}
		for (int j = startIndexs.size(); j > 0; j--) {
			texto.delete(startIndexs.get(j-1), endIndexs.get(j-1));
		}


		System.out.println("invariante 1 " + contadorinv1);
		System.out.println("invariante 2 " + contadorinv2);
		System.out.println("invariante 3 " + contadorinv3);
		System.out.println("invariante 4 " + contadorinv4);
		System.out.println("invariante 5 " + contadorinv5);
		System.out.println("invariante 6 " + contadorinv6);

		log.escribirLog(texto);
	}
    
}

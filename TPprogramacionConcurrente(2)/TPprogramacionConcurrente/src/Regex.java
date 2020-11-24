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

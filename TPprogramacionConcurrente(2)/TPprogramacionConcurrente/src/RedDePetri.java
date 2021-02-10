public class RedDePetri {
	private int[][] matrizIncidencia = { 
			{ -1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,     -1,      0 },
			{ 1,	-1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0,      0 }, 
			{ 0,	0,     -1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      1 },
			{ 0,	0,	1,     -1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0 }, 
			{ 0,	0,     -1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0 },
			{ 0,	0,	0,	0,     -1,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,      0 }, 
			{ 0,	0,	0,	0,	1,     -1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0 },
			{ 0,	0,	0,	0,      0,     -1,     -1,	0,	0,	0,	0,	0,	0,	0,	0,	0,      1 }, 
			{ 0,	0,	0,	0,	0,	1,	0,     -1,	0,	0,	0,	0,	0,	0,	0,	0,      0 },
			{ 0,	1,	0,	0,	0,	0,	0,      0,     -1,	0,	0,	0,	0,	0,	0,	0,      1 }, 
			{ 0,	1,	0,	0,	0,	0,	0,	0,	0,     -1,	0,	0,	0,	0,	0,	0,      0 },
			{ 0,	0,	0,	0,	0,	0,	0,	0,	0,      1,     -1,	0,	0,	0,	0,	0,      0 }, 
			{ 0,	0,	0,	0,	0,	0,	0,	0,      0,     -1,	1,	0,	0,	0,	0,	0,      0 },
			{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0,     -1,	0,	0,	1,	0,      0 }, 
			{ 0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,     -1,     -1,	0,	0,      0 },
			{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,     -1,	0,	0,	0,      0 }, 
			{ 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,     -1,	0,      0 },
                        { 0,    0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      1,     -1 }, 
        };
	private int[][] backwardsIncidenceMatrix = {
			{1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      1,      0},
			{0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	1,	0,	0,	0,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,      0},
			{0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	1,	1,	0,      0},
                        {0,     0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      1},
        };
	private int[] marcado = { 0,	0,	0,	0,	1,	1,	0,	0,	0,	1,	0,	0,	1,	1,	0,	0,	0,      0 };
	private int[][] h = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[] e = new int[17];
	private int[] q = new int[18];
	private int[] b = new int[17];
	private int[] ex = new int[17];
	private Tiempo tiempo;
	

	public RedDePetri(Tiempo tiempo) {
		calcularE();
		calcularQ();
		calcularB();
		ex = calcularConjuncion(e, b);
		this.tiempo = tiempo;
	}

	public boolean evaluarDisparo(Transicion t) {
		boolean aviso = false;
		int mult[] = new int[marcado.length];
		int suma[] = new int[marcado.length];
		int conjuncion[] = calcularConjuncion(t.getTransicion(), ex);
		for (int i = 0; i < marcado.length; i++) {
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < 17; k++) {
					mult[i] += matrizIncidencia[i][k] * conjuncion[k];
				}
			}
		}
		for(int i = 0; i<marcado.length; i++) {
			if(mult[i]!=0) {
				aviso=true;
				break;
			}
		}
		if(!aviso) {
			return false; // return false
		}
		for (int i = 0; i < marcado.length; i++) {
			suma[i] = marcado[i] + mult[i];
			if (suma[i] < 0) {
				return false; // return false
			}
		}
		//Si es temporal
		if (tiempo.esTemporal(t)) {
			long time = tiempo.calcularTiempo(t);
			System.out.println("tiempo " + time);
			if (time>0) {
				return false;
			}
			else {

				return true;
			}
		}
		return true;
	}
	
	public int disparar(Transicion t) {
		boolean aviso = false;
		int mult[] = new int[marcado.length];
		int suma[] = new int[marcado.length];
		int conjuncion[] = calcularConjuncion(t.getTransicion(), ex);
		for (int i = 0; i < marcado.length; i++) {
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < 17; k++) {
					mult[i] += matrizIncidencia[i][k] * conjuncion[k];
				}
			}
		}
		for(int i = 0; i<marcado.length; i++) {
			if(mult[i]!=0) {
				aviso=true;
				break;
			}
		}
		if(!aviso) {
			return 0;
		}
		for (int i = 0; i < marcado.length; i++) {
			suma[i] = marcado[i] + mult[i];
			if (suma[i] < 0) {
				return 0;
			}
		}
		marcado = suma;
		calcularE();
		calcularQ();
		calcularB();
		ex = calcularConjuncion(e, b);
		//borra el valor
		tiempo.setTiempoActual(0, t.getId());
		//Setear tiempos
		System.out.println("Estoy aca");
		int[] sensibilizadas = getTransicionesSensibilizadas();
		for (int i = 0; i < sensibilizadas.length; i++) {
			System.out.println("sensibilizadas " +sensibilizadas[i]);
		}
		
		for (int i = 0; i < sensibilizadas.length; i++ ) {
			if(tiempo.esTemporal2(sensibilizadas[i])) {
				System.out.println("Soy temporal " + sensibilizadas[i] );
				if(tiempo.getTiempoInicial(sensibilizadas[i]) == 0) {

					tiempo.setTiempoActual(System.currentTimeMillis(), sensibilizadas[i]);
					tiempo.displayTiempo();
				}
			}
		}
		return 1;
	}
	
	private void calcularE() {
		for(int i = 0; i<17; i++) {
			e[i]=1;
			for(int j = 0; j<marcado.length; j++) {
				if(backwardsIncidenceMatrix[j][i] == 1) {
					if(marcado[j]==0) {
						e[i]=0;
						break;
					}
				}
			}
		}
	}
	
	private void calcularQ() {
		for(int i = 0; i<marcado.length; i++) {
			q[i] = 1;
			if(marcado[i]!=0) {
				q[i] =0;
			}
		}
	}
	
	private void calcularB() {
		for(int i = 0; i<17; i++) {
			b[i]=1;
			for(int j = 0; j<marcado.length; j++) {
				if(h[j][i] == 1) {
					if(marcado[j]>0) {
						b[i]=0;
						break;
					}
				}
			}
		}
	}
	
	private int[] calcularConjuncion(int[] t1, int[] t2) {
		int[] t3 = new int[17];
		for(int i = 0; i<17; i++) {
			if(t1[i] == 1 && t2[i]==1) {
				t3[i]=1;
			}
			else {
				t3[i]=0;
			}
		}
		return t3;
	}

	public int getElementosBuffer1() {
		return marcado[10];
	}

	public int getElementosBuffer2() {
		return marcado[2];
	}
	
	public int getMP0() {
		return marcado[0];
	}
	
	public int getMP1() {
		return marcado[1];
	}
	
	public int getMP9() {
		return marcado[17];
	}
	
	public int getMP3() {
		return marcado[11];
	}
	
	public int getMP4() {
		return marcado[12];
	}
	
	public int getMP5() {
		return marcado[13];
	}
	
	public int getMP7() {
		return marcado[15];
	}
	
	public int getMP8() {
		return marcado[16];
	}
	
	public int getMP11() {
		return marcado[3];
	}
	
	public int getMP12() {
		return marcado[4];
	}
	
	public int getMP13() {
		return marcado[5];
	}

	public int getMP14() {
		return marcado[6];
	}
	
	public int getMP16() {
		return marcado[8];
	}
        
	public int getMP17() { return marcado[9]; }
        
	public int[] getTransicionesSensibilizadas() {
            int contador = 0;
            for(int i = 0; i < ex.length; i++) {
                if(ex[i] > 0) {
                    contador++;
                }
            }
            int aux = 0;
            int[] tSensibilizadas = new int[contador];
            for(int i = 0; i < ex.length; i++) {
                if(ex[i] > 0) {
                    tSensibilizadas[aux] = i;
                    aux++;
                }
            }
            return tSensibilizadas;
	}

}


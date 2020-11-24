public class Main {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		
		int t0[] = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t1[] = {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t2[] = {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0};
		int t3[] = {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0};
		int t4[] = {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		int t5[] = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0};
		int t6[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0};
		int t7[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
		int t8[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
		int t9[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		int t10[] = {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t11[] = {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t12[] = {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0};
		int t13[] = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0};
		int t14[] = {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		int t15[] = {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0};
		int t16[] = {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0};

		Tiempo tiempo = new Tiempo();
		RedDePetri rdp = new RedDePetri(tiempo);
		Buffer buffer1 = new Buffer(rdp, 1);
		Buffer buffer2 = new Buffer(rdp, 2);
		Log log = new Log();
		Politica politica = new Politica(buffer1, buffer2, rdp);
		Monitor monitor = new Monitor(rdp, politica, log, tiempo);
		Regex regex = new Regex(log);


		Encendido encendido1 = new Encendido(monitor, t4, t5, t7);
		Encendido encendido2 = new Encendido(monitor, t12, t13, t15);
		Auxiliar auxiliar1 = new Auxiliar(monitor, t6);
		Auxiliar auxiliar2 = new Auxiliar(monitor, t14);
		Servicio servicio1 = new Servicio(monitor, t2, t3);
		Servicio servicio2 = new Servicio(monitor, t10, t11);
		Arribo arribo = new Arribo(monitor, t16);
		aBuffer aBuffer1 = new aBuffer(t0, t1, monitor);
		aBuffer aBuffer2 = new aBuffer(t8, t9, monitor);
		
		Thread enc1 = new Thread(encendido1);
		enc1.setName("ENCENDIDO 1");
		Thread enc2 = new Thread(encendido2);
		enc2.setName("ENCENDIDO 2");
		Thread aux1 = new Thread(auxiliar1);
		aux1.setName("AUXILIAR 1");
		Thread aux2 = new Thread(auxiliar2);
		aux2.setName("AUXILIAR 2");
		Thread serv1 = new Thread(servicio1);
		serv1.setName("SERVICIO 1");
		Thread serv2 = new Thread(servicio2);
		serv2.setName("SERVICIO 2");
		Thread ar = new Thread(arribo);
		ar.setName("ARRIBO");
		Thread ab1 = new Thread(aBuffer1);
		ab1.setName("BUFFER 1");
		Thread ab2 = new Thread(aBuffer2);
		ab2.setName("BUFFER 2");
		
		enc1.start();
		enc2.start();
		aux1.start();
		aux2.start();
		serv1.start();
		serv2.start();
		ar.start();
		ab1.start();
		ab2.start();

		try {
			serv1.join();
			serv2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		enc1.interrupt();
		enc2.interrupt();
		aux1.interrupt();
		aux2.interrupt();
		ar.interrupt();
		ab1.interrupt();
		ab2.interrupt();

		long endTime = System.nanoTime() - startTime;
		int milisegundos = (int) (endTime/1e6) ;
		int segundos = (int) (endTime / 1e9);
		int minutos = (int) (segundos / 60);
		segundos = segundos - minutos * 60;
		milisegundos = milisegundos - segundos*1000;
		System.out.println("\n\n");
		log.cerrar();
		regex.chequeoInvariantes();
		
		System.out.println("\n-----FIN DEL MAIN-----");
		System.out.println("Duracion: " + minutos + " minutos, " + segundos + " segundos, " + milisegundos + "milisegundos");
	}
}

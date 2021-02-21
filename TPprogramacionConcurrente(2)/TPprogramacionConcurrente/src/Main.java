import java.util.List;
import java.util.ArrayList;

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

		Transicion transicion0 = new Transicion(t0, 0);
		Transicion transicion1 = new Transicion(t1, 1);
		Transicion transicion2 = new Transicion(t2, 2);
		Transicion transicion3 = new Transicion(t3, 3);
		Transicion transicion4 = new Transicion(t4, 4);
		Transicion transicion5 = new Transicion(t5, 5);
		Transicion transicion6 = new Transicion(t6, 6);
		Transicion transicion7 = new Transicion(t7, 7);
		Transicion transicion8 = new Transicion(t8, 8);
		Transicion transicion9 = new Transicion(t9, 9);
		Transicion transicion10 = new Transicion(t10, 10);
		Transicion transicion11 = new Transicion(t11, 11);
		Transicion transicion12 = new Transicion(t12, 12);
		Transicion transicion13 = new Transicion(t13, 13);
		Transicion transicion14 = new Transicion(t14, 14);
		Transicion transicion15 = new Transicion(t15, 15);
		Transicion transicion16 = new Transicion(t16, 16);

		Administrador administrador = new Administrador();

		
		Tiempo tiempo = new Tiempo();
		RedDePetri rdp = new RedDePetri(tiempo);
		Buffer buffer1 = new Buffer(rdp, 1);
		Buffer buffer2 = new Buffer(rdp, 2);
		Log log = new Log();
		InvPlazas invariante = new InvPlazas(rdp);
		Politica politica = new Politica(buffer1, buffer2, rdp);
		Monitor monitor = new Monitor(rdp, politica, log, tiempo, administrador, invariante);


		List<InvTransicion> listaInvariantes = new ArrayList<>();
		//Tinvariantes
		int [] gruposInvariante1 = {2, 6, 8, 12, 14, 16, 18, 20};
		InvTransicion invariante1 = new InvTransicion("invariante1", gruposInvariante1);
		int [] gruposInvariante2 = {2, 6, 8, 22, 24, 26};
		InvTransicion invariante2 = new InvTransicion("invariante2", gruposInvariante2);
		int [] gruposInvariante3 = {2, 6, 8, 28, 30, 32};
		InvTransicion invariante3 = new InvTransicion("invariante3", gruposInvariante3);
		int [] gruposInvariante4 = {2, 6, 8, 34, 36, 38};
		InvTransicion invariante4 = new InvTransicion("invariante4", gruposInvariante4);
		int [] gruposInvariante5 = {2, 40, 42, 46, 48, 50, 52, 54};
		InvTransicion invariante5 = new InvTransicion("invariante5", gruposInvariante5);
		int [] gruposInvariante6 = {2, 40, 42, 56, 58, 60};
		InvTransicion invariante6 = new InvTransicion("invariante6", gruposInvariante6);
		int [] gruposInvariante7 = {2, 40, 42, 62, 64, 66};
		InvTransicion invariante7 = new InvTransicion("invariante7", gruposInvariante7);
		int [] gruposInvariante8 = {2, 40, 42, 68, 70, 72};
		InvTransicion invariante8 = new InvTransicion("invariante8", gruposInvariante8);
		listaInvariantes.add(invariante1);
		listaInvariantes.add(invariante2);
		listaInvariantes.add(invariante3);
		listaInvariantes.add(invariante4);
		listaInvariantes.add(invariante5);
		listaInvariantes.add(invariante6);
		listaInvariantes.add(invariante7);
		listaInvariantes.add(invariante8);
		Regex regex = new Regex(log, listaInvariantes);


		Encendido encendido1 = new Encendido(monitor, transicion4, transicion5, transicion7, administrador);
		Encendido encendido2 = new Encendido(monitor, transicion12, transicion13, transicion15, administrador);
		Auxiliar auxiliar1 = new Auxiliar(monitor, transicion6, administrador);
		Auxiliar auxiliar2 = new Auxiliar(monitor, transicion14, administrador);
		Servicio servicio1 = new Servicio(monitor, transicion2, transicion3, administrador);
		Servicio servicio2 = new Servicio(monitor, transicion10, transicion11, administrador);
		Arribo arribo = new Arribo(monitor, transicion16, administrador);
		aBuffer aBuffer1 = new aBuffer(transicion0, transicion1, monitor, administrador, buffer1);
		aBuffer aBuffer2 = new aBuffer(transicion8, transicion9, monitor, administrador, buffer2);

		
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
        Thread ab2 = new Thread(aBuffer2);
		
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

		
		ar.interrupt();
		ab1.interrupt();
		ab2.interrupt();
		enc1.interrupt();
		enc2.interrupt();
		aux1.interrupt();
		aux2.interrupt();
		
		
		long endTime = System.nanoTime() - startTime;
		int milisegundos = (int) (endTime/1e6) ;
		int segundos = (int) (endTime / 1e9);
		int minutos = (int) (segundos / 60);
		segundos = segundos - minutos * 60;
		milisegundos = milisegundos - segundos*1000;
		milisegundos = milisegundos - minutos*60*1000;

		System.out.println("\n\n");
		log.cerrar();
		regex.chequeoInvariantes();
		
		System.out.println("\n-----FIN DEL MAIN-----");
		System.out.println("Duracion: " + minutos + " minutos, " + segundos + " segundos, " + milisegundos + "milisegundos");

	}
	
}

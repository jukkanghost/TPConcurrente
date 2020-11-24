import java.util.concurrent.Semaphore;

public class Monitor {
	private Semaphore semaforo;
	private Semaphore[] semaforos;
	private RedDePetri rdp;
	private Politica politica;
	private Log log;
	private int tareas;
	private int tareas1;
	private int tareas2;
	private Tiempo tiempo;
	private boolean end = true;

	public Monitor(RedDePetri rdp, Politica politica, Log log, Tiempo tiempo) {
		semaforo = new Semaphore(1, true);
		semaforos = new Semaphore[17];
		for (int i = 0; i < semaforos.length; i++) {
			semaforos[i] = new Semaphore(0, true);
		}
		this.rdp = rdp;
		this.politica = politica;
		this.log = log;
		tareas = 0;
		tareas1 = 0;
		tareas2 = 0;
        this.tiempo = tiempo;
	}

	public void disparar(int[] t) {
		try {
			semaforo.acquire();
			while (end) {
				if (rdp.evaluarDisparo(t)) {
					rdp.disparar(t);
                                        int transicion = 9999;
                                        for(int i = 0; i < 17; i++) {
                                            if(t[i] == 1) {
                                                switch(i) {
                                                    case 0: transicion = 0;
                                                        System.out.println("Se dirige al buffer 1");
                                                        break;
                                                    case 1: transicion = 1;
                                                            System.out.println("Arribó una tarea al buffer 1");
                                                            System.out.println("Elementos en el buffer 1: " + rdp.getElementosBuffer1());
                                                            break;
                                                    case 2: transicion = 10;
                                                        tiempo.setTiempoActual(System.currentTimeMillis(), t);
                                                        System.out.println(Thread.currentThread().getName() + " esta resolviendo una tarea");
                                                            break;
                                                    case 3: transicion = 11;
                                                    tareas2++;
                                                    tareas++;
                                                        if (tareas==1000) {
                                                            setEnd();
                                                        }
                                                        System.out.println(Thread.currentThread().getName() + " resolvio " + tareas2 + " tareas.");
                                                        System.out.println(Thread.currentThread().getName() + " resolvió una tarea. Tareas resueltas: " + getTareas());
                                                            break;
                                                    case 4: transicion = 12;
                                                        System.out.println(Thread.currentThread().getName() + " esta encendiendo el nucleo");
                                                        break;
                                                    case 5: transicion = 13;
                                                        System.out.println(Thread.currentThread().getName() + " encendio el nucleo");
                                                        break;
                                                    case 6: transicion = 14;
                                                        System.out.println(Thread.currentThread().getName() + " realizo un disparo");
                                                            break;
                                                    case 7: transicion = 15;
                                                        System.out.println(Thread.currentThread().getName() + " apago el nucleo");
                                                        break;
                                                    case 8: transicion = 16;
                                                        tiempo.setTiempoActual(System.currentTimeMillis(), t);
                                                        System.out.println("Arribo una tarea");
                                                        break;
                                                    case 9: transicion = 2;
                                                        tiempo.setTiempoActual(System.currentTimeMillis(), t);
                                                        System.out.println(Thread.currentThread().getName() + " esta resolviendo una tarea");
                                                            break;
                                                    case 10: transicion = 3;
                                                    tareas1++;
                                                    tareas++;
                                                    if (tareas==1000) {
                                                        setEnd();
                                                    }
                                                        System.out.println(Thread.currentThread().getName() + " resolvio " + tareas1 + " tareas.");
                                                        System.out.println(Thread.currentThread().getName() + " resolvió una tarea. Tareas resueltas: " + getTareas());
                                                             break;
                                                    case 11: transicion = 4;
                                                        System.out.println(Thread.currentThread().getName() + " esta encendiendo el nucleo");
                                                        break;
                                                    case 12: transicion = 5;
                                                        System.out.println(Thread.currentThread().getName() + " encendio el nucleo");
                                                        break;
                                                    case 13: transicion = 6;
                                                        System.out.println(Thread.currentThread().getName() + " realizo un disparo");
                                                        break;
                                                    case 14: transicion = 7;
                                                        System.out.println(Thread.currentThread().getName() + " apago el nucleo");
                                                        break;
                                                    case 15: transicion = 8;
                                                        System.out.println("Se dirige al buffer 2");
                                                        break;
                                                    case 16: transicion = 9;
                                                             System.out.println("Arribó una tarea al buffer 2");
                                                             System.out.println("Elementos en el buffer 2: " + rdp.getElementosBuffer2());
                                                             break;
													default:
														throw new IllegalStateException("Unexpected value: " + i);
												}
                                                break;
                                            }
                                        }
                                        log.escribir(transicion + "\n");
                                        //INVARIANTES DE PLAZA
                                        int p1 = rdp.getMP0() + rdp.getMP1() + rdp.getMP9() + rdp.getMP17();
                                        int p2 = rdp.getMP3() + rdp.getMP4();
                                        int p3 = rdp.getMP5() + rdp.getMP7() + rdp.getMP8();
                                        int p4 = rdp.getMP11() + rdp.getMP12();
                                        int p5 = rdp.getMP13() + rdp.getMP14() + rdp.getMP16();

                                        assert p1 == 1 : String.format("Invariante 1 no cumplido");
                                        assert p2 == 1 : String.format("Invariante 2 no cumplido");
                                        assert p3 == 1 : String.format("Invariante 3 no cumplido");
                                        assert p4 == 1 : String.format("Invariante 4 no cumplido");
                                        assert p5 == 1 : String.format("Invariante 5 no cumplido");

                        int[] sensibilizadas = rdp.getTransicionesSensibilizadas();
                        int decision = politica.resolverConflicto(sensibilizadas); //Una vex que tengo la decisión, despierto a la transici+on elegida (en el vector de semáforos)
                        //int decision = politica.resolverConflictoRandom(sensibilizadas);
                        semaforos[decision].release();
					break;
				} else {
					semaforo.release();
					dormir(t);
					try {
                        semaforo.acquire();
                    } catch (Exception exit) {
					    break;
                    }

                }
			}
		} catch (InterruptedException e) {
		    return;
		} finally {
            if (!end) {
                for (int i = 0; i <semaforos.length ; i++) {
                    semaforos[i].release();
                }
            }
			semaforo.release();
		}
		return;
	}

	private void dormir(int[] t) {
		for (int i = 0; i < t.length; i++) {
			if (t[i] == 1) {
                    if (tiempo.esTemporal(t)) {
                        long time = tiempo.calcularTiempo(t);
                        if (time>0) {
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException exit) {
                                break;
                            }
                        }
                    }
                    try {
                        semaforos[i].acquire();
                    } catch (InterruptedException exit) {
                        break;
                    }
				break;
			}
		}
	}

    public int getTareas() {
	    return tareas;
    }

    public void setEnd() {
	    end = false;
    }
}

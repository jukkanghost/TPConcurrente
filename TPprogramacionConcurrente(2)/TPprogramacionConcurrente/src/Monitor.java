import java.util.concurrent.Semaphore;

public class Monitor {
	private Semaphore semaforo;
	private Semaphore[] semaforos;
	private RedDePetri rdp;
	private Politica politica;
	private Log log;
	private Tiempo tiempo;
	private Administrador administrador;
	private InvPlazas invariante;
	

	public Monitor(RedDePetri rdp, Politica politica, Log log, Tiempo tiempo, Administrador admin,
			InvPlazas invariantes) {
		semaforo = new Semaphore(1, true);
		semaforos = new Semaphore[17];
		for (int i = 0; i < semaforos.length; i++) {
			semaforos[i] = new Semaphore(0, true);
		}
		
		this.rdp = rdp;
		this.politica = politica;
		this.log = log;
		this.tiempo = tiempo;
		this.administrador = admin;
		this.invariante = invariantes;
	}

	public void disparar(Transicion t) {
		try {
			semaforo.acquire();
			while (!administrador.getEndServicio()) {
				if (rdp.evaluarDisparo(t)) {
					rdp.disparar(t);
<<<<<<< HEAD
					log.escribir(t.getId() + "  ");
					//INVARIANTES DE PLAZA
					invariante.CheckInvPlazas();

                    int[] sensibilizadas = rdp.getTransicionesSensibilizadas();
					for (int i = 0; i < sensibilizadas.length; i++) {
						System.out.println("sensibilizadas " +sensibilizadas[i]);
					}
                    int decision = politica.decidir(sensibilizadas); //Una vex que tengo la decisión, despierto a la transici+on elegida (en el vector de semáforos)
                    System.out.println("decision politca "+ decision);
					//int decision = politica.resolverConflictoRandom(sensibilizadas);
					//int decision = politica.resolverConflicto(sensibilizadas);
=======
					log.escribir("-T" + t.getId() + "-");
					
					// INVARIANTES DE PLAZA
					invariante.CheckInvPlazas();

					int [] esperando = quienesEstan();

					int [] m = rdp.calcularConjuncion(esperando, rdp.getEx());

					boolean bandera = false;
                    for (int i = 0; i < m.length; i++) {
                        if (m[i] != 0) {
                            bandera = true;
                            break;
                        }
                    }
					
					if (bandera) {
					int decision = politica.decidir(m); // Una vex que tengo la decisión, despierto a la
																		// transici+on elegida (en el vector de
																		// semáforos)
					
					// int decision = politica.resolverConflictoRandom(sensibilizadas);
					// int decision = politica.resolverConflicto(sensibilizadas);
					
>>>>>>> CORRECION-FINAL
					semaforos[decision].release();
					}
					break;
				} else {
					
					semaforo.release();
					dormir(t);
						try {
							semaforo.acquire();
						} catch (Exception exit) {
							System.out.println(t.getId());
							break;
						}
				}
			}
		} catch (InterruptedException e) {
			return;
		} finally {
			if (administrador.getEndArribo()) {
				int [] esperando = quienesEstan();
				for (int i = 0; i < esperando.length; i++) {
					if(esperando[i] != 0) {
						semaforos[i].release();
					}
				}
			}
			semaforo.release();
		}
		return;
	}

	private void dormir(Transicion t) {
		if (tiempo.esTemporal(t)) {
			long time = tiempo.calcularTiempo(t);
			if (time > 0) {
				try {
					Thread.sleep(time);

				} catch (InterruptedException exit) {

				}
			}
		}
		if (!tiempo.esTemporal(t)) {
			try {
				for (int i = 0; i < t.getTransicion().length; i++) {
					if (t.getTransicion()[i] == 1) {
						
						semaforos[i].acquire();
						
					}
				}
			} catch (InterruptedException exit) {

			}
		}
	}

	private int[] quienesEstan() {
        int[] esperando = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < semaforos.length; i++) {
            if (semaforos[i].hasQueuedThreads()) {
                esperando[i] = 1;
            }
        }
        return esperando;
    }

}

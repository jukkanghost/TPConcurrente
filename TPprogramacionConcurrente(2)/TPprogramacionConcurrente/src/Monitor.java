import java.util.concurrent.Semaphore;

public class Monitor {
	private Semaphore semaforo;
	private Semaphore[] semaforos;
	private RedDePetri rdp;
	private Politica politica;
	private Log log;

	private Tiempo tiempo;//private int tareas;
	//private int tareas1;
	//private int tareas2;
	//private boolean end = true;
	private Administrador administrador;
	private InvPlazas invariante;

	public Monitor(RedDePetri rdp, Politica politica, Log log, Tiempo tiempo, Administrador admin, InvPlazas invariantes) {
		semaforo = new Semaphore(1, true);
		semaforos = new Semaphore[17];
		for (int i = 0; i < semaforos.length; i++) {
			semaforos[i] = new Semaphore(0, true);
		}
		this.rdp = rdp;
		this.politica = politica;
		this.log = log;
		//tareas = 0;
		//tareas1 = 0;
		//tareas2 = +0;
		this.tiempo = tiempo;
		administrador = admin;
		this.invariante = invariantes;
	}

	public void disparar(Transicion t) {
		try {
			semaforo.acquire();
			while (!administrador.getEnd()) {
				if (rdp.evaluarDisparo(t.getTransicion())) {
					rdp.disparar(t.getTransicion());
					log.escribir(t.getId() + "  ");
					//INVARIANTES DE PLAZA
					invariante.CheckInvPlazas();

					int[] sensibilizadas = rdp.getTransicionesSensibilizadas();
					//int decision = politica.resolverConflicto(sensibilizadas);
					int decision = politica.decidir(sensibilizadas); //Una vex que tengo la decisión, despierto a la transici+on elegida (en el vector de semáforos)
					//int decision = politica.resolverConflictoRandom(sensibilizadas);
					semaforos[decision].release();
					break;
				} else {
					semaforo.release();
					dormir(t.getTransicion());
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
			if (administrador.getEnd()) {
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

   /* public int getTareas() {
	    return tareas;
    }

    public void setEnd() {
	    end = false;
    }
    */

  /*public void liberarHilos() {
	  for (int i = 0; i <semaforos.length ; i++) {
		  semaforos[i].release();
	  }
	  return;
  }
   */
}

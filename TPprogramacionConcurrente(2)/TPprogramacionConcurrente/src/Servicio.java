public class Servicio implements Runnable{
	private Monitor monitor;
	private int t1[];
	private int t2[];
	private static final int TAREAS_COMPLETADAS = 1000; // Cada hilo hace 500 tareas.
	
	public Servicio(Monitor monitor, int[] t1, int[] t2) {
		this.monitor=monitor;
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public void run() {
		while(monitor.getTareas()<TAREAS_COMPLETADAS) {
				monitor.disparar(t1);
				monitor.disparar(t2);
			}
		}	
	}


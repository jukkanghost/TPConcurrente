public class Encendido implements Runnable{
	private Monitor monitor;
	private int t1[];
	private int t2[];
	private int t3[];
	public Encendido(Monitor monitor, int[] t1, int[] t2, int[] t3) {
		this.monitor=monitor;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}

	@Override
	public void run() {
		while(monitor.getTareas()<1000) {
			monitor.disparar(t1);
			monitor.disparar(t2);
			monitor.disparar(t3);
		}
	}

}


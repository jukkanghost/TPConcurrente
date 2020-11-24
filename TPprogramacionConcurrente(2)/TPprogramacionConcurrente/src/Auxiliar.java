public class Auxiliar implements Runnable{
	private Monitor monitor;
	private int t1[];
	
	public Auxiliar(Monitor monitor, int[] t1) {
		this.monitor=monitor;
		this.t1 = t1;
	}

	@Override
	public void run() {
		while(monitor.getTareas()<1000) {
			monitor.disparar(t1);
		}
	}

}

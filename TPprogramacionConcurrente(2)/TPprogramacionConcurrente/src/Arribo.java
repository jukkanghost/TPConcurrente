public class Arribo implements Runnable {
	private Monitor monitor;
	private int t[];
        
	public Arribo(Monitor monitor, int[] t) {
		this.monitor=monitor;
		this.t = t;
	}

	@Override
	public void run() {
		while(monitor.getTareas()<1000) {
				monitor.disparar(t);
			}
		}
	}
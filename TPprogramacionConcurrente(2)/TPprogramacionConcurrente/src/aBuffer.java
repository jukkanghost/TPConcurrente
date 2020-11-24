public class aBuffer implements Runnable {
    
    private Monitor monitor;
    private int t1[];
    private int t2[];
    private String buffer;
    
    public aBuffer(int t1[], int t2[], Monitor monitor) {
        this.t1 = t1;
        this.t2 = t2;
        this.monitor = monitor;
    }
    
    @Override
    public void run() {
        while(monitor.getTareas()<1000) {
            monitor.disparar(t1);
            monitor.disparar(t2);
        }
    }
}

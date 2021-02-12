public class Tiempo {
    private int []tiempo = {0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 10};
    private long []tiempoInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public Tiempo() {
    }

    public void setTiempoActual (long tiempo, int transicion) {
       tiempoInicial[transicion] = tiempo;
        return;
    }

    public long getTiempoInicial (Transicion transicion) {
        return tiempoInicial[transicion.getId()];
    }

    public boolean esTemporal (Transicion transicion) {
        boolean temp = false;
        if (tiempo[transicion.getId()] > 0) {
            temp = true;
        }
        return temp;
    }

    public long calcularTiempo(Transicion transicion) {
        long tiempoinicial = tiempoInicial[transicion.getId()];
        long tiempofinal = System.currentTimeMillis() - tiempoinicial;
        long tiemporestante = tiempo[transicion.getId()] - tiempofinal;
        return tiemporestante;
    }

    public void displayTiempo() {
        System.out.println("Tiempo iniciales");
        for (int i = 0; i < tiempoInicial.length; i++) {
            System.out.print(tiempoInicial[i] + " ");
        }
    }
}

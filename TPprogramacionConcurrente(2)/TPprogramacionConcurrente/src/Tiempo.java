public class Tiempo {
    private int []tiempo = {0, 0, 0, 20, 0, 0, 0, 0, 10, 0, 20, 0, 0, 0, 0, 0, 0};
    private long []tiempoInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //private int []temporales = {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1};
    private int [] sensibilizada = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};


    public Tiempo() {
    }

    public void setTiempoActual (long tiempo, int transicion) {
        tiempoInicial[transicion] = tiempo;
        return;
    }

    public boolean esTemporal (Transicion transicion) {
        boolean temp = false;
        for (int i = 0; i < transicion.getTransicion().length; i++) {
            if (transicion.getTransicion()[i] == 1){
                if (tiempo[i] > 0) {
                    temp = true;
                }
            }
        }

        return temp;
    }

    public long calcularTiempo(Transicion transicion) {
        long tiemporestante = 0;
        for (int i = 0; i < transicion.getTransicion().length; i++) {
            if (transicion.getTransicion()[i] == 1) {
                long tiempoinicial = tiempoInicial[i];
                long tiempofinal = System.currentTimeMillis() - tiempoinicial;
                tiemporestante = tiempo[i] - tiempofinal;
            }
        }
        return tiemporestante;
    }

    public void displayTiempo() {
        System.out.println("Tiempo iniciales");
        for (int i = 0; i < tiempoInicial.length; i++) {
            System.out.print(tiempoInicial[i] + " ");
        }
    }

    public boolean esTemporal2 (int transicion) {
        boolean temp = false;
        if (tiempo[transicion]>0) {
            temp = true;
        }
        return temp;
    }

    public long getTiempoInicial (int transicion) {
        return tiempoInicial[transicion];
    }

    public boolean getSensibilizada (int t) {
        return sensibilizada[t] == 1;
    }

    public void setSensibilizada (int t, int valor) {
        sensibilizada[t] = valor;
    }
}
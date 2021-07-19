import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Administrador {
    private boolean endServicio;
    private boolean endArribo;
    private int tareasCompletadas;
    private int tareasArribadas;
    ReentrantReadWriteLock locki; 

    private static final int TAREAS_COMPLETADAS = 1000;
    private static final int TAREAS_ARRIBADAS = 1000;

    public Administrador() {
        endServicio = false;
        endArribo = false;
        tareasCompletadas = 0;
        tareasArribadas = 0;
        locki = new ReentrantReadWriteLock();
    }

    public int getTareas() {
        locki.readLock().lock();
        int ret = tareasCompletadas;
        locki.readLock().unlock();
        return ret;
    }

    public void tareaCompetada() {
        locki.writeLock().lock();
        if (tareasCompletadas == TAREAS_COMPLETADAS - 1) {
            setEndServicio();
        } else {
            tareasCompletadas++;
        }
        locki.writeLock().unlock();
    }

    public void tareaArribada() {
        if (tareasArribadas == TAREAS_ARRIBADAS - 1) {
            setEndArribo();
        } else {
            tareasArribadas++;
        }
    }

    public void setEndServicio() {
        endServicio = true;
    }

    public void setEndArribo() {
        endArribo = true;
    }

    public int getTareasCompletadas() {
        return TAREAS_COMPLETADAS;
    }

    public int getTareasArribadas() {
        return TAREAS_ARRIBADAS;
    }

    public boolean getEndServicio() {
        return endServicio;
    }

    public boolean getEndArribo() {
        return endArribo;
    }
}

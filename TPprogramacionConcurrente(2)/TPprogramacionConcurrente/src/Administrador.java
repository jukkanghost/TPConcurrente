import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Administrador {
    private boolean end;
    private int tareas;

    private static final int TAREAS_COMPLETADAS = 1000;

    //private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //private final Lock read = readWriteLock.readLock();
    //private final Lock write = readWriteLock.writeLock();

    public Administrador() {
        end = false;
        tareas = 0;
    }

    public synchronized int getTareas() {
               return tareas;
    }

    public synchronized void tareaCompetada() {
            if (tareas == TAREAS_COMPLETADAS) {
                setEnd();
            } else {
                tareas++;
            }
    }

    public void setEnd() {
        end = true;
    }

    public int getTareasCompletadas() {
        return TAREAS_COMPLETADAS;
    }

    public boolean getEnd() {
        return end;
    }
}

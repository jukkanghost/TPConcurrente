import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Administrador {
    private boolean end;
    private int tareas;
    ReentrantReadWriteLock locki; 

    private static final int TAREAS_COMPLETADAS = 1000;

    public Administrador() {
        end = false;
        tareas = 0;
        locki = new ReentrantReadWriteLock();
    }

    public int getTareas() {
        locki.readLock().lock();
        int ret = tareas;
        locki.readLock().unlock();
        return ret;
    }

    public void tareaCompetada() {
        locki.writeLock().lock();
        if (tareas == TAREAS_COMPLETADAS) {
            setEnd();
        } else {
            tareas++;
        }
        locki.writeLock().unlock();
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

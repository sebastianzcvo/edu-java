package swingWorkers.worker;

import lombok.Getter;

import javax.swing.*;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Abstract base class for SwingWorkers that can be paused, resumed, and stopped.
 * Subclasses should implement the doInBackground() method and periodically check shouldStop()
 * and handlePause() to respect pause/stop requests.
 */
public abstract class ControllableWorker<T, V> extends SwingWorker<T, V> {

    @Getter
    private final UUID id = UUID.randomUUID();

    private volatile boolean paused = false;
    private volatile boolean stopped = false;
    private final Lock pauseLock = new ReentrantLock();
    private final Condition unpause = pauseLock.newCondition();

    public void pause() {
        paused = true;
    }

    public void resume() {
        pauseLock.lock();
        try {
            paused = false;
            unpause.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    protected void handlePause() throws InterruptedException {
        pauseLock.lock();
        try {
            while (paused && !stopped) {
                unpause.await();
            }
        } finally {
            pauseLock.unlock();
        }
    }

    public void stop() {
        stopped = true;
        resume(); // Resume if paused to allow stopping
    }

    protected boolean shouldStop() {
        return stopped || isCancelled();
    }

}

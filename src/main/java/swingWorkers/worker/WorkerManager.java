package swingWorkers.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkerManager {

    private final List<ControllableWorker<?, ?>> pausables = new ArrayList<>();
    private final List<ControllableWorker<?, ?>> stoppables = new ArrayList<>();

    public synchronized void submit(ControllableWorker<?, ?> worker) {
        submit(worker, false);
    }

    public synchronized void submit(ControllableWorker<?, ?> worker, boolean stopsOnPause) {
        if (stopsOnPause) stoppables.add(worker);
        else pausables.add(worker);
        worker.execute();
    }

    public synchronized void pause() {
        pausePausables();
        stopStoppables();
    }

    private void pausePausables() {
        pausables.forEach(ControllableWorker::pause);
    }

    private void stopStoppables() {
        stoppables.forEach(ControllableWorker::stop);
        stoppables.clear();
    }

    public void pauseExcept(UUID id) {
        pausePausablesExcept(id);
        stopStoppablesExcept(id);
    }

    private void pausePausablesExcept(UUID id) {
        pausables.stream()
                .filter(w -> !w.getId().equals(id))
                .forEach(ControllableWorker::pause);
    }

    private void stopStoppablesExcept(UUID id) {
        stoppables.stream()
                .filter(w -> !w.getId().equals(id))
                .forEach(ControllableWorker::pause);
        stoppables.clear();
    }

    public synchronized void resume() {
        pausables.forEach(ControllableWorker::resume);
    }
}

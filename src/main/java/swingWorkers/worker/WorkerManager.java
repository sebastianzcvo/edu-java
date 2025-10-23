package swingWorkers.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkerManager {

    private final List<ControllableWorker<?, ?>> pausables = new ArrayList<>();
    private final List<ControllableWorker<?, ?>> stoppables = new ArrayList<>();
    private boolean isHalted = false;

    public synchronized void submit(ControllableWorker<?, ?> worker, boolean stopsOnHalt) {
        if (stopsOnHalt) stoppables.add(worker);
        else pausables.add(worker);
        worker.execute();
    }

    public synchronized boolean halt() {
        if (isHalted) return false;
        isHalted = true;

        pausePausables();
        stopStoppables();

        return true;
    }

    private void pausePausables() {
        pausables.forEach(ControllableWorker::pause);
    }

    private void stopStoppables() {
        stoppables.forEach(ControllableWorker::stop);
        stoppables.clear();
    }

    public synchronized boolean haltExcept(UUID id) {
        if (isHalted) {
            System.out.println("WorkerManager.haltExcept: already halted");
            return false;
        }
        isHalted = true;

        pausePausablesExcept(id);
        stopStoppablesExcept(id);

        return true;
    }

    private void pausePausablesExcept(UUID id) {
        pausables.stream()
                .filter(w -> !w.getId().equals(id))
                .forEach(ControllableWorker::pause);
    }

    private void stopStoppablesExcept(UUID id) {
        stoppables.stream()
                .filter(w -> !w.getId().equals(id))
                .forEach(ControllableWorker::stop);
        stoppables.clear();
    }

    public synchronized void resume() {
        isHalted = false;
        pausables.forEach(ControllableWorker::resume);
    }
}

package swingWorkers.worker;

import java.util.List;
import java.util.function.Consumer;

public class CountingWorker extends ControllableWorker<Integer, Integer> {

    private final WorkerManager workerManager;
    private final Consumer<Integer> publishCallback;
    private final Consumer<Boolean> doneCallback;

    private final int MAX_COUNT = 5;

    public CountingWorker(WorkerManager workerManager, Consumer<Integer> publishCallback, Consumer<Boolean> doneCallback) {
        this.workerManager = workerManager;
        this.publishCallback = publishCallback;
        this.doneCallback = doneCallback;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        if (!workerManager.haltExcept(getId())) return 0;

        for (int i = 1; i <= MAX_COUNT; i++) {
            if (shouldStop() || i == MAX_COUNT) {
                return i;
            }
            publish(i);
            Thread.sleep(1000);
        }
        return 0;
    }

    @Override
    protected void process(List<Integer> chunks) {
        if (shouldStop() || isPaused()) return;
        for (Integer count : chunks) {
            publishCallback.accept(count);
        }
    }

    @Override
    protected void done() {
        if (shouldStop()) return;
        workerManager.resume();
        try {
            Integer result = get();
            doneCallback.accept(result == MAX_COUNT);
        } catch (Exception ex) {
            ex.getCause().printStackTrace();
            doneCallback.accept(false);
        }
    }
}

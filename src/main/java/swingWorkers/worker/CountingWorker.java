package swingWorkers.worker;

import java.util.List;
import java.util.function.Consumer;

public class CountingWorker extends ControllableWorker<Integer, Integer> {

    private final WorkerManager workerManager;
    private final Consumer<String> publishCallback;
    private final Consumer<Boolean> doneCallback;

    public CountingWorker(WorkerManager workerManager, Consumer<String> publishCallback, Consumer<Boolean> doneCallback) {
        this.workerManager = workerManager;
        this.publishCallback = publishCallback;
        this.doneCallback = doneCallback;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        workerManager.pauseExcept(this.getId());
        for (int i = 1; i <= 10; i++) {
            if (shouldStop() || i == 10) {
                return i;
            }
            publish(i);
            Thread.sleep(1000);
        }
        return 0;
    }

    @Override
    protected void process(List<Integer> chunks) {
        for (Integer count : chunks) {
            publishCallback.accept("Count: " + count);
        }
    }

    @Override
    protected void done() {
        try {
            Integer result = get();
            doneCallback.accept(result == 10);
        } catch (Exception ex) {
            ex.getCause().printStackTrace();
            doneCallback.accept(false);
        }
    }
}

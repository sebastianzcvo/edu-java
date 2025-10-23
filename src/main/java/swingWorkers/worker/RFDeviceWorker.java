package swingWorkers.worker;

import swingWorkers.device.RFDevice;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class RFDeviceWorker extends ControllableWorker<Boolean, String> {

    private final WorkerManager workerManager;
    private final RFDevice device;
    private final BiConsumer<String, RFDevice> callback;

    public RFDeviceWorker(RFDevice device, WorkerManager workerManager, BiConsumer<String, RFDevice> callback) {
        this.device = device;
        this.workerManager = workerManager;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        while (!shouldStop()) {
            handlePause();

            if (device.connect() && device.loginToCard()) {
                String data = device.read();
                if (Objects.equals(data, "0")) {

                    if (workerManager.haltExcept(getId())) {
                        publish(data);
                    }

                }
            }
        }
        return false;
    }

    @Override
    protected void process(List<String> chunks) {
        if (shouldStop() || isPaused()) return;
        workerManager.resume();// where should this be? prolly should think of a better example and put it in done()
        for (String data : chunks) {
            callback.accept(data, device);
        }
    }
}

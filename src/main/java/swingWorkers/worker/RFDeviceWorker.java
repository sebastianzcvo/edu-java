package swingWorkers.worker;

import swingWorkers.device.RFDevice;
import swingWorkers.device.RFDeviceMech;
import swingWorkers.device.RFDeviceProx;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public class RFDeviceWorker extends ControllableWorker<Void, String> {

    private final WorkerManager workerManager;
    private final RFDevice device;
    private final Consumer<String> callback;

    public RFDeviceWorker(RFDevice device, WorkerManager workerManager, Consumer<String> callback) {
        this.device = device;
        this.workerManager = workerManager;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!shouldStop()) {
            handlePause();
            if (device.connect() && device.loginToCard()) {
                String data = device.read();
                if (Objects.equals(data, "0")) {
                    workerManager.pause();
                    publish(data);
                }
            }
        }
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String data : chunks) {
            switch (device) {
                case RFDeviceProx prox -> callback.accept("Prox: " + data);
                case RFDeviceMech mech -> callback.accept("Mech: " + data);
            }
        }
    }
}

package swingWorkers.device;

public sealed interface RFDevice permits RFDeviceMech, RFDeviceProx {

    boolean connect();
    boolean loginToCard();
    String read();
    void write(String data);
    void disconnect();

    default void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

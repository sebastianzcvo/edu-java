package swingWorkers.device;


public non-sealed class RFDeviceMech implements RFDevice {

    int cont = 0;
    @Override
    public synchronized boolean connect() {
        return true;
    }

    @Override
    public synchronized boolean loginToCard() {
        return true;
    }

    @Override
    public synchronized String read() {
        sleep(3_000);
        String data = String.valueOf((int) (Math.random() * 10));
        if (cont++ % 4 == 0) data = "0";
        System.out.println("Mech: " + data);
        return data;
    }

    @Override
    public synchronized void write(String data) {
        System.out.println("Prox writing data: " + data);
    }

    @Override
    public synchronized void disconnect() {
    }
}

package ru.avalon.vergentev.j130.labwork4a;

public class Producer implements Runnable {
    private int delay;
    private int quantity;
    private final Storage storage;

    public Producer(int delay, int quantity, Storage storage) {
        this.delay = delay;
        this.quantity = quantity;
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(delay);
                storage.counter(delay, quantity);
            } catch (InterruptedException e) {
                System.out.println("Error in run method Producer class: " + e.getMessage());
            }
        }
        System.out.println("<<" + Thread.currentThread().getName() + ">>" + " has finished.");
    }


    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        if (delay <= 0) {
            delay = 0;
        }
        this.delay = delay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be more or equal zero");
        }
        this.quantity = quantity;
    }
}

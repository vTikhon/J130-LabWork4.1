package ru.avalon.vergentev.j130.labwork4a;

public class Producer implements Runnable {
    private String name;
    private int delay;
    private int quantity;
    private final Storage storage;

    public Producer(String name, int delay, int quantity, Storage storage) {
        this.name = name;
        this.delay = delay;
        this.quantity = quantity;
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            storage.counter(name, delay, quantity);
        }
        System.out.println("Producer has finished.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        if (delay <= 0) {
            throw new IllegalArgumentException("Delay must be more or equal zero");
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

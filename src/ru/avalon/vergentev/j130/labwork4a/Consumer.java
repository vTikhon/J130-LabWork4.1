package ru.avalon.vergentev.j130.labwork4a;

public class Consumer implements Runnable {
    private String name;
    private int delay;
    private int quantity;
    private Storage storage;

    public Consumer(String name, int delay, int quantity, Storage storage) {
        this.name = name;
        this.delay = delay;
        this.quantity = quantity;
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            storage.counter(name, delay, quantity);
            if (i == 9) System.out.println("complete Consumer");
        }
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
        if (quantity >= 0) {
            throw new IllegalArgumentException("Quantity must be less or equal zero");
        }
        this.quantity = quantity;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

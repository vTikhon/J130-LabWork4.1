package ru.avalon.vergentev.j130.labwork4a;

public class Storage {
    private int itemsQuantity;

    public Storage(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public synchronized boolean counter (int quantity) {
        if ((itemsQuantity + quantity) >= 0) {
            itemsQuantity = itemsQuantity + quantity;
            System.out.println(Thread.currentThread().getName() + " changed available quantity to: " + itemsQuantity);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " is waiting for " + -1*quantity + " items. Current quantity: " + itemsQuantity);
            return false;
        }
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(int itemsQuantity) {
        if (itemsQuantity <= 0) {
            throw new IllegalArgumentException("Quantity of items must be more or equal zero");
        }
        this.itemsQuantity = itemsQuantity;
    }
}

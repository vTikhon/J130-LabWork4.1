package ru.avalon.vergentev.j130.labwork4a;

public class Storage {
    private int itemsQuantity;

    public Storage(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public synchronized void counter (String name, int delay, int quantity) {
        if ((itemsQuantity + quantity) >= 0) {
            itemsQuantity = itemsQuantity + quantity;
            System.out.println(name + " changed available quantity to: " + itemsQuantity);
            try {
                //время задержки после поставки
                Thread.sleep(delay);
                //возобновляем все другие потоки
                notifyAll();
                //приостанавливаем текущий поток
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error1 in counter method: " + e.getMessage());
            }
        } else {
            System.out.println(name + " is waiting for " + -1*quantity + " items. Current quantity: " + itemsQuantity);
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error2 in counter method: " + e.getMessage());
            }
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

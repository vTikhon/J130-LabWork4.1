package ru.avalon.vergentev.j130.labwork4a;

public class Storage {
    private int itemsQuantity;

    public Storage(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public synchronized void counter (String name, int delay, int quantity) {
        itemsQuantity = itemsQuantity + quantity;
        System.out.println(name + " changed the quantity of the items: " + itemsQuantity);
        try {
            //время задержки после поставки
            Thread.sleep(delay);
            //возобновляем все другие потоки
            notifyAll();
            //приостанавливаем текущий поток
            wait();
        } catch (InterruptedException e) {
            System.out.println("Error in counter method: " + e.getMessage());
        }
    }
}

package ru.avalon.vergentev.j130.labwork4a;

public class Main {
    public static void main(String[] args) {
        //задаём склад и стартовое кол-во товаров
        Storage storage = new Storage(20);
        //создаём поставщиков
        Producer producer1 = new Producer("producer1", 100, 1, storage);
        Producer producer2 = new Producer("producer2", 200, 2, storage);
        Producer producer3 = new Producer("producer3", 300, 3, storage);
        //создаём покупателей
        Consumer consumer1 = new Consumer("consumer1", 150, -2, storage);
        Consumer consumer2 = new Consumer("consumer2", 250, -3, storage);
        Consumer consumer3 = new Consumer("consumer3", 350, -4, storage);

        //создаём потоки
        Thread thread1 = new Thread(producer1, "producer1");
        Thread thread2 = new Thread(producer2, "producer2");
        Thread thread3 = new Thread(producer3, "producer3");
        Thread thread4 = new Thread(consumer1, "consumer1");
        Thread thread5 = new Thread(consumer2, "consumer2");
        Thread thread6 = new Thread(consumer3, "consumer3");
        //запускаем потоки
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        try {
            thread1.join(producer1.getDelay());
            thread2.join(producer2.getDelay());
            thread3.join(producer3.getDelay());
            thread4.join(consumer1.getDelay());
            thread5.join(consumer2.getDelay());
            thread6.join(consumer3.getDelay());
            synchronized (storage) {
                storage.notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println("Error in main method: " + e.getMessage());
        }
    }
}

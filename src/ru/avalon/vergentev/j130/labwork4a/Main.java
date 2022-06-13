package ru.avalon.vergentev.j130.labwork4a;

public class Main {
    public static void main(String[] args) {
        //задаём склад и стартовое кол-во товаров
        Storage storage = new Storage(30);
        //создаём поставщиков (суммарно прибавят 60)
        Producer producer1 = new Producer("producer1", 100, 1, storage);
        Producer producer2 = new Producer("producer2", 200, 2, storage);
        Producer producer3 = new Producer("producer3", 300, 3, storage);
        //создаём покупателей (суммарно отнимут 90)
        Consumer consumer1 = new Consumer("consumer1", 150, -2, storage);
        Consumer consumer2 = new Consumer("consumer2", 250, -3, storage);
        Consumer consumer3 = new Consumer("consumer3", 350, -4, storage);

        //создаём потоки
        ThreadGroup threadGroupProducers = new ThreadGroup("Producers");
        Thread thread1 = new Thread(threadGroupProducers, producer1, "producer1");
        Thread thread2 = new Thread(threadGroupProducers, producer2, "producer2");
        Thread thread3 = new Thread(threadGroupProducers, producer3, "producer3");
        ThreadGroup threadGroupConsumers = new ThreadGroup("Consumers");
        Thread thread4 = new Thread(threadGroupConsumers, consumer1, "consumer1");
        Thread thread5 = new Thread(threadGroupConsumers, consumer2, "consumer2");
        Thread thread6 = new Thread(threadGroupConsumers, consumer3, "consumer3");
        //запускаем потоки (сумма товаров д.б. в конце: 90 - 90 = 0)
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        //добиваемся выполнения наших потоков прежде, чем дальше выполнять метод main
        try {
            thread1.join(producer1.getDelay()*20);
            thread2.join(producer2.getDelay()*20);
            thread3.join(producer3.getDelay()*20);
            thread4.join(consumer1.getDelay()*20);
            thread5.join(consumer2.getDelay()*20);
            thread6.join(consumer3.getDelay()*20);
            synchronized (storage) {storage.notify();} //разблокировать последний поток
        } catch (InterruptedException e) {
            System.out.println("Error in main method: " + e.getMessage());
        }
        //проверка выполнения
        System.out.println("------------------");
        if (storage.getItemsQuantity() == 0) {
            System.out.println("COMPLETED CORRECTLY");
        } else {
            System.out.println("COMPLETED INCORRECTLY");
        }
    }
}

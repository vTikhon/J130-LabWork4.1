package ru.avalon.vergentev.j130.labwork4a;

public class Main {
    public static void main(String[] args) {
        //задаём склад и стартовое кол-во товаров
        Storage storage = new Storage(30);
        //создаём поставщиков (суммарно прибавят 60)
        ThreadGroup threadGroupProducers = new ThreadGroup("Producers");
        Thread thread1 = new Thread(threadGroupProducers, new Producer(100, 1, storage), "producer1");
        Thread thread2 = new Thread(threadGroupProducers, new Producer( 200, 2, storage), "producer2");
        Thread thread3 = new Thread(threadGroupProducers, new Producer( 300, 3, storage), "producer3");
        //создаём покупателей (суммарно отнимут 90)
        ThreadGroup threadGroupConsumers = new ThreadGroup("Consumers");
        Thread thread4 = new Thread(threadGroupConsumers, new Consumer(50, -2, storage), "consumer1");
        Thread thread5 = new Thread(threadGroupConsumers, new Consumer(150, -3, storage), "consumer2");
        Thread thread6 = new Thread(threadGroupConsumers, new Consumer( 250, -4, storage), "consumer3");
        //запускаем потоки (сумма товаров д.б. в конце: 30 + 60 - 90 = 0)
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

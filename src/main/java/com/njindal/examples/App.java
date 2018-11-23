package com.njindal.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrinterThread(1));
        Thread thread2 = new Thread(new PrinterThread(2));
        Thread thread3 = new Thread(new PrinterThread(3));

        List<String> st = new ArrayList<>();
        st.add("das");
        st.add("tt");

        st.add(0,"nites");

        st.stream().forEach(System.out::println);

       // st.stream().forEach(StringBuilder::append);
        /*thread1.start();
        thread2.start();
        thread3.start();*/
    }
}


class PrinterThread implements Runnable {
    private int value;
    private static AtomicInteger count = new AtomicInteger(1);


    PrinterThread(int value) {
        this.value = value;
    }

    @Override
    public void run() {

        while(this.value != count.get()){
            try {
                wait();
            } catch (InterruptedException e) {
                continue;
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value);
        if(count.incrementAndGet() == 4){
            count.set(1);
        }
        notifyAll();
    }
}
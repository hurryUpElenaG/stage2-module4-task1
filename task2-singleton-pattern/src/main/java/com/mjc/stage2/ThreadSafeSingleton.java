package com.mjc.stage2;

public class ThreadSafeSingleton {
    // Write your code here!

    //private static ThreadSafeSingleton instance = new ThreadSafeSingleton();
    private static volatile ThreadSafeSingleton instance; //way for thread safe

    private ThreadSafeSingleton(){

    }

    public static ThreadSafeSingleton getInstance(){
        synchronized (ThreadSafeSingleton.class) {     //way for thread safe
            if (instance == null){
                instance = new ThreadSafeSingleton();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        ThreadSafeSingleton threadSafeSingleton = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton threadSafeSingleton_1 = ThreadSafeSingleton.getInstance();
        System.out.println("Is the same? " + threadSafeSingleton.equals(threadSafeSingleton_1));
    }
}

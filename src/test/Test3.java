package test;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource{
    Integer counter = 0;
    private Lock lock = new ReentrantLock();
    private AtomicInteger counter1 = new AtomicInteger(0);

    public int getNextValueAtomic() {
        counter1.getAndIncrement();
        return counter1.get();
    }


    public synchronized int getNextValueSync (){
        counter++;

        return counter;
    }


    public int getNextValueLock(){
        try {
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                counter++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //release lock
            lock.unlock();
        }

        return counter;
    }




}

class ThreadSync implements Runnable{
    String threadName;
    Resource resource;


    public ThreadSync(String name, Resource resource) {
        this.threadName = name;
        this.resource = resource;

    }

    @Override
    public void run(){
        System.out.println("Starting " + threadName);

        for(int i = 0;i<5;i++){
            System.out.println(threadName + " " + resource.getNextValueSync());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

}

class ThreadLock implements Runnable{
    String threadName;
    Resource resource;


    public ThreadLock(String name, Resource resource) {
        this.threadName = name;
        this.resource = resource;

    }

    @Override
    public void run(){
        System.out.println("Starting " + threadName);

        for(int i = 0;i<5;i++){
            System.out.println(threadName + " " + resource.getNextValueLock());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

}

class ThreadAtomic implements Runnable{
    String threadName;
    Resource resource;


    public ThreadAtomic(String name, Resource resource) {
        this.threadName = name;
        this.resource = resource;

    }

    @Override
    public void run(){
        System.out.println("Starting " + threadName);

        for(int i = 0;i<5;i++){
            System.out.println(threadName + " " + resource.getNextValueAtomic());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

}

public class Test3 {
       public static void main(String[] args) {
           Resource resource = new Resource();



           ThreadSync T1 = new ThreadSync( "Thread - Sync ", resource );
           ThreadLock T2 = new ThreadLock( "Thread - Lock ", resource );
           ThreadAtomic T3 = new ThreadAtomic( "Thread - Atomic ", resource );


           new Thread(T1).start();
           //new Thread(T2).start();
           //new Thread(T3).start();


       }

}

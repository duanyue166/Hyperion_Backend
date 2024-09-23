package org.hydra.hyperion_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadLocalTests {

    // Define a ThreadLocal variable
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    @Test
    public void test() {
        // Create and start multiple threads
        Thread thread1 = new Thread(new Task(), "Thread-1");
        Thread thread2 = new Thread(new Task(), "Thread-2");
        Thread thread3 = new Thread(new Task(), "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Task to be executed by each thread
    static class Task implements Runnable {
        @Override
        public void run() {
            // Get the current thread's name
            String threadName = Thread.currentThread().getName();

            // Set the initial value for the thread-local variable
            threadLocal.set((int) (Math.random() * 100));

            // Get and print the thread-local variable's value
            System.out.println(threadName + " initial value: " + threadLocal.get());

            // Modify the thread-local variable's value
            threadLocal.set(threadLocal.get() + 10);

            // Get and print the modified value
            System.out.println(threadName + " modified value: " + threadLocal.get());
        }
    }
}

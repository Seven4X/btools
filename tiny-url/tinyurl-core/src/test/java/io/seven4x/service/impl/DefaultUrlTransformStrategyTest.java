package io.seven4x.service.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class DefaultUrlTransformStrategyTest {


    @Test
    public void hash() {
        String longUrl = "https://stackoverflow.com/questions/9241230/what-is-murmurhash3-seed-parameter";
        DefaultUrlTransformStrategy strategy = new DefaultUrlTransformStrategy();

        Set<String> has = new CopyOnWriteArraySet<>();
        CountDownLatch latch = new CountDownLatch(3);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                int hash = strategy.hash(longUrl);
                String base64 = strategy.toBase64(hash);
                has.add(base64);
            }
            latch.countDown();
        };
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(has.toArray()));
        Assert.assertTrue(has.size() == 1);

    }

    @Test
    public void toBase64() {
        DefaultUrlTransformStrategy strategy = new DefaultUrlTransformStrategy();


        String base64 = strategy.toBase64(Integer.MAX_VALUE -1 );

        System.out.println(base64);

    }
}
package com.shapran.util;

import com.shapran.model.Detail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ThreadRobots {
    private static final Object Lock = new Object();
    private static final Random random = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadRobots.class);
    private int existingFuel;
    private int amountFuel;
    private int usedFuel;
    private int brokenMicrochips;

    public Detail create() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        startRobots();
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime) / 1000;
        return new Detail(UUID.randomUUID().toString(), LocalDate.now(),
                amountFuel, usedFuel, brokenMicrochips, usedTime);
    }

    public void startRobots() throws InterruptedException {
        Robot1 robot1 = new Robot1();
        DetailRobots robot2 = new DetailRobots();
        DetailRobots robot3 = new DetailRobots();
        Robot4 robot4 = new Robot4();
        Robot5 robot5 = new Robot5();

        Thread thread1 = new Thread(robot1, "First Robot");
        Thread thread2 = new Thread(robot2, "Second Robot");
        Thread thread3 = new Thread(robot3, "Third Robot");
        Thread thread4 = new Thread(robot4, "Fourth Robot");
        Thread thread5 = new Thread(robot5, "Fifth Robot");

        thread1.start();
        thread2.start();
        thread3.start();
        thread2.join();
        thread3.join();
        thread4.start();
        thread4.join();
        thread5.start();
        thread5.join();
    }

    public class Robot1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                LOGGER.info("{} starts working", Thread.currentThread().getName());
                int preparedFuel = new Random().nextInt(500, 1001);
                existingFuel += preparedFuel;
                amountFuel += preparedFuel;
                LOGGER.info("First robot prepared {} fuel and total amount fuel {}", preparedFuel, amountFuel);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LOGGER.info("{} finished work", Thread.currentThread().getName());
            }
        }
    }

    public class DetailRobots implements Runnable {

        @Override
        public void run() {
            int progress = 0;
            while (progress <= 100) {
                LOGGER.info("{} starts working", Thread.currentThread().getName());
                int addNumder = random.nextInt(10, 21);
                synchronized (Lock) {
                    progress += addNumder;
                }
                LOGGER.info(" {} prepared {} details, total amount = {}",
                        Thread.currentThread().getName(), addNumder, progress);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            LOGGER.info("{} finished work", Thread.currentThread().getName());
        }
    }

    public class Robot4 implements Runnable {

        @Override
        public void run() {
            int progress = 0;
            while (progress <= 100) {
                LOGGER.info("{} starts working", Thread.currentThread().getName());
                int addNumder = random.nextInt(25, 36);
                progress += addNumder;
                LOGGER.info("Fourth robot {} prepared {} details, total amount = {}",
                        Thread.currentThread().getName(), addNumder, progress);
                double probability = random.nextDouble(101);
                if (probability <= 0.3) {
                    LOGGER.info("Fourth robot {} have broken microchips and starts working from the beginning",
                            Thread.currentThread().getName());
                    progress = 0;
                    brokenMicrochips += 1;
                    continue;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            LOGGER.info("{} finished work", Thread.currentThread().getName());
        }
    }

    public class Robot5 implements Runnable {

        @Override
        public void run() {
            int progress = 0;
            while (progress <= 100) {
                LOGGER.info("Fifth robot {} starts working", Thread.currentThread().getName());
                int needFuel = random.nextInt(350, 701);
                if (existingFuel > needFuel) {
                    usedFuel += needFuel;
                    existingFuel -= needFuel;
                    LOGGER.info("Fifth robot {} used {} fuel and total amount = {}",
                            Thread.currentThread().getName(), needFuel, usedFuel);
                }
                progress += 10;
                LOGGER.info("Fifth robot {} have progress: {}", Thread.currentThread().getName(), progress);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LOGGER.info("Fifth robot {} finished work", Thread.currentThread().getName());
            }
        }
    }
}

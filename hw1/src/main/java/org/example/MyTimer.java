package org.example;

import java.util.concurrent.TimeUnit;

public class MyTimer {
    public static void waitSecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.job4j.coccurent;

public class Wget {
    public static void main(String[] args) {
        Thread loading = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            System.out.print("\rLoading : " + i  + "%");
                            Thread.sleep(1000);
                        }
                        System.out.print("\rLoaded!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        loading.start();
    }
}

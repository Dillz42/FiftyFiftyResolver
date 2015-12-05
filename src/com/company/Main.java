package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Fifty Fifty Resolver");

            ResolverThread thread = new ResolverThread("http://true5050.com/2H0Il");
            thread.start();
            synchronized (thread) {
                thread.wait();
            }
            System.out.println(thread.getFiftyfifty());
            System.out.println(thread.getAdrs1());
            System.out.println(thread.getAdrs2());
            thread.kill();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

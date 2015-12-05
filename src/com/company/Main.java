package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Fifty Fifty Resolver");

            ClipBoardManager clipBoard = new ClipBoardManager();

            ResolverThread thread = new ResolverThread(clipBoard.getClipboardContents());
            thread.start();
            synchronized (thread) {
                thread.wait();
            }
            System.out.println(thread.getFiftyfifty());
            System.out.println("\t" + thread.getAdrs1());
            System.out.println("\t" + thread.getAdrs2());
            thread.kill();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

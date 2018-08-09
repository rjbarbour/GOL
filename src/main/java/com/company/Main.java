package com.company;


import java.io.IOException;

public class Main {

    private static Controller controller;

    public static void main(String[] args) throws IOException, InterruptedException {
        controller = new Controller();
        controller.start();
    }

}

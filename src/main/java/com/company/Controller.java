package com.company;

import java.io.IOException;

public class Controller {

    private static Model model;
    private static View view;
    private static final boolean STEP_MODE = false;
    private static final int TEST_GRID = 0;

    public int start() throws IOException, InterruptedException {

        Integer waitTime = 1;
        byte[] input = new byte[2];//TODO initialize or use Scanner

        if(!STEP_MODE) {
            System.out.print("Desired intergenerational wait time (ms): ");

            System.in.read(input);
            String inputTime = new String(input);

            System.out.println("Input:  " + inputTime);

            waitTime = Integer.parseInt(inputTime.substring(0, 2));

            System.out.println("Wait Time:  " + waitTime);
        }

        model = new Model();
        model.initializeGrid();

        view = new View();
        view.displayGrid(model.getGrid());

        while(true) {

            if(STEP_MODE) {
                System.in.read(input);

                if (input[0] == "q".getBytes()[0]) {
                    System.exit(1);
                }
            }

            Thread.sleep(waitTime);

            boolean[][] nextGenerationGrid = model.processGeneration();

            view.displayGrid(nextGenerationGrid);
        }

    }

    public static int getTestGridNumber() {
        return TEST_GRID;
    }
}

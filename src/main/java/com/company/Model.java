package com.company;

import java.util.Random;

public class Model {

    private static final int GRID_SIZE = 32;
    private boolean[][] grid;

    void initializeGrid() {

        if (Controller.getTestGridNumber() == 0) {
            initializeRandomGrid();
        } else if (Controller.getTestGridNumber() == 1) {//TODO refactor to JUnit test cases
            //Bar on upper edge
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if (i == 0 && (j == 5 || j == 6 || j == 7)) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                    if (i == 0 && j == 0) {
                        grid[i][j] = true;
                    }

                    if (i == GRID_SIZE - 1 && j == GRID_SIZE - 1) {
                        grid[i][j] = true;
                    }

                }
            }
        } else if (Controller.getTestGridNumber() == 2) {
            //Bar on left edge
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if ((i == 4 || i == 5 || i == 6) && j == 0) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                }
            }
        }else if (Controller.getTestGridNumber() == 3) {
            //Bar on right edge
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if ((i == 4 || i == 5 || i == 6) && j == GRID_SIZE-1) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                }
            }
        }else if (Controller.getTestGridNumber() == 4) {
            //Bar on lower edge
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if (i == GRID_SIZE-1 && (j == 5 || j == 6 || j == 7)) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                }
            }
        }else if (Controller.getTestGridNumber() == 5) {
            //Bar in lower left corner
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if (i == GRID_SIZE-1 && (j == 31 || j == 0 || j == 1)) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                }
            }
        }else if (Controller.getTestGridNumber() == 6) {
            //Bar in upper right corner
            grid = new boolean[GRID_SIZE][GRID_SIZE];

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {

                    if (i == 0 && (j == 30 || j == 31 || j == 0)) {
                        grid[i][j] = true;
                    } else {
                        grid[i][j] = false;
                    }

                }
            }
        }
    }

    void initializeRandomGrid() {

        grid = new boolean[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Random ran = new Random();
                int x = ran.nextInt(5);//TODO: hardcoding
                if (x == 0) {
                    grid[i][j] = true;
                } else {
                    grid[i][j] = false;
                }
            }
        }
    }

    public boolean[][] processGeneration() {

        boolean[][] previousGenerationGrid = grid;

        boolean[][] nextGenerationGrid = new boolean[previousGenerationGrid.length][previousGenerationGrid.length];

        for (int i = 0; i < previousGenerationGrid.length; i++) {
            for (int j = 0; j < previousGenerationGrid[0].length; j++) {

                int numberOFNeighbours = countNumberOFNeighbours(i, j, previousGenerationGrid);

                if (numberOFNeighbours < 2) {
                    nextGenerationGrid[i][j] = false;
                }

                if (previousGenerationGrid[i][j] == true && (numberOFNeighbours == 2 || numberOFNeighbours == 3)) {
                    nextGenerationGrid[i][j] = true;
                }

                if (previousGenerationGrid[i][j] == true && numberOFNeighbours > 3) {
                    nextGenerationGrid[i][j] = false;
                }

                if (previousGenerationGrid[i][j] == false && numberOFNeighbours == 3) {
                    nextGenerationGrid[i][j] = true;
                }

            }
        }

        grid = nextGenerationGrid;

        return nextGenerationGrid;
    }

    int countNumberOFNeighbours(int i, int j, boolean[][] previousGenerationGrid) {

        int numberOFNeighbours = 0;
        int gridLengthIndex = previousGenerationGrid[0].length - 1;

        //Upper row
        int tempi = i;//TODO

        if (i - 1 >= 0) {
            //do nothing
        } else {
            i = gridLengthIndex + 1;//wrap col
        }
        //UL
        int tempj = j;
        if (j - 1 < 0) {
            j = gridLengthIndex + 1;//wrap row
        }
        if (j - 1 >= 0 && previousGenerationGrid[i - 1][j - 1] == true) {
            numberOFNeighbours++;
        }
        j = tempj;

        //UC
        if (previousGenerationGrid[i - 1][j] == true) {
            numberOFNeighbours++;
        }

        //UR
        tempj = j;
        if (j + 1 > gridLengthIndex) {
            j = -1;//wrap row
        }
        if (j + 1 <= gridLengthIndex && previousGenerationGrid[i - 1][j + 1] == true) {
            numberOFNeighbours++;
        }

        j = tempj;
        i = tempi;

        //Centre row
        //CL
        tempj = j;
        if (j - 1 < 0) {
            j = gridLengthIndex + 1;//wrap row
        }
        if (j - 1 >= 0 && previousGenerationGrid[i][j - 1] == true) {
            numberOFNeighbours++;
        }
        j = tempj;

        //CR
        tempj = j;
        if (j + 1 > gridLengthIndex) {
            j = -1;//wrap row
        }
        if (j + 1 <= gridLengthIndex && previousGenerationGrid[i][j + 1] == true) {
            numberOFNeighbours++;
        }
        j = tempj;

        //Lower row
        if (i + 1 <= gridLengthIndex) {
            //do nothing
        } else {
            i = -1;//wrap row
        }
        //LL
        tempj = j;
        if (j - 1 < 0) {
            j = gridLengthIndex + 1;//wrap row
        }
        if (j - 1 >= 0 && previousGenerationGrid[i + 1][j - 1] == true) {
            numberOFNeighbours++;
        }
        j = tempj;

        //LC
        if (previousGenerationGrid[i + 1][j] == true) {
            numberOFNeighbours++;
        }

        //LR
        tempj = j;
        if (j + 1 > gridLengthIndex) {
            j = -1;//wrap row
        }
        if (j + 1 <= gridLengthIndex && previousGenerationGrid[i + 1][j + 1] == true) {
            numberOFNeighbours++;
        }
        j = tempj;


        return numberOFNeighbours;
    }


    public static int getGridSize() {
        return GRID_SIZE;
    }

    public boolean[][] getGrid() {
        return grid;
    }
}

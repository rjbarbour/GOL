package com.company;

public class View {

    private static int iter = 1;

    void displayGrid(boolean[][] grid){

        System.out.print("\033[?1049h\033[H");

        String output = "";

        for(int i = 0; i < grid.length; i++) {
            if(i<10){output += "0";}
            output += i;
            for (int j = 0; j < grid[0].length; j++) {
//                System.out.print(grid[i][j]?"◼ ":"  ");
                output += grid[i][j]?"◼ ":"  ";
            }
            output += "|\n";
        }
//        output += '\n';
//        output += "****************************************************************\n";
        output += "Generations rendered: " + iter + '\n' + '\n' + '\n' + '\n';
        System.out.println(output);
        //System.out.print("\033[?1049l");
        iter++;

    }
}

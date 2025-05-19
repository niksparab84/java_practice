package org.nikhil.examples;

public class GameOfLife {

    public int[][] processBoard(int[][] board) {

        int length = board.length;
        System.out.println("length: " + length);

        int[][] nextBoard;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j< length; j++) {
                int n = board[i][j];

            }
        }
        // implementation goes here
        return new int[1][1];
    }

    public int getLiveNeighboursCount(int currentState, int row, int col, int[][] board) {
        int liveCount =0;
        if(row-1 >=0 && col-1 >=0 && board[row-1][col-1] == 1) {
            liveCount++;
        }
        return liveCount;
    }
}

package org.nikhil.examples.dsaChallenges;

public class GameOfLife {

    // Function to compute the next generation of the matrix
    public static int[][] findNextGen(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length; // Get the dimensions of the matrix, m is the number of rows and n is the number of columns

        // Traverse the matrix to compute the next generation
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = countLiveNeighbors(mat, i, j, m, n);

                // Apply the rules of the Game of Life
                if (mat[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        mat[i][j] = 3; // Cell will die
                    }
                } else {
                    if (liveNeighbors == 3) {
                        mat[i][j] = 2; // Cell will become alive
                    }
                }
            }
        }

        // Update the matrix to the next generation
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 2) {
                    mat[i][j] = 1; // Cell becomes alive
                } else if (mat[i][j] == 3) {
                    mat[i][j] = 0; // Cell dies
                }
            }
        }

        return mat;
    }

    // Function to count the number of live neighbors for a given cell
    // Explain: This function counts the number of live neighbors for a given cell in the matrix. It takes the matrix `mat`,
    // the coordinates of the cell `(x, y)` and the dimensions of the matrix `m` (number of rows) and `n` (number of columns) as parameters.
    // It iterates through the neighboring cells (including diagonals) and checks if they are within the bounds of the matrix.
    // If a neighboring cell is alive (represented by 1 or 3), it increments the count. Finally, it returns the total count of live neighbors.
    private static int countLiveNeighbors(int[][] mat, int x, int y, int m, int n) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            //why -1 to 1? Because we want to check the neighboring cells in all directions (up, down, left, right, and diagonals) relative to the current cell at (x, y).
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself, why? Because we only want to count the neighbors, not the cell itself.
                int newX = x + i; // what is newX? newX is the row index of the neighboring cell we are checking. It is calculated by adding the offset `i` to the current row index `x`.
                int newY = y + j;
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (mat[newX][newY] == 1 || mat[newX][newY] == 3) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*
        int[][] mat = {
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        }; */

        int[][] mat = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0}
        };

        System.out.println("Current Generation:");
        printMatrix(mat);

        mat = findNextGen(mat);

        System.out.println("Next Generation:");
        printMatrix(mat);
    }
}

// Problem Statement:
// Given a binary matrix mat[][] of size m × n, each cell represents the state of a cell in a generation:
// 1 → Live cell
// 0 → Dead cell
// The matrix represents the current generation. Compute the next generation based on the following rules:
// Any live cell with fewer than two live neighbours dies (under-population).
// Any live cell with two or three live neighbours survives to the next generation.
// Any live cell with more than three live neighbours dies (overpopulation).
// Any dead cell with exactly three live neighbours becomes a live cell (reproduction).
// Note: Neighbours are the cells connected horizontally, vertically, or diagonally to a given cell.

// Input: mat[][] = [[0, 0, 0, 0],
//                   [0, 0, 1, 0],
//                   [0, 1, 1, 0],
//                   [0, 0, 0, 0]]
//Output: mat[][] = [[0, 0, 0, 0],
//                   [0, 1, 1, 0],
//                   [0, 1, 1, 0],
//                   [0, 0, 0, 0]]
//Explanation: The cell at index {1, 1} with initial value 0 has exactly 3 live neighbours in index {1, 2}, {2, 1}, {2, 2}, thus it will be alive in next generation. Rest all three live cells have exactly 2 live neighbouring cells each, thus they all will remain alive in next generation as well. All the remaining dead cells have less than 3 live cells thus they will remain dead.
//
//Input: mat[][] = [[0, 0, 0, 0, 0],
//                  [0, 1, 1, 0, 0],
//                  [1, 1, 0, 0, 0],
//                  [0, 0, 0, 1, 0]
//                  [0, 0, 1, 0, 0]]
//Output: mat[][] = [[0, 0, 0, 0, 0],
//                   [1, 1, 1, 0, 0],
//                   [1, 1, 0, 0, 0],
//                   [0, 1, 1, 0, 0]
//                   [0, 0, 0, 0, 0]]
//Explanation: The live cells at indices {1, 1}, {1, 2}, {2, 0}, {2, 1} have either 2 or 3 live neighbours, they will remain alive. The remaining live cells at indices {3, 3} and {4, 2} have only 1 live neighbour, thus they will become dead. The dead cells at indices {1, 0}, {3, 1} and {3, 2} have exactly three live neighbours, thus they will become alive. Dead cell at index {2, 2} has 4 live neighbours and all remaining dead cells have less than 2 live neighbours, thus they will remain dead.

//Approach:
//Traverse the matrix mat[][] and for each cell find the count of live neighbors (i.e. cells with value 1 or 3).
// If the cell at index {x, y} is dead (i.e. 0) and the count of live neighbors is three, set mat[x][y] = 2, which means that the cell will become
// alive in the next generation, but is dead for now. Else if the cell is live (i.e. 1) and the count of live neighbors is not equal to two or three,
// set mat[x][y] = 3, which means that the cell will be dead in the next generation, but is alive for now. After completing the traversal, traverse the matrix mat[][] once again. Set all the cells with value 2 to 1 and cells with value 3 to 0. All the remaining cells will retain there value i.e. the cells with value 0 and 1 will remain as it is.

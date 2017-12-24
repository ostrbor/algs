import edu.princeton.cs.algs4.StdOut;

import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class Board {
    private final int[][] blocks;
    private final int[][] goal;

    public Board(int[][] blocks) {
        if (blocks == null) throw new IllegalArgumentException("Null is not allowed");
        this.blocks = blocks;
        int rowNumber = blocks.length;
        int colNumber = blocks[0].length;

        this.goal = new int[rowNumber][colNumber];

        int blockValue = 1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                this.goal[i][j] = blockValue++;
            }
        }
        this.goal[rowNumber - 1][colNumber - 1] = 0;  // last value in array must be 0
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int dim = dimension();
        int outOfPlace = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] != goal[i][j]) {
                    outOfPlace++;
                }
            }
        }
        return outOfPlace;
    }


    public int manhattan() {
        int distanceTotal = 0;
        return 0;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return Arrays.deepEquals(blocks, goal);
    }                // is this board the goal board?

    public Board twin() {
        int[][] twin = new int[0][0];
        return new Board(twin);
    }                    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        return false;
    }       // does this board equal y?

//    public Iterable<Board> neighbors()     // all neighboring boards

    public String toString() {
        String blocksRepresentation = "";
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                String blockValue = String.format("%d ", blocks[i][j]);
                blocksRepresentation = blocksRepresentation.concat(blockValue);
            }
            blocksRepresentation = blocksRepresentation.concat("\n");
        }
        String dim = String.format("%d%n", dimension());
        return dim.concat(blocksRepresentation);
    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 0}};
        Board board = new Board(a);
        assert board.isGoal();
        System.out.print(board);

        int[][] b = {{1,3}, {3,0}};
        Board board1 = new Board(b);
        assert 1 == board1.hamming();
    } // unit tests (not graded)
}

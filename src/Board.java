import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class Board {
    private final int[][] blocks;

    public Board(int[][] blocks) {
        if (blocks == null) throw new IllegalArgumentException("Null is not allowed");
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int outOfPlace = 0;
        int dim = dimension();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) continue;  // don't count 0
                int expected = (i * dim) + (j + 1);
                if (blocks[i][j] != expected) outOfPlace++;
            }
        }
        return outOfPlace;
    }

    public int manhattan() {
        int distanceTotal = 0;
        int distanceCurrent = 0;
        int dim = dimension();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int actual = blocks[i][j];
                if (actual == 0) continue;  // don't count 0
                int expected = (i * dim) + (j + 1);
                if (actual != expected) {
                    int rowIndex = actual / dim;
                    int colIndex = actual % dim - 1;
                    int rowDistance = Math.abs((i - rowIndex));
                    int colDistance = Math.abs((j - colIndex));
                    distanceCurrent = rowDistance + colDistance;
                    distanceTotal += distanceCurrent;
                }
            }
        }
        return distanceTotal;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }


    public Board twin() {
        int rowNumber = blocks.length;
        int colNumber = blocks[0].length;

        int randRowIndex = StdRandom.uniform(rowNumber);
        int randColIndex = StdRandom.uniform(colNumber);
        int randomBlock = blocks[randRowIndex][randColIndex];

        int randRowIndexPair;
        int randColIndexPair;

        do {
            randRowIndexPair = StdRandom.uniform(rowNumber);
            randColIndexPair = StdRandom.uniform(colNumber);
        }
        while ((randRowIndex == randRowIndexPair) && (randColIndex == randColIndexPair));

        int randomBlockPair = blocks[randRowIndexPair][randColIndexPair];


        int[][] newBlocks = new int[rowNumber][colNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                newBlocks[i][j] = blocks[i][j];
            }
        }
        newBlocks[randRowIndex][randColIndex] = randomBlockPair;
        newBlocks[randRowIndexPair][randColIndexPair] = randomBlock;

        return new Board(newBlocks);
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;

        return Arrays.deepEquals(this.blocks, that.blocks);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();

        int blankRow = -1;
        int blankCol = -1;
        boolean found = false;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (blankRow == 0) {

        }

        return stack;
    }

    public String toString() {
        String blocksRepresentation = "";
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                String blockValue = String.format("%d ", blocks[i][j]);
                blocksRepresentation += blockValue;
            }
            blocksRepresentation += "\n";
        }
        String dim = String.format("%d%n", dimension());
        return dim.concat(blocksRepresentation);
    }

    public static void main(String[] args) {
        int[][] b1 = {
                {1, 3},
                {2, 0}};
        Board bd1 = new Board(b1);
        assert 2 == bd1.hamming();

        int[][] b2 = {
                {0, 2},
                {3, 1}};
        Board bd2 = new Board(b2);
        assert 1 == bd2.hamming();

        int[][] a = {
                {1, 2},
                {3, 0}};
        Board board = new Board(a);
        assert board.isGoal();
        System.out.print(board);


        int[][] c = {
                {4, 2, 3},
                {1, 5, 6},
                {7, 8, 0}};
        Board board2 = new Board(c);
        assert 2 == board2.manhattan();

        int[][] c1 = {
                {7, 2, 3},
                {4, 5, 6},
                {1, 8, 0}};
        Board board3 = new Board(c1);
        assert 4 == board3.manhattan();

        int[][] c2 = {
                {6, 2, 3},
                {4, 5, 1},
                {7, 8, 0}};
        Board board4 = new Board(c2);
        assert 6 == board4.manhattan();

        int[][] c3 = {
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1}};
        Board board5 = new Board(c3);
        assert 4 == board5.manhattan();

        int[][] d = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};
        Board board1 = new Board(d);
        Board boardTwin = board1.twin();
        StdOut.print(boardTwin);
    }
}


import java.lang.IllegalArgumentException;

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
        if (hamming() == 0) return true;
        else return false;
    }

    public Board twin() {
        int[][] twin = new int[0][0];
        return new Board(twin);
    }                    // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        // TODO: check if it's correct
        return this == y;
    }

//    public Iterable<Board> neighbors()     // all neighboring boards

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
    }
}

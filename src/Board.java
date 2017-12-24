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
                int expected = (i + 1) * (j + 1);
                if (blocks[i][j] != expected) outOfPlace++;
            }
        }
        return outOfPlace;
    }


    public int manhattan() {
        int distanceTotal = 0;
        return 0;
    }                 // sum of Manhattan distances between blocks and goal

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

        int[][] b = {{1, 3}, {3, 0}};
        Board board1 = new Board(b);
        assert 1 == board1.hamming();
    } // unit tests (not graded)
}

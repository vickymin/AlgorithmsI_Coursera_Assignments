import java.util.Arrays;

public class Board {

    private final int[][] blocks;
    private final int N;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N][];
        for (int i = 0; i < N; i++) {
            this.blocks[i] = Arrays.copyof(blocks[i], N)
        }
    }

    // board dimension N
    public int dimension() {
        return this.N;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N && i+j < 2*N-2; j++) {
                if (blocks[i][j] != i*N + j + 1) {
                   count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != 0) {
                    int row = (blocks[i][j] - 1) / N;
                    int col = (blocks[i][j] - 1) % N;
                    count += (Math.abs(row - i) + Math.abs(col - j));
                }
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return humming() = 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twin = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twin[i][j] = blocks[i][j];
            }
        }
        int temp;
        if (blocks[0][0] != 0 && blocks[0][1] != 0) {
            temp = block[0][0];
            twin[0][0] = twin[0][1];
            twin[0][1] = temp;
        } else {
            temp = twin[1][0];
            twin[1][0] = twin[1][1];
            twin[1][1] = temp;
        }
        return new Board(twin);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board)y;
        if (N != that.N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != that[i][j]) {
                    retrun false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> boards = new Queue<Board>();
        int temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    if (i > 0) {
                        int[][] left = copyBoard();
                        left[i][j] = blocks[i-1][j];
                        left[i-1][j] = 0;
                        boards.enqueue(new Board(left));
                    }
                    if (i < N-1) {
                        int[][] right = copyBoard();
                        right[i][j] = blocks[i+1][j];
                        left[i+1][j] = 0;
                        boards.enqueue(new Board(right));
                    }
                    if (j > 0) {
                        int[][] up = copyBoard();
                        up[i][j] = blocks[i][j-1];
                        up[i][j-1] = 0;
                        boards.enqueue(new Board(up));
                    }
                    if (j < N-1) {
                        int[][] down = copyBoard();
                        down[i][j] = blocks[i][j+1];
                        down[i][j+1] = 0;
                        boards.enqueue(new Board(down));
                    }
                    return boards;
                }
            }
        }
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int[][] copyBoard() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}

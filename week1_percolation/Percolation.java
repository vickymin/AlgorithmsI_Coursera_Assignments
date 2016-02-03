import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int N;
  private WeightedQuickUnionUF uf;
  private boolean[] status;
  private boolean[] connectedToBottom;
  // create N-by-N grid, with all sites blocked
  public Percolation(int n) {
    N = n;
    if (N <= 0) {
      throw new IllegalArgumentException();
    }
    uf = new WeightedQuickUnionUF(N*N+1);
    status = new boolean[N*N + 1];
    status[0] = true;
    connectedToBottom = new boolean[N*N + 1];
    for (int k = (N-1)*N + 1; k <= N*N; k++) {
      connectedToBottom[k] = true;
    }
  }

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    if (i <= 0 || i > N) {
      throw new IndexOutOfBoundsException("row index " + i + " out of bounds");
    }
    if (j <= 0 || j > N) {
      throw new IndexOutOfBoundsException("row index " + j + " out of bounds");
    }
    status[(i-1)*N+j] = true;
    if (i == 1) {
      if (connectedToBottom[uf.find((i-1)*N+j)] || connectedToBottom[uf.find(0)]) {
        uf.union((i-1)*N+j, 0);
        connectedToBottom[uf.find((i-1)*N+j)] = true;
      } else {
        uf.union((i-1)*N+j, 0);
      }
    }
    if (i != 1 && status[(i-2)*N+j]) {
      if (connectedToBottom[uf.find((i-1)*N+j)] || connectedToBottom[uf.find((i-2)*N+j)]) {
        uf.union((i-1)*N+j, (i-2)*N+j);
        connectedToBottom[uf.find((i-1)*N+j)] = true;
      } else {
        uf.union((i-1)*N+j, (i-2)*N+j);
      }
    }
    if (i != N && status[i*N+j]) {
      if (connectedToBottom[uf.find((i-1)*N+j)] || connectedToBottom[uf.find(i*N+j)]) {
        uf.union((i-1)*N+j, i*N+j);
        connectedToBottom[uf.find((i-1)*N+j)] = true;
      } else {
        uf.union((i-1)*N+j, i*N+j);
      }
    }
    if (j != 1 && status[(i-1)*N+(j-1)]) {
      if (connectedToBottom[uf.find((i-1)*N+j)] || connectedToBottom[uf.find((i-1)*N+(j-1))]) {
        uf.union((i-1)*N+j, (i-1)*N+(j-1));
        connectedToBottom[uf.find((i-1)*N+j)] = true;
      } else {
        uf.union((i-1)*N+j, (i-1)*N+(j-1));
      }
    }
    if (j != N && status[(i-1)*N+(j+1)]) {
      if (connectedToBottom[uf.find((i-1)*N+j)] || connectedToBottom[uf.find((i-1)*N+(j+1))]) {
        uf.union((i-1)*N+j, (i-1)*N+(j+1));
        connectedToBottom[uf.find((i-1)*N+j)] = true;
      } else {
        uf.union((i-1)*N+j, (i-1)*N+(j+1));
      }
    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    if (i <= 0 || i > N) {
      throw new IndexOutOfBoundsException("row index " + i + " out of bounds");
    }
    if (j <= 0 || j > N) {
      throw new IndexOutOfBoundsException("row index " + j + " out of bounds");
    }
    return status[(i-1)*N+j];
  }

  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    if (i <= 0 || i > N) {
      throw new IndexOutOfBoundsException("row index " + i + " out of bounds");
    }
    if (j <= 0 || j > N) {
      throw new IndexOutOfBoundsException("row index " + j + " out of bounds");
    }
    return uf.connected((i-1)*N+j, 0);
  }

  // does the system percolate?
  public boolean percolates() {
    return connectedToBottom[uf.find(0)];
  }

  // test client (optional)
  public static void main(String[] args) {
    return;
  }
}

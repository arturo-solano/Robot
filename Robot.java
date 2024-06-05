package robot;

import java.util.LinkedList;
import java.util.Queue;

public class Robot {
    private final int rows;
    private final int cols;
    private final int[][] board;
    private final boolean[][] visited;
    private final int[] rowDirections = {0, 1}; // Right and Down
    private final int[] colDirections = {1, 0}; // Right and Down

    public Robot(int[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];
    }

    public int shortestPath() {
        if (board[0][0] == 1 || board[rows - 1][cols - 1] == 1) {
            return -1; // Start or end is blocked
        }

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(0, 0, 0)); // Starting point
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // If reached the bottom-right corner
            if (current.row == rows - 1 && current.col == cols - 1) {
                return current.distance;
            }

            // Explore neighbors
            for (int i = 0; i < 2; i++) {
                int newRow = current.row + rowDirections[i];
                int newCol = current.col + colDirections[i];

                if (isValid(newRow, newCol)) {
                    queue.add(new Cell(newRow, newCol, current.distance + 1));
                    visited[newRow][newCol] = true;
                }
            }
        }

        return -1; // No path found
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] == 0 && !visited[row][col];
    }

    private static class Cell {
        int row, col, distance;

        Cell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 0, 1, 0},
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };

        Robot robot = new Robot(board);
        int result = robot.shortestPath();
        if (result != -1) {
            System.out.println("El camino más corto tiene " + result + " pasos.");
        } else {
            System.out.println("No se encontró un camino.");
        }
    }
}

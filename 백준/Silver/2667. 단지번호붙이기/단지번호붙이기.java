import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> queue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int bfs() {
        int home = 1;           // 인접한 집의 수

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = cur.x + pos[i][0];
                int newY = cur.y + pos[i][1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                if (board[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new Pair(newX, newY));
                    home++;
                }
            }
        }

        return home;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];
        queue = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.add(new Pair(i, j));
                    result.add(bfs());
                }
            }
        }

        Collections.sort(result);

        int size = result.size();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(result.get(i)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
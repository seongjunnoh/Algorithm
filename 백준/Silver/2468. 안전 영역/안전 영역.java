import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> queue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static void bfs(int rain) {
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = cur.x + pos[i][0];
                int newY = cur.y + pos[i][1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                if (!visited[newX][newY] && board[newX][newY] > rain) {
                    visited[newX][newY] = true;
                    queue.add(new Pair(newX, newY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (maxHeight < board[i][j]) {
                    maxHeight = board[i][j];
                }
            }
        }

        int max = 0;            // 정답
        for (int rain = 0; rain <= maxHeight; rain++) {
            visited = new boolean[n][n];
            queue = new LinkedList<>();
            int count = 0;          // 현재 rain 값에 대한 안전한 영역의 개수

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] > rain && !visited[i][j]) {
                        // 해당 구역이 현재 rain값에 대해 물에 잠기지 않았고, 방문하지 않은 지역이면
                        visited[i][j] = true;
                        queue.add(new Pair(i, j));
                        bfs(rain);
                        count++;
                    }
                }
            }

            max = Math.max(max, count);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
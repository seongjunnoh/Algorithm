import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> queue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int bfs() {
        int rec = 1;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newRow = cur.row + pos[i][0];
                int newCol = cur.col + pos[i][1];
                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m) continue;
                if (board[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.add(new Pair(newRow, newCol));
                    rec++;
                }
            }
        }

        return rec;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        queue = new LinkedList<>();
        // result의 size = 영역 개수 , result의 각각의 값 = 각 영역의 넓이
        LinkedList<Integer> result = new LinkedList<>();

        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int col1 = Integer.parseInt(st.nextToken());
            int row1 = Integer.parseInt(st.nextToken());
            int col2 = Integer.parseInt(st.nextToken());
            int row2 = Integer.parseInt(st.nextToken());
            for (int row = n - 1 - row1; row > n - 1 - row2; row--) {
                for (int col = col1; col < col2; col++) {
                    board[row][col] = 1;            // 1 : 직사각형 내부
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.add(new Pair(i, j));
                    int rec = bfs();
                    result.offer(rec);
                }
            }
        }

        Collections.sort(result);       // 결과 오름차순 정렬

        int size = result.size();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(result.poll());
            if (i != size - 1) {
                sb.append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
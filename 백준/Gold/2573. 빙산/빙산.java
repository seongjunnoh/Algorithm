import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] board;
    static int[][] temp;
    static Queue<Pair> queue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean checkBingsan(int x, int y) {
        // (x,y) 와 인접한 빙산을 모두 방문 처리
        // -> 빙산 중 방문 처리가 안된 빙산이 없으면 true, 있으면 false return
        Queue<Pair> bingsan = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        bingsan.add(new Pair(x, y));
        visited[x][y] = true;

        while (!bingsan.isEmpty()) {
            Pair cur = bingsan.poll();
            for (int i = 0; i < 4; i++) {
                int nX = cur.x + pos[i][0];
                int nY = cur.y + pos[i][1];
                if (board[nX][nY] > 0 && !visited[nX][nY]) {
                    visited[nX][nY] = true;
                    bingsan.add(new Pair(nX, nY));
                }
            }
        }

        // 빙산들 방문 처리 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0 && !visited[i][j]) return false;
            }
        }

        return true;
    }

    static boolean isNoBingsan() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) return false;           // 빙산이 있음
            }
        }

        return true;           // 빙산이 없음 -> 0 이 정답
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        temp = new int[n][m];
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > 0) {
                    queue.add(new Pair(i, j));          // 현재 빙산인 부분 add
                }
            }
        }

        // 예외처리 : 빙산이 처음부터 하나도 없을 경우
        if (isNoBingsan()) {
            bw.write("0");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int year = 1;
        while (true) {
            while (!queue.isEmpty()) {
                Pair cur = queue.poll();        // 현재 빙산 위치
                int count = 0;                  // 현재 빙산 위치와 맞닿아있는 바다 수
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x + pos[i][0];
                    int nY = cur.y + pos[i][1];
                    if (board[nX][nY] == 0) count++;
                }

                // 빙산 높이 update
                temp[cur.x][cur.y] = board[cur.x][cur.y] - count;
                if (temp[cur.x][cur.y] < 0) temp[cur.x][cur.y] = 0;
            }

            // temp의 빙산을 board로 옮김
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = temp[i][j];
                    if (board[i][j] > 0) {
                        queue.add(new Pair(i, j));          // 1년 후 빙산인 부분 queue에 add
                    }
                }
            }

            // 1년 후 빙산이 없을 경우
            if (isNoBingsan()) {
                bw.write("0");
                bw.flush();
                bw.close();
                br.close();
                return;
            }

            // board의 빙산이 분리된 경우 체크
            if (!checkBingsan(queue.peek().x, queue.peek().y)) {
                bw.write(String.valueOf(year));
                bw.flush();
                bw.close();
                br.close();
                return;
            }

            year++;
        }
    }
}
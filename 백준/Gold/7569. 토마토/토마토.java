import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pair {
        int x;
        int y;
        int z;
        int time;
        Pair (int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }

    static int n, m, h;
    static int[][][] board;
    static int[][][] time;
    static int[] posX = {1, -1, 0, 0, 0, 0};
    static int[] posY = {0, 0, 1, -1, 0, 0};
    static int[] posZ = {0, 0, 0, 0, -1, 1};
    static Queue<Pair> queue;

    static int bfs() {
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int nowX = cur.x;
            int nowY = cur.y;
            int nowZ = cur.z;
            int nowDay = cur.time;
            for (int i = 0; i < 6; i++) {
                int newX = nowX + posX[i];
                int newY = nowY + posY[i];
                int newZ = nowZ + posZ[i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || newZ < 0 || newZ >= h) continue;
                if (board[newZ][newX][newY] == 0) {
                    board[newZ][newX][newY] = 1;
                    queue.add(new Pair(newX, newY, newZ, nowDay + 1));
                    time[newZ][newX][newY] = nowDay + 1;
                }
            }
        }

        // while loop 끝난후 time 배열 돌면서 -> -1 있고, 그자리의 토마토가 안익었으면 -1이 정답 , 아니면 가장 큰 정수가 정답
        int maxTime = 0;
        for (int z = 0; z < h; z++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (time[z][x][y] == -1 && board[z][x][y] == 0) return -1;
                    maxTime = Math.max(maxTime, time[z][x][y]);
                }
            }
        }

        return maxTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][n][m];
        time = new int[h][n][m];
        queue = new LinkedList<>();
        boolean isMatch = true;

        for (int z = 0; z < h; z++) {
            for (int x = 0; x < n; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < m; y++) {
                    board[z][x][y] = Integer.parseInt(st.nextToken());
                    time[z][x][y] = -1;
                    if (board[z][x][y] == 1) {
                        queue.add(new Pair(x, y, z, 0));
                        time[z][x][y] = 0;
                    }
                    if (board[z][x][y] == 0) {
                        isMatch = false;
                    }
                }
            }
        }

        if (isMatch) {
            // 입력이 1, -1 로만 구성 -> 이미 모든 토마토가 익은 상태
            bw.write("0");
        } else {
            bw.write(String.valueOf(bfs()));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
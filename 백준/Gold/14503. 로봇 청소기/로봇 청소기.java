import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n, m;
    static int[][] map;         // 0 : 빈칸, 1 : 벽
    static boolean[][] clean;   // true : 청소 O, false : 청소 X
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int result = 0;

    static void play(int x, int y, int d) {

        if (!clean[x][y]) {
            clean[x][y] = true;
            result++;
        }

        boolean hasToClean = false;
        for (int i = 0; i < 4; i++) {
            int nX = x + pos[i][0];
            int nY = y + pos[i][1];
            if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
            if (map[nX][nY] == 0 && !clean[nX][nY]) hasToClean = true;
        }

        if (!hasToClean) {
            // 현재 칸 주변에 청소되지 않은 빈 칸이 없는 경우
            int nX = 0, nY = 0;
            if (d == 0) {
                nX = x + 1;
                nY = y;
            } else if (d == 1) {
                nX = x;
                nY = y - 1;
            } else if (d == 2) {
                nX = x - 1;
                nY = y;
            } else {
                nX = x;
                nY = y + 1;
            }

            // 후진할 수 없는 경우
            if (nX < 0 || nX >= n || nY < 0 || nY >= m) return;
            if (map[nX][nY] == 1) return;

            // 후진할 수 있는 경우
            if (map[nX][nY] == 0) play(nX, nY, d);

        } else {
            // 현재 칸 주변에 청소되지 않은 빈칸이 있는 경우
            int nX = 0, nY = 0, nD = d;
            for (int i = 0; i < 4; i++) {
                nD = nD - 1;
                if (nD == -1) nD = 3;

                if (nD == 0) {
                    nX = x - 1;
                    nY = y;
                } else if (nD == 1) {
                    nX = x;
                    nY = y + 1;
                } else if (nD == 2) {
                    nX = x + 1;
                    nY = y;
                } else {
                    nX = x;
                    nY = y - 1;
                }

                if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
                if (map[nX][nY] == 0 && !clean[nX][nY]) {
                    play(nX, nY, nD);
                    return;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        clean = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play(r, c, d);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 5 14503번 로봇 청소기
 *
 *
 */

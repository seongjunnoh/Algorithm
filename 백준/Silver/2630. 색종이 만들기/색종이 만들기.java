import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n;
    static int[][] board;
    static int white = 0;
    static int blue = 0;

    static void recursive(int x, int y, int size) {
        // 현재 모든 색종이의 색깔이 같은지 확인
        boolean isWhite = true;
        boolean isBlue = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] != 0) isWhite = false;
                if (board[i][j] != 1) isBlue = false;
            }
        }

        if (isWhite) {
            // 현재 모든 색종이가 0임
            white++;
            return;
        }
        if (isBlue) {
            // 현재 모든 색종이가 1임
            blue++;
            return;
        }

        // 현재 색종이의 색깔이 섞여 있는 경우
        recursive(x, y, size / 2);
        recursive(x, y + size / 2, size / 2);
        recursive(x + size / 2, y, size / 2);
        recursive(x + size / 2, y + size / 2, size / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0, 0, n);

        bw.write(white + "\n" + blue);
        bw.flush();
        bw.close();
        br.close();
    }
}
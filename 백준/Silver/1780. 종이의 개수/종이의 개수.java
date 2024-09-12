import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;
    static int r1 = 0;
    static int r2 = 0;
    static int r3 = 0;          // r1 : -1, r2 : 0, r3 : 1 정답

    static void recursive(int x, int y, int size, int target) {

        // size == 1 인 경우
        if (size == 1) {
            if (target == -1 && board[x][y] == -1) r1++;
            else if (target == 0 && board[x][y] == 0) r2++;
            else if (target == 1 && board[x][y] == 1) r3++;
            return;
        }

        // 현재 체크할 종이의 수가 모두 같은지 확인
        boolean isEnd = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] != target) {
                    isEnd = false;
                }
            }
        }

        if (isEnd) {
            if (target == -1) r1++;
            else if (target == 0) r2++;
            else if (target == 1) r3++;
            return;
        }

        // 같지 않으면 -> 종이를 자르고 다시 recursive 호출
        recursive(x, y, size / 3, target);
        recursive(x, y + size / 3, size / 3, target);
        recursive(x, y + size / 3 * 2, size / 3, target);

        recursive(x + size / 3, y, size / 3, target);
        recursive(x + size / 3, y + size / 3, size / 3, target);
        recursive(x + size / 3, y + size / 3 * 2, size / 3, target);

        recursive(x + size / 3 * 2, y, size / 3, target);
        recursive(x + size / 3 * 2, y + size / 3, size / 3, target);
        recursive(x + size / 3 * 2, y + size / 3 * 2, size / 3, target);
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

        recursive(0, 0, n, -1);
        recursive(0, 0, n, 0);
        recursive(0, 0, n, 1);

        bw.write(String.valueOf(r1) + "\n");
        bw.write(String.valueOf(r2) + "\n");
        bw.write(String.valueOf(r3));

        bw.flush();
        bw.close();
        br.close();
    }
}
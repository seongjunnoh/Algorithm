import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;
    static StringBuilder sb;

    static void recursive(int x, int y, int size) {
        // 현재 사각형이 모두 0 또는 1 인지 확인
        boolean isZero = true;
        boolean isOne = true;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] != 0) isZero = false;
                if (board[i][j] != 1) isOne = false;
            }
        }

        if (isZero) {
            // 모두 0인 경우
            sb.append(0);
            return;
        }
        if (isOne) {
            // 모두 1인 경우
            sb.append(1);
            return;
        }

        // 현재 사각형의 숫자가 모두 같지 않은 경우
        sb.append("(");
        recursive(x, y, size / 2);
        recursive(x, y + size / 2, size / 2);
        recursive(x + size / 2, y, size / 2);
        recursive(x + size / 2, y + size / 2, size / 2);
        sb.append(")");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        recursive(0, 0, n);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

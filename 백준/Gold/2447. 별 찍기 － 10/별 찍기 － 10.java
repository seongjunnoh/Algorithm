import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n;
    static char[][] star;

    static void recursive(int x, int y, int size) {
        if (size == 1) {
            star[x][y] = '*';
            return;
        }

        recursive(x, y, size / 3);
        recursive(x, y + size / 3, size / 3);
        recursive(x, y + size / 3 * 2, size / 3);

        recursive(x + size / 3, y, size / 3);
        recursive(x + size / 3, y + size / 3 * 2, size / 3);

        recursive(x + size / 3 * 2, y, size / 3);
        recursive(x + size / 3 * 2, y + size / 3, size / 3);
        recursive(x + size / 3 * 2, y + size / 3 * 2, size / 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        star = new char[n][n];

        // star를 ' ' 로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                star[i][j] = ' ';
            }
        }

        recursive(0, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(star[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
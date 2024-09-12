import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static int n, r, c;
    static int num = 0;
    static int result = 0;

    static void visit(int pow, int x, int y) {

        if (pow == 0) return;

        int half = (int) Math.pow(2, pow - 1);

        if (r < x + half && c < y + half) {
            visit(pow - 1, x, y);       // 왼쪽 상단
        } else if (r < x + half && c >= y + half) {
            result += half * half;
            visit(pow - 1, x, y + half);        // 오른쪽 상단
        } else if (r >= x + half && c < y + half) {
            result += 2 * half * half;
            visit(pow - 1, x + half, y);        // 왼쪽 하단
        } else {
            result += 3 * half * half;
            visit(pow - 1, x + half, y + half);        // 오른쪽 하단
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visit(n, 0, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] saved = new long[91];
        saved[1] = 1;
        saved[2] = 1;
        for (int i = 3; i <= n; i++) {
            saved[i] = saved[i - 2] + saved[i - 1];
        }

        System.out.println(saved[n]);
        br.close();
    }
}

/**
 * 실버 3 2193 이친수
 *
 * n=1 -> 1 => 1
 * n=2 -> 10 => 1
 * n=3 -> 101, 100 => 2
 * n=4 -> 1010, 1001, 1000 => 3
 * n=5 -> 10101, 10100, 10010, 10001, 10000 => 5
 * n=6 -> 1 + 2 + 2 + 1 + 2 => 8
 * n=7 -> 2 + 1 + 2 + 1 + 2 + 2 + 1 + 2 => 13
 * 
 * saved 배열 자료형 long 으로 해야함
 */
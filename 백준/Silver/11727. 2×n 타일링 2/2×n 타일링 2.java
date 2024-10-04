import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] saved = new int[1001];
        saved[1] = 1;
        saved[2] = 3;
        for (int i = 3; i <= n; i++) {
            saved[i] = (saved[i - 2] * 2 + saved[i - 1]) % 10007;
        }

        System.out.println(saved[n]);
        br.close();
    }
}

/**
 * 실버 3 11727 2*n 타일링 2
 * n=1 -> 1
 * n=2 -> 2*2 0 : 2 + 2*2 1 : 1 => 3
 * n=3 -> 2*2 0 : 3 + 2*2 1 : 2(= 1*2) => 5
 * n=4 -> 2*2 0 : 5 + 2*2 1 : 5(= 2*2 + 1) + 2*2 2 : 1 => 11
 * n=5 -> 2*2 0 : 8 + 2*2 1 : 10(= 2*3 + 2*2) + 2*2 2 : 3(= 1*3) => 21
 *
 *
 */

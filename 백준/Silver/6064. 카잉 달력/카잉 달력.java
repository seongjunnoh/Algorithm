import java.io.*;
import java.util.*;

class Solution_6064 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int k = x;          // k = 0이상의 정수 * m + x
            boolean flag = false;
            int lcm = LCM(m, n);        // m, n 의 최소공배수
            while (k <= lcm) {
                int nY = k % n;         // 현재 k값으로 계산한 y 값 = nY
                if (nY == 0) nY = n;

                if (nY == y) {
                    flag = true;
                    break;
                }

                k += m;
            }

            if (flag) {
                sb.append(k).append("\n");
            } else {
                sb.append("-1").append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    int LCM(int m, int n) {     // m, n 의 최소공배수 계산
        return (m * n) / GCD(m, n);         // 최소공배수 = 두 수의 곱 / 두 수의 최대공약수
    }

    int GCD(int m, int n) {     // m, n 의 최대공약수 계산 -> 유클리드 호제법
        int a = Math.max(m, n);
        int b = Math.min(m, n);
        int r = a % b;

        if (r == 0) return b;

        return GCD(b, r);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_6064 s = new Solution_6064();
        s.solution();
    }
}

/**
 * 시간복잡도 계산 ?? -> 최대 m * n 번 반복 -> 시간 초과
 * ---------------------------------------------------
 *
 *
 */
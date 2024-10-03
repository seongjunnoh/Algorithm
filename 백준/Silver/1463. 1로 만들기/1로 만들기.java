import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int[] saved;          // 메모이제이션 -> n일때 1로 만들 수 있는 최소 count 값

    static int dp(int n) {
        if (n == 1) return 0;

        if (saved[n] == 0) {
            // 1,2,3 번 연산 모두 가능한 경우 -> 각각의 경우 중 최소값으로 saved 지정
            if (n % 6 == 0) {
                saved[n] = Math.min(dp(n - 1), Math.min(dp(n / 3), dp(n / 2))) + 1;
            }
            // 1, 3 번 연산 가능한 경우
            else if (n % 3 == 0) {
                saved[n] = Math.min(dp(n / 3), dp(n - 1)) + 1;
            }
            // 2, 3 번 연산 가능한 경우
            else if (n % 2 == 0) {
                saved[n] = Math.min(dp(n / 2), dp(n - 1)) + 1;
            }
            // 3번 연산만 가능한 경우
            else {
                saved[n] = dp(n - 1) + 1;
            }
        }

        // 이미 메모이제이션이 된 경우
        return saved[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        saved = new int[n + 1];
        saved[1] = 0;

        System.out.println(dp(n));
    }
}

/**
 * 실버 3 1463번 1로 만들기
 *
 *
 */

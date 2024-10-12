import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static Integer[] values;
    static int[][] saved;       // saved[index][remain] : index ~ n-1 까지의 동전으로 remain을 구성하는 방법의 수

    static int dp(int index, int remain) {
        if (remain == 0) return 1;
        if (index == n) return 0;
        if (saved[index][remain] != -1) return saved[index][remain];

        // values[index]를 포함하지 않는 경우
        saved[index][remain] = dp(index + 1, remain);

        // values[index]를 포함하는 경우 (포함할 수 있다면)
        if (remain >= values[index]) {
            saved[index][remain] += dp(index, remain - values[index]);
        }

        return saved[index][remain];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        values = new Integer[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        // values를 정렬하는 과정이 없어도 가능할 듯

        saved = new int[n][k+1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(saved[i], -1);          // 초기화 = -1 (아직 게산하지 않은 값을 -1로 초기화)
        }

        System.out.println(dp(0, k));
        br.close();
    }
}

/**
 * 골드 4 2293 동전 1
 *
 * 사용한 동전의 구성이 달라야 함
 *
 * 10 -> 5 2개, 5 1개 2 2개 1 1개, 5 1개 2 1개 1 3개, 2 5개, ,,,
 * 현재 고려하는 동전 최대한 사용 & 나머지 금액은 이전 메모이제이션 결과 이용
 *
 */

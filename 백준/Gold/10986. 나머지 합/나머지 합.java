import java.io.*;
import java.util.*;

class Solution_10986 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] sum = new long[n + 1];       // long[0] = 0
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 누적합을 m으로 나눈 나머지를 기준으로 분류
        int[] count = new int[m];       // count[i] : m으로 나눈 나머지가 i인 누적합의 개수
        for (int i = 1; i <= n; i++) {
            int remain = (int)(sum[i] % m);
            count[remain]++;
        }

        long total = 0;      // 정답
        for (int i = 0; i < m; i++) {
            if (i == 0) total += count[i];

            total += comb(count[i], 2);
        }

        System.out.println(total);
        br.close();
    }

    long comb(int n, int k) {       // n choose k 를 반환
        return (long) n * (n - 1) / k;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_10986 s = new Solution_10986();
        s.solution();
    }
}

/**
 * dp 로 i ~ j까지의 구간 합 구하면 n제곱 걸린다 -> 시간초과
 * 1 ~ i 까지의 누적합 구하고, i ~ j 까지의 구간 합은 s[j] - s[i-1] 로 구하자 -> 이래도 모든 구간 합을 구하려면 n제곱 걸린다
 *
 * 일단 누적합을 완전 탐색하면서 m으로 나눈 나머지 구하기
 * -> 나머지가 0 : 1 ~ j 까지의 누적구간이 정답
 * -> 나머지가 0이 아님 : 저장 -> 완전탐색 후 저장된 구간들의 차이가 정답이다
 * 이러면 한번의 완전탐색으로 가능
 */
import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[n];
        sum[0] = input[0];
        for (int i = 1; i < n; i++) {
            int oldMaxSum = 0;
            for (int j = 0; j < i; j++) {
                if (input[j] >= input[i]) continue;
                oldMaxSum = Math.max(oldMaxSum, sum[j]);
            }

            sum[i] = oldMaxSum + input[i];
        }

        Arrays.sort(sum);

        System.out.println(sum[n - 1]);
        br.close();
    }
}

/**
 * 실버 2 11055 가장 큰 증가하는 부분 수열
 *
 * 1 -> 1
 * 1 100 -> 101
 * 1 100 -> 101
 * 1 100 -> 101
 * 1 2 50 60 -> 113
 * ,,
 *
 * sum[i] : i번째 항이 최대값인 부분증가수열의 누적합
 * 1, 101, 3, 53, 113, ,,,
 *
 */

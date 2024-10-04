import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean isPlus = false;
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            if (input[i] > 0) isPlus = true;
        }

        // 예외처리
        if (!isPlus) {
            Arrays.sort(input);
            System.out.println(input[n - 1]);
            br.close();
            return;
        }

        int[][] sum = new int[n][2];         // 메모이제이션 , [][0] : 해당 input을 포함하지 않은 경우, [][1] : 해당 input을 포함한 경우
        sum[0][1] = input[0];

        for (int i = 1; i < n; i++) {
            sum[i][0] = Math.max(sum[i-1][0], sum[i-1][1]);
            // i번째를 포함한 경우는 i-1번째를 포함한 경우 + input[i] (=> 연속된 수이므로) or input[i]
            sum[i][1] = Math.max(input[i], sum[i-1][1] + input[i]);
        }

        System.out.println(Math.max(sum[n-1][0], sum[n-1][1]));
        br.close();
    }
}

/**
 * 실버 2 1912 연속합
 *
 * sum[i] == 0~i 까지의 수 중 가장 큰 연속합 == sum[i-1] + input[i] or sum[i-1]
 * -> 이럴경우 음수가 들어오다가 양수가 들어오는 경우를 고려하지 못하게 됨
 * => sum[i] : input[i] >= 0 -> max(sum[i-1][0] + input[i], sum[i-1][1] + input[i])
 *
 * -> 전부 음수의 입력이 들어온다면?? => 예외처리
 */

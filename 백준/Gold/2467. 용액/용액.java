import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int left = 0;
        int right = n-1;
        long r1 = 0;
        long r2 = 0;
        long sum = Long.MAX_VALUE;
        while (left < right) {
            long min = arr[left];
            long max = arr[right];
            long currentSum = min + max;

            if (Math.abs(sum) > Math.abs(min + max)) {
                sum = min + max;
                r1 = min;
                r2 = max;
            }

            if (currentSum < 0) left++;
            else if (currentSum > 0) right--;
            else break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(r1).append(" ").append(r2);

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 5 2467번 용액
 *
 * 그냥 순차탐색 2번으로 모든 수의 합을 구해서 0과의 차이를 비교하면 시간초과 발생
 * --------------------------------------------------------------
 *
 * 이진탐색이 아니라, 투 포인터 알고리즘으로 접근해야 함
 * 
 */

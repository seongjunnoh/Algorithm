import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        long closest = Long.MAX_VALUE;
        long[] result = new long[3];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < closest) {
                    closest = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    // 합이 0인 경우
                    sb.append(result[0]).append(" ").append(result[1]).append(" ").append(result[2]);
                    System.out.println(sb);
                    br.close();
                    return;
                }
            }
        }

        sb.append(result[0]).append(" ").append(result[1]).append(" ").append(result[2]);
        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 3 2473 세 용액
 *
 * a+b+c 가 0에 가장 가까운 (a,b,c) 쌍 구하기
 * -> 단순히 숫자 3개의 쌍 구하면 1250 억 -> 시간초과
 * => 2개의 쌍 고르기(n 제곱) & 각 쌍마다 이분탐색으로 적절한 수 하나 고르기(log n) -> n제곱 * logn => 이것도 시간 초과
 * -> ??
 *
 * -> 하나의 숫자 고정(n) & 나머지 2개의 수 투 포인터로 찾기(n) -> n 제곱
 */

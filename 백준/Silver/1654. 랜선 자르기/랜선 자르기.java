import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Integer[] arr = new Integer[k];
        long max = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long min = 1;
        while (min <= max) {
            long mid = (min + max) / 2;
            long sum = 0;
            for (int i = 0; i < k; i++) {
                sum += arr[i] / mid;
            }

            if (sum < n) max = mid - 1;
            else min = mid + 1;
        }

        System.out.println(max);
        br.close();
    }
}

/**
 * 실버 2 1654 랜선 자르기
 *
 * arr[i] / x = ai 라 할 때, a1 + a2 + ,,, + ak = n 이 되는 x 중 최대값 구하기
 * -> 0부터 arr 의 max 값을 시작으로 이분탐색 시작
 *      => mid 로 나눈 값들의 합이 n보다 작으면 -> 더 작은 값으로 나눠야 함 -> mid-1를 max로 해서 다시 이분탐색
 *      =>                     크거나 같으면 -> 더 큰 값으로 나눠야 함 -> mid+1을 min으로 해서 다시 이분탐색
 *     
 */

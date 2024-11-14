import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            long target = arr[i - 1];
            int l = i;
            int r = n - 1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (arr[mid] - target >= m) r = mid - 1;
                else l = mid + 1;
            }

            if (l == n) l = n - 1;      // 예외처리
            if (arr[l] - target >= m) min = Math.min(min, arr[l] - target);
        }

        System.out.println(min);
        br.close();
    }
}

/**
 * 골드5 2230 수 고르기
 *
 * n개의 수 중, 2개씩 쌍 고르고 & 고른 쌍들 중 차이가 m이상인 것들 중 최솟값 구하기
 * -> n 제곱 -> 시간초과
 *
 * => 정렬 후, 자신의 뒤의 수 중 차이가 m이상인 최소의 수를 이분 탐색으로 구하기 (= lowerBound)
 * -> n * logn
 *
 */

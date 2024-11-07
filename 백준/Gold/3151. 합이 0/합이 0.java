import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int target = -(arr[i] + arr[j]);

                // target의 lower bound 찾기
                int l = j + 1;
                int r = n - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;

                    if (arr[mid] >= target) r = mid - 1;
                    else l = mid + 1;
                }
                int lowerBound = l;

                // target의 upper bound 찾기
                l = j + 1;
                r = n - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;

                    if (arr[mid] <= target) l = mid + 1;
                    else r = mid - 1;
                }
                int upperBound = l;

                count += upperBound - lowerBound;
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 4 3151 합이 0
 *
 * arr 중 3개의 합이 0이 되는 세트를 몇개 만들 수 있는가?
 * -> a + b + c = 0 <=> a + b = -c
 *      => a, b 찾고 (n 제곱) && 이분탐색으로 c 찾기 (log n)
 *      => 이때, a, b, c 모두 중복이 허용되므로 c의 개수는 upper bound - lower bound
 */

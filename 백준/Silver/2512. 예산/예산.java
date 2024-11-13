import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        long m = Long.parseLong(br.readLine());

        if (sum <= m) {
            System.out.println(max);
            br.close();
            return;
        }

        long l = 1;
        long r = max;
        while (l <= r) {
            long mid = (l + r) / 2;

            long curSum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] < mid) curSum += arr[i];
                else curSum += mid;
            }

            if (curSum <= m) l = mid + 1;
            else r = mid - 1;
        }

        System.out.println(l - 1);      // upperBound
        br.close();
    }
}

/**
 * 실버 2 2512 예산
 *
 */

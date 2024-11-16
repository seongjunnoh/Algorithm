import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        long sum = arr[0];
        int count = 0;
        for (int l = 0; l < n; l++) {
            boolean flag = false;
            while (sum < m && r < n) {
                r++;

                if (r == n) flag = true;
                else sum += arr[r];
            }

            if (flag) break;

            if (sum == m) count++;

            sum -= arr[l];
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 실버4 2003 수들의 합 2
 *
 */

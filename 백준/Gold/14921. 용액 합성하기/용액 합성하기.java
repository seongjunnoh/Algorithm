import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = n - 1;
        long result = Long.MAX_VALUE;
        while (l < r) {
            long sum = arr[l] + arr[r];
            if (Math.abs(sum) < Math.abs(result)) result = sum;

            if (sum < 0) l++;
            else r--;
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 5 14921 용액 합성하기
 *
 * 2개의 합이 0에 가장 가까운 2개의 쌍 구하기
 *
 * 투 포인터로 접근
 */

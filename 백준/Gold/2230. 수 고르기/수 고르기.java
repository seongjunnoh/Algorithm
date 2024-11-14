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

        int before = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int l = i;
            int r = before;

            boolean flag = true;
            while (arr[r] - arr[l] < m){
                if (r == n - 1) {
                    flag = false;
                    break;
                }
                r++;
            }
            before = r;

            if (flag) min = Math.min(min, arr[r] - arr[l]);
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
 * => 정렬 후, 투 포인터를 이용해 차이가 m 이상이 되는 쌍들 중, 최소값 구하기
 *
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        int sum = arr[0];
        int min = Integer.MAX_VALUE;
        for (int l = 0; l < n; l++) {
            boolean flag = false;
            while (r < n && sum < s) {
                r++;
                if (r == n) flag = true;
                else sum += arr[r];
            }

            if (flag) break;        // 더 이상 l을 움직여도 부분합이 s보다 커질 수 없음

            min = Math.min(min, r - l + 1);

            sum -= arr[l];
        }

        if (min == Integer.MAX_VALUE) min = 0;      // 예외처리
        System.out.println(min);
        br.close();
    }
}

/**
 * 골드4 1806 부분합
 *
 * 연속된 수들의 부분합이 s 이상이 되는 것 중, 가장 짧은 길이 구하기
 * -> 투 포인터로 연속된 수들의 시작, 끝을 관리
 *
 */

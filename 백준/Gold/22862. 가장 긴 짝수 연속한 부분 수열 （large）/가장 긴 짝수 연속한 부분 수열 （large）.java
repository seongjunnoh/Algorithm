import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int count = 0;      // 현재 구간에서 제거한 홀수 개수
        int max = 0;

        while (r < n) {     // 투 포인터
            if (arr[r] % 2 != 0) count++;

            while (count > k) {
                if (arr[l] % 2 != 0) count--;
                l++;
            }

            // 현재 구간에서 짝수 최대 길이 계산
            max = Math.max(max, r - l + 1 - count);

            r++;
        }

        System.out.println(max);
        br.close();
    }
}

/**
 * 골드 5 22862 가장 긴 짝수 연속한 부분 수열 (large)
 *
 * 1이상 정수인 arr에서 최대 k개 골라 삭제
 * -> 짝수로 이루어진 연속한 부분 수열 중 가장 긴 길이 구하기
 *
 * => arr 중 짝수인 곳에서 시작, 뒤로 이동하면서 홀수인것 삭제
 *    & 이 과정을 출발점을 계속 뒤로 이동하면서 반복
 *    => 투 포인터 기법
 *    
 * ---------------------------------------------------
 * 앞에서부터 모든 연속하는 부분 수열 고려 & 그 중 중간 중간 홀수인것 제거한 것의 길이를 구하자
 * -> r - l + 1 - count
 *
 */

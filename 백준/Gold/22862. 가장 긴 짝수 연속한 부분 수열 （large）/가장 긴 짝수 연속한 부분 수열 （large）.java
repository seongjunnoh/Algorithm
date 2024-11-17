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

        int r = 0;
        int max = 0;
        int length = 0;     // 연속한 짝수 부분수열의 길이
        int count = 0;      // 홀수를 제거한 횟수
        for (int l = 0; l < n; l++) {
            if (arr[l] % 2 != 0) {      // 부분수열 시작 포인트 찾기 (짝수인 지점)
                count--;
                if (l == r) {
                    count = 0;
                    r = l + 1;
                }
                continue;
            }

            while (r < n && count <= k) {
                if (arr[r] % 2 == 0) length++;
                else {
                    if (count == k) break;
                    count++;
                }

                r++;
            }

            max = Math.max(max, length);

            if (r == n) break;      // 더이상 l을 옮길 필요 없음

            length--;       // arr[l]를 부분수열에서 빼기
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
 */

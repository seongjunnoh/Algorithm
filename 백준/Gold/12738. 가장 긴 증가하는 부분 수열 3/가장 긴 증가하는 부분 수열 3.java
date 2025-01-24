import java.io.*;
import java.util.*;

class Solution_12738 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n + 1];         // 가장 긴 증가하는 부분 수열
        lis[0] = arr[0];        // 초기화
        int end = 0;            // 현재 lis의 마지막 원소의 인덱스
        for (int i = 0; i < n; i++) {
            if (arr[i] > lis[end]) {
                lis[end + 1] = arr[i];
                end++;
            } else {            // lis 중 arr[i]의 lower bound 찾기 & lower bound의 원소와 arr[i]를 swap
                int l = 0;
                int r = end;
                while (l <= r) {
                    int mid = (l + r) / 2;

                    if (lis[mid] >= arr[i]) r = mid - 1;
                    else l = mid + 1;
                }

                lis[l] = arr[i];
            }
        }

        System.out.println(end + 1);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_12738 s = new Solution_12738();
        s.solution();
    }
}

/**
 * 가장 긴 증가하는 부분 수열 2 문제와 풀이법 동일
 *
 */

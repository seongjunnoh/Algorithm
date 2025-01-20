import java.io.*;
import java.util.*;
import java.lang.*; 

class Solution_32712 {

    int n, k;
    long[] arr;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 반시계방향 회전
        long max = 0;           // 정답
        long score = 0;
        for (int i = 0; i < Math.min(n, k); i++) {      // min(n, k) 까지만 반복 (최대 한 바퀴만 돌려도 ok)
            max = Math.max(max, score + (k - i) * arr[i]);          // i번째 위치까지 이동 & 여기서 왓다갓다
            score += arr[i];
        }

        // 시계방향 회전
        // 배열 뒤집기
        long temp = 0;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }

        score = 0;
        for (int i = 0; i < Math.min(n, k); i++) {
            max = Math.max(max, score + (k - i) * arr[i]);
            score += arr[i];
        }

        System.out.println(max);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_32712 s = new Solution_32712();
        s.solution();
    }
}

/**
 * 다이얼 룰렛을 k번 회전해 얻을 수 있는 점수의 최댓값은?
 *
 * 모든 경로 고려? -> dfs -> 시간초과
 * 모든 위치에 대해서 해당 위치까지 이동 & 왔다갔다하는걸 생각해보자 -> 이중 최대값이 정답
 * 
 * cf) 배열의 인덱스를 거꾸로 탐색하는거보다 그냥 배열을 뒤바꾸는게 속편한듯
 */

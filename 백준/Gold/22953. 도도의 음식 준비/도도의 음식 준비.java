import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_22953 {

    int[] time;
    int n, k;
    long min = 1_000_000_000_000L;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        time = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        dfs(c);

        System.out.println(min);
        br.close();
    }

    void dfs(int remain) {
        if (remain == 0) {      // 격려 끝
            search();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (time[i] > 1) {
                time[i]--;
                dfs(remain - 1);
                time[i]++;
            }
        }
        
        // 모든 격려 경우를 고려한 뒤에도 remain > 0 인 경우에도 search() 수행
        search();
    }

    void search() {
        long l = 1;
        long r = 1_000_000_000_000L;

        while (l <= r) {
            long mid = (l + r) / 2;
            long cnt = 0;       // mid 시간 동안 요리사들이 만들 수 있는 음식 총 개수
            for (int i = 0; i < n; i++) {
                cnt += mid / time[i];
            }

            if (cnt >= k) r = mid - 1;      // lowerBound
            else l = mid + 1;
        }

        min = Math.min(min, l);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_22953 s = new Solution_22953();
        s.solution();
    }
}

/**
 * 골드 4 22953번 도도의 음식 준비
 *
 * k를 n개로 어떻게 분배??
 * -> 분배한 n개의 값들의 차이가 최소가 되도록 격려 & 분배 해야함
 *
 * 격려 -> 그리디 ?? -> no, 원래 요리시간이 작은 요리사를 격려하는게 좋을지, 요리시간 큰 요리사를 격려하는게 좋을지는 미리 알 수 없음
 * -> 백트래킹으로 가능한 모든 격려의 경우의 수 구하기 (= 10의 0제곱 + 10의 1제곱 + ,, + 10의 5제곱 = 약 10의 6제곱)
 * -> 현재 경우에 대해서 k개 요리에 걸리는 최소 시간 구하기
 * -> 어떻게 ???
 *
 * 최소시간을 구하는게 아니라, 1 ~ 10의 12제곱 중 가능한 최소 시간을 찾는다고 생각 -> 매개변수 탐색
 * -> 시간 : log 10의 12제곱 = 약 40
 * -> 총 시간 = 4천만 -> ok
 *
 */

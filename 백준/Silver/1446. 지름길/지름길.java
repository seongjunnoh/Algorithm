import java.io.*;
import java.util.*;

class Load_1 implements Comparable<Load_1> {
    int s;
    int e;
    int w;

    Load_1(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }

    @Override
    public int compareTo(Load_1 l) {
        if (this.s == l.s) return this.e - l.e;
        return this.s - l.s;
    }
}

class Solution_1446_1 {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<Load_1> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (e <= d && (e - s) > w) list.add(new Load_1(s, e, w));     // 진짜 지름길만 add
        }

        Collections.sort(list);         // 정렬

        int[] dp = new int[d + 1];
        for (int i = 0; i <= d; i++) {
            dp[i] = i;      // dp 초기화
        }
 
        for (int i = 0; i < list.size(); i++) {
            Load_1 cur = list.get(i);

            if (dp[cur.e] > dp[cur.s] + cur.w) {        // 현재 지름길 타는게 이득이라면 cur.e 이후부터의 모든 dp 값 update
                int dist = dp[cur.s] + cur.w;
                for (int j = cur.e; j <= d; j++) {      // dp 값 update 시 기존 dp 값과 비교 후 update
                    if (dp[j] < dist) break;
                    dp[j] = dist++;
                }
            }
        }

        System.out.println(dp[d]);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1446_1 s = new Solution_1446_1();
        s.solution();
    }
}

/**
 * 지름길 최대 12개 -> 모든 경우의 수 : 2의 12제곱 = 약 백만 가지
 * -> 완전탐색 ??
 *
 * dp[i] : i 위치까지 도달하는데 운전해야하는 최단 거리
 *
 */

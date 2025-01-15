import java.io.*;
import java.util.*;

class Solution_32114 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] w = new int[n];           // w[i] : i -> i+1 로 가는 차선 개수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[n + 1];         // 누적합 구현을 위한 차분 배열
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());       // 출발
            int v = Integer.parseInt(st.nextToken());       // 도착
            int x = Integer.parseInt(st.nextToken());       // 자동차 개수
            diff[u] += x;
            diff[v] -= x;
        }

        int[] car = new int[n + 1];          // car[i] : i->i+1 로 가는 자동차 개수
        car[1] = diff[1];
        for (int i = 2; i <= n; i++) {
            car[i] = car[i - 1] + diff[i];
        }

        // 도로 부담의 최솟값 계산
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(cal(w[i], car[i])).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    long cal(int w, int car) {
        int q = car / w;
        int r = car % w;

        // 나머지가 없는 경우 -> 모든 차선에 자동차 q대씩 분배
        if (r == 0) return (long) q * q * w;

        // 나머지가 있는 경우 -> 차선 r개에 자동차 q+1대씩, 나머지 차선 w-r개에 자동차 q대씩 분배
        long sum1 = (long) r * (q + 1) * (q + 1);
        long sum2 = (long) (w - r) * q * q;
        return sum1 + sum2;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_32114 s = new Solution_32114();
        s.solution();
    }
}

/**
 * 골드 4 32114번 SoleMap
 *
 * 1 <= i <= n-1 에서 i -> i+1 로 가는 도로에 자동차가 몇 대 있는지 알아야 함
 * 2중 for loop 사용하면 시간 초과
 * -> 시간 초과 피하면서 어떻게 i -> i+1 자동차 개수 알 수 있나?? -> 누적합 & 차분 배열 사용
 *
 * 각 도로마다 도로 부담의 최솟값을 어떻게 계산 ???
 * -> car를 w개로 분배 -> 분배 결과의 최댓값과 최솟값의 차이가 최소가 되도록 분배해야 한다
 *                  -> 차이의 최소값은 무조건 0 또는 1 (???)
 *
 */
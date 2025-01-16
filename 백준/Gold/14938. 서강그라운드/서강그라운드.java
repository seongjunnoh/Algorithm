import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_14938 {

    final int INF = 1501;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        int[][] edge = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {          // edge 초기화
            for (int j = 1; j <= n; j++) {
                if (i == j) edge[i][j] = 0;
                else edge[i][j] = INF;
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[a][b] = w;
            edge[b][a] = w;         // 양방향 그래프
        }

        // 플로이드 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
                }
            }
        }

        int max = 0;        // 정답
        for (int i = 1; i <= n; i++) {
            int curSum = 0;     // i 노드에 낙하할 경우 얻을 수 있는 아이템 개수
            for (int j = 1; j <= n; j++) {
                if (edge[i][j] <= m) curSum += item[j];
            }

            max = Math.max(max, curSum);
        }

        System.out.println(max);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_14938 s = new Solution_14938();
        s.solution();
    }
}

/**
 * 골드 4 14938번 서강그라운드
 *
 * 얻을 수 있는 아이템의 최대 개수는 ??
 * -> 모든 노드에 대해서, 각 노드에 낙하했을 때 얻을 수 있는 아이템 개수 구하기 & 그 중 최대값 찾기
 * -> 낙하한 노드에서 m 이하의 거리로 이동가능한 노드들 구하기
 * -> 플로이드 알고리즘
 */
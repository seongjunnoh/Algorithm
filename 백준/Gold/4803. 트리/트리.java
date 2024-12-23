import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static boolean[] cycle;

    static int find(int n) {
        if (n == parent[n]) return n;

        parent[n] = find(parent[n]);
        return parent[n];
    }

    static void union(int x, int y) {
        int rx = find(x);        // x의 루트
        int ry = find(y);        // y의 루트

        if (rx == ry) cycle[rx] = true;       // x와 y의 루트가 같다 == 사이클 발생
        else {
            if (rx > ry) parent[rx] = ry;
            else parent[ry] = rx;
        }

        // 사이클 전파 -> 한쪽이 사이클이면, union 한 결과도 사이클
        if (cycle[rx] || cycle[ry]) {
            cycle[rx] = cycle[ry] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int count = 1;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            parent = new int[n + 1];
            cycle = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                cycle[i] = false;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
            }

            // 트리 개수 계산
            int result = 0;
            for (int i = 1; i <= n; i++) {
                if (find(i) == i && !cycle[i]) result++;
            }

            if (result == 0) sb.append("Case " + count + ": No trees.").append("\n");
            else if (result == 1) sb.append("Case " + count + ": There is one tree.").append("\n");
            else sb.append("Case " + count + ": A forest of " + result + " trees.").append("\n");
            count++;
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 4803번 트리
 *
 * 그래프에서 트리 개수 구하기 -> 사이클이 없는 연결요소 개수 찾기
 * bfs로 연결요소 개수 찾기
 *
 * 1->2, 2->1 : 1->2 고려할 경우, 2->1 에지를 false로 변경해야함 & bfs는 true인 에지에 대해서만 수행
 * ==============================
 * 방법2 -> node - 1 = edge 인 경우 해당 연결요소를 트리라고 할 수 있음
 * 방법3 -> union find 로 union 의 개수 찾기
 */

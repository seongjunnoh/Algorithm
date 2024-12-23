import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean[] visit;
    static int result;

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        int n = 0;
        int e = 0;      // node, edge 개수

        q.add(start);
        visit[start] = true;
        while (!q.isEmpty()) {
            int poll = q.poll();
            n++;

            for (int near : graph[poll]) {
                e++;

                if (!visit[near]) {
                    visit[near] = true;
                    q.add(near);
                }
            }
        }

        if (2 * (n - 1) == e) result++;     // tree 하나 추가
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

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }

            // bfs로 연결요소 개수 찾기
            visit = new boolean[n + 1];
            result = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) bfs(i);
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

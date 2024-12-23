import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Map<Integer, Boolean>> graph;       // graph.get(i) : i 노드와 연결된 노드들의 상태를 나타내는 map (-> key : 연결된 노드의 번호, val : bfs 대상 여부)
    static boolean[] visit;
    static int result;

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int poll = q.poll();

            Map<Integer, Boolean> nears = graph.get(poll);
            for (int near : nears.keySet()) {
                if (nears.get(near)) {          // bfs 대상이 되는 poll -> near 에 대해서만
                    graph.get(near).put(poll, false);           // near -> poll 로 가는 에지는 bfs 대상 아님

                    if (nears.get(near) && !visit[near]) {      // poll -> near가 bfs 대상이고, near가 방문하지 않은 노드인 경우
                        visit[near] = true;
                        q.add(near);
                    } else {
                        return;     // 현재 연결요소는 사이클이다
                    }
                }
            }
        }

        result++;       // 연결요소 개수 하나 추가
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

            graph = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                graph.put(i, new HashMap<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).put(y, true);
                graph.get(y).put(x, true);
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
 */

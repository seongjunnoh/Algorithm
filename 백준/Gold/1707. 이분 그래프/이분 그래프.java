import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;       // 리스트의 배열로 graph 표현
    static int[] color;         // 0 : 미방문, -1/1 : 2가지 색
    static StringBuilder sb = new StringBuilder();
    static int v, e;

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (color[i] == 0) {
                color[i] = 1;

                for (int near : graph[i]) {
                    if (color[near] == 0) {
                        color[near] = -1;
                        q.add(near);
                    }
                }
            }

            // bfs
            while (!q.isEmpty()) {
                Integer poll = q.poll();

                for (int near : graph[poll]) {
                    if (color[near] == color[poll]) {
                        sb.append("NO").append("\n");
                        return;
                    }

                    if (color[near] == 0) {
                        if (color[poll] == -1) color[near] = 1;
                        else color[near] = -1;
                        q.add(near);
                    }
                }
            }
        }

        sb.append("YES").append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v + 1];
            for (int j = 1; j <= v; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }

            color = new int[v + 1];

            bfs();
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 1707번 이분 그래프
 *
 * v = 1, 2, 3
 * 1-3, 2-3 -> yes
 *
 * v = 1, 2, 3, 4
 * 1-2, 2-3, 3-4, 4-2 -> no
 *
 * 간선을 가지는 두 노드를 서로 다른 공간에 분리 -> 가능하면 yes, 아니면 no
 * -> 모든 노드가 가지는 간선을 확인하면 시간 초과 발생
 * -> bfs 로 방문 여부 확인해야함
 */



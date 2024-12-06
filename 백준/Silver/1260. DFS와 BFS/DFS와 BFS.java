import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, List<Integer>> map;
    static StringBuilder b = new StringBuilder();       // bfs
    static StringBuilder d = new StringBuilder();       // dfs
    static boolean[] visitB;
    static boolean[] visitD;

    static void bfs(int node) {
        visitB[node] = true;
        b.append(node).append(" ");

        Queue<Integer> q = new LinkedList<>();

        for (int num : map.get(node)) {
            if (!visitB[num]) {
                visitB[num] = true;
                q.add(num);
            }
        }

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            b.append(poll).append(" ");

            for (int num : map.get(poll)) {
                if (!visitB[num]) {
                    visitB[num] = true;
                    q.add(num);
                }
            }
        }
    }

    static void dfs(int node) {
        visitD[node] = true;

        for (int num : map.get(node)) {
            if (!visitD[num]) {
                visitD[num] = true;
                d.append(num).append(" ");
                dfs(num);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        visitB = new boolean[n + 1];
        visitD = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(x).add(y);
            map.get(y).add(x);
        }

        // map의 value 오름 차순 정렬
        for (int key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        d.append(v).append(" ");
        dfs(v);
        bfs(v);

        System.out.println(d);
        System.out.println(b);
        br.close();
    }
}

/**
 * 실버 2 1260번 DFS와 BFS
 *
 * 우선순위 큐를 for-each 문으로 순회하면 우선순위 순서대로 참조되는것 아님
 *
 */

import java.io.*;
import java.util.*;
import java.lang.*;

class Pair_1167 {
    int from;
    int to;
    int w;

    Pair_1167(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

class Solution_1167 {

    List<Pair_1167>[] graph;
    boolean[] visit;
    int[] l;        // l[i] : 특정 노드에서 출발해서 i 노드까지의 거리

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int w = Integer.parseInt(st.nextToken());

                graph[from].add(new Pair_1167(from, to, w));
            }
        }

        // 1번 노드부터 시작 & 가장 멀리 갈 수 있는 노드(= start) 찾기
        visit = new boolean[n + 1];
        l = new int[n + 1];
        visit[1] = true;
        calculateLength(1, 0);

        int max = 0;
        int start = -1;
        for (int i = 1; i <= n; i++) {
            if (max < l[i]) {
                max = l[i];
                start = i;
            }
        }

        visit = new boolean[n + 1];
        l = new int[n + 1];
        visit[start] = true;
        calculateLength(start, 0);

        max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, l[i]);
        }
        System.out.println(max);
        br.close();
    }

    void calculateLength(int node, int length) {
        for (Pair_1167 pair : graph[node]) {
            int near = pair.to;
            if (!visit[near]) {
                visit[near] = true;
                l[near] = pair.w + length;
                calculateLength(near, l[near]);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1167 s = new Solution_1167();
        s.solution();
    }
}

/**
 * 골드 2 1167번 트리의 지름
 */

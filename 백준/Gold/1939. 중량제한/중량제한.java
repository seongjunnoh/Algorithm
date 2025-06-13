import java.io.*;
import java.util.*;

class Edge_1939 implements Comparable<Edge_1939> {
    int a;
    int b;
    long w;

    Edge_1939(int a, int b, long w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1939 n) {     // w 값 기준 내림차순 정렬
        if (this.w > n.w) return -1;
        else if (this.w < n.w) return 1;
        return 0;
    }
}

class Solution_1939 {

    int n, m;
    PriorityQueue<Edge_1939> pq;
    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        parent = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            pq.add(new Edge_1939(a, b, w));
            parent[a] = a;
            parent[b] = b;
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        long result = -1;

        while (!pq.isEmpty()) {
            Edge_1939 poll = pq.poll();
            if (parent[poll.a] != parent[poll.b]) {
                union(poll.a, poll.b);

                if (find(x) == find(y)) {
                    result = poll.w;
                    break;
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    int find(int node) {
        if (node == parent[node]) return node;

        parent[node] = find(parent[node]);
        return parent[node];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1939 s = new Solution_1939();
        s.solution();
    }
}

/**
 * x -> y 로 가는 경로는 항상 존재
 * edge 들 중 w 값이 큰 것들부터 끝 노드들을 union
 * x, y 가 union 되는 순간의 edge의 w 값이 정답
 *
 * 총 edge 수가 10만이므로 ok
 */

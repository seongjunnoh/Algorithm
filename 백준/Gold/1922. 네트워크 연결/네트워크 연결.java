import java.io.*;
import java.util.*;

class Edge_1922 implements Comparable<Edge_1922> {
    int a;
    int b;
    int w;

    Edge_1922(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1922 e) {
        return this.w - e.w;        // 에지의 가중치 기준 오름차순 배열
    }
}

class Solution_1922 {

    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        PriorityQueue<Edge_1922> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Edge_1922(a, b, w));
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            Edge_1922 poll = pq.poll();
            if (find(poll.a) != find(poll.b)) {
                union(poll.a, poll.b);
                sum += poll.w;
            }
        }

        System.out.println(sum);
        br.close();
    }

    int find(int node) {
        if (parent[node] == node) return node;

        parent[node] = find(parent[node]);
        return parent[node];
    }

    void union(int a, int b) {
        a = find(a);        // a의 루트
        b = find(b);        // b의 루트

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1922 s = new Solution_1922();
        s.solution();
    }
}

/**
 * 모든 컴퓨터를 연결하는데 필요한 최소 비용 구하기
 * -> 유니온 파인드
 */
import java.io.*;
import java.util.*;
import java.lang.*;

class Pair_1774 {
    int num;
    int x;
    int y;

    Pair_1774(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Edge_1774 implements Comparable<Edge_1774> {
    int start;
    int end;
    double w;

    Edge_1774(int start, int end, double w) {
        this.start = start;
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_1774 e) {         // 가중치 작은 순 정렬
        if (this.w < e.w) return -1;
        else if (this.w == e.w) return 0;
        return 1;
    }
}

class Solution_1774 {

    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Pair_1774[] map = new Pair_1774[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[i] = new Pair_1774(i, x, y);
        }

        PriorityQueue<Edge_1774> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int x = map[i].x - map[j].x;
                int y = map[i].y - map[j].y;
                double w = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                pq.add(new Edge_1774(i, j, w));
            }
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            union(n1, n2);          // 이미 연결된 통로들
        }

        double result = 0;
        while (!pq.isEmpty()) {
            Edge_1774 poll = pq.poll();
            if (union(poll.start, poll.end)) result += poll.w;
        }

        System.out.printf("%.2f", result);         // 소수점 둘째자리까지 반올림
        br.close();
    }

    boolean union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);
        if (n1 == n2) return false;
        if (n1 > n2) parent[n1] = n2;
        else parent[n2] = n1;
        return true;
    }

    int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1774 s = new Solution_1774();
        s.solution();
    }
}

/**
 * 골드 3 1774번 우주신과의 교감
 *
 * 모든 노드들을 연결하기 위해 추가로 만들어야 할 통로 길이의 합의 최소값 구하기
 * -> 최소 스패닝 트리 (크루스칼)
 *
 */

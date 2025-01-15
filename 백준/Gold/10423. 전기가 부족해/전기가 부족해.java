import java.io.*;
import java.util.*;

class Edge_10423 implements Comparable<Edge_10423> {
    int u;
    int v;
    int w;

    Edge_10423(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_10423 e) {
        return this.w - e.w;            // w 기준 오름차순 정렬
    }
}

class Root {
    int num;
    boolean hasPower;

    Root(int num, boolean hasPower) {
        this.num = num;
        this.hasPower = hasPower;
    }
}

class Solution_10423 {

    int[] parent;
    boolean[] hasPower;     // hasPower[i] = true : i가 속한 트리가 발전소를 가지고 있다
    Set<Integer> power = new HashSet<>();       // 발전소

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            power.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Edge_10423> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge_10423(u, v, w));
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {          // parent 초기화
            parent[i] = i;
        }

        hasPower = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {          // hasPower 초기화
            if (power.contains(i)) hasPower[i] = true;
            else hasPower[i] = false;
        }

        int sum = 0;        // 정답
        while (!pq.isEmpty()) {
            Edge_10423 poll = pq.poll();
            if (union(poll.u, poll.v)) sum += poll.w;
        }

        System.out.println(sum);
        br.close();
    }

    boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        // 두 subtree의 루트가 모두 발전소를 가지고 있으면 -> union X
        if (u == v || (hasPower[u] && hasPower[v])) return false;

        if (u > v) {
            parent[u] = v;
            if (hasPower[u]) hasPower[v] = true;
        } else {
            parent[v] = u;
            if (hasPower[v]) hasPower[u] = true;
        }
        return true;
    }

    int find(int node) {
       if (parent[node] == node) return node;
       if (hasPower[node]) hasPower[parent[node]] = true;
       parent[node] = find(parent[node]);
       return parent[node];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_10423 s = new Solution_10423();
        s.solution();
    }
}

/**
 * 골드 3 10423번 전기가 부족해
 *
 * 케이블을 최소로 설치하여 모든 도시에 전기를 공급하자
 * 하나의 트리에 발전소가 하나만 존재하도록 모든 도시들을 여러개의 트리로 매핑
 * -> 최소 스패닝 트리 (크루스칼 알고리즘)
 */
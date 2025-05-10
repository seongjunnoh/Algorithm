import java.io.*;
import java.util.*;

class Star_4386 {
    float x;
    float y;

    Star_4386(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class Edge_4386 implements Comparable<Edge_4386> {
    int from;
    int to;
    float w;

    Edge_4386(int from, int to, float w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_4386 e) {
        if (this.w < e.w) return -1;
        else if (this.w > e.w) return 1;
        else return 0;
    }
}

class Solution_4386 {

    Star_4386[] stars;
    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        stars = new Star_4386[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star_4386(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
        }

        PriorityQueue<Edge_4386> pq = new PriorityQueue<>();
        for (int from = 0; from < stars.length; from++) {
            for (int to = from + 1; to < stars.length; to++) {
                float xDif = Math.abs(stars[from].x - stars[to].x);
                float yDif = Math.abs(stars[from].y - stars[to].y);
                float dist = (float) Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));      // 강제 형변환해도 ok

                pq.add(new Edge_4386(from, to, dist));
            }
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;      // parent 초기화
        }

        float sum = 0;
        while (!pq.isEmpty()) {
            Edge_4386 poll = pq.poll();
            if (find(poll.from) != find(poll.to)) {
                union(poll.from, poll.to);
                sum += poll.w;
            }
        }

        System.out.printf("%.2f", sum);     // 출력 형식 주의
        br.close();
    }

    int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }

    void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);

        if (n1 < n2) parent[n2] = n1;
        else parent[n1] = n2;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_4386 s = new Solution_4386();
        s.solution();
    }
}

/**
 * 최소 스패닝 트리 구하기 -> 크루스칼 알고리즘 (union find)
 *
 */

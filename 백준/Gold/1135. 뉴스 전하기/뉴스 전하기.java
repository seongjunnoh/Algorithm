import java.io.*;
import java.util.*;
import java.lang.*;

class Node_1135 implements Comparable<Node_1135> {          // num 노드를 루트로 하는 subtree의 전파 시간
    int num;
    int t;      // 총 전파 시간

    Node_1135(int num, int t) {
        this.num = num;
        this.t = t;
    }

    @Override
    public int compareTo(Node_1135 n) {
        return n.t - this.t;            // t 기준 내림차순 정렬
    }
}

class Solution_1135 {

    int[] parent;
    List<Integer>[] graph;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parent = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (i > 0) {
                graph[parent[i]].add(i);
            }
        }

        System.out.println(dfs(0) - 1);
        br.close();
    }

    int dfs(int node) {
        if (graph[node].isEmpty()) {        // node = 리프
            return 1;
        }

        PriorityQueue<Node_1135> pq = new PriorityQueue<>();
        for (int child : graph[node]) {
            pq.add(new Node_1135(child, dfs(child)));
        }

        int idx = 0;
        int curTime = 0;        // node가 루트인 subtree에서의 총 전파 시간
        while (!pq.isEmpty()) {
            Node_1135 poll = pq.poll();
            curTime = Math.max(curTime, poll.t + idx++);
        }
        return curTime + 1;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1135 s = new Solution_1135();
        s.solution();
    }
}

/**
 * 골드 2 1135번 뉴스 전하기
 *
 * 모든 직원에게 뉴스를 전하는데 걸리는 시간의 최솟값 구하기
 * -> subtree에 포함된 노드가 많은 쪽에 먼저 뉴스를 전해야 한다 (그리디)
 * -------------------------------------------------
 * 상사는 가장 전파가 오래 걸리는 부하한테 먼저 전파
 * -> 가장 오래걸리는 기준 : 자식수가 많은 부하?? 자식의 높이가 높은 부하??
 * -> 미리 결정할 수 없음 -> 직접 리프까지 탐색해서 가장 전파가 오래걸리는 부하를 찾자
 */

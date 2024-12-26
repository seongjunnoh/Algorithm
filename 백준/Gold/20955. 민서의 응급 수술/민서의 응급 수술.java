import java.io.*;
import java.util.*;

class Solution {

    int[] parent;
    int count = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;      // parent 초기화
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        // subtree를 하나로 연결 -> 그 전에 모든 노드들에 대해서 find 연산 한번 더 수행해야함(루트노드 최종 update)
        for (int i = 1; i <= n; i++) {
            find(i);
        }
        Arrays.sort(parent);
        for (int i = 2; i <= n; i++) {
            if (parent[i - 1] != parent[i]) count++;
        }

        System.out.println(count);
        br.close();
    }

    void union(int u, int v) {
        u = find(u);        // u의 루트
        v = find(v);        // v의 루트

        if (u == v) count++;        // 사이클 형성 -> 에지 없애는 연산 1번 추가

        else {
            if (u < v) parent[v] = u;
            else parent[u] = v;
        }
    }

    int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution s = new Solution();
        s.solution();
    }
}

/**
 * 골드 4 20955번 민서의 응급 수술
 *
 * 노드를 연결 or 노드간 연결 끊기 연산을 최소로 수행
 * -> union-find로 사이클 만드는 에지 제거 & 트리 개수 찾고, 찾은 트리들 연결하기
 *
 * -----------------------------------------
 * find 메서드를 모든 union 연산 끝난 후, 마지막으로 한번 더 해줘야 subtree의 루트가 최종적으로 확정
 */

import java.io.*;
import java.util.*;

class Solution_1717 {

    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];        // 0 ~ n 개
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) union(a, b);
            else {      // command == 1
                if (find(a) == find(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);        // a, b 는 같을 수 있음

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);
        return parent[node];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1717 s = new Solution_1717();
        s.solution();
    }
}

/**
 * 두 원소가 포함된 집합을 합치기 & 두 원소가 같은 집합에 포함되어 있는지를 확인
 * -> union - find
 */
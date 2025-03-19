import java.io.*;
import java.util.*;

class Solution_1976 {

    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] trip = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            trip[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;      // 초기화
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1) union(i, j);        // 합집합 만들기
            }
        }

        for (int i = 1; i < m; i++) {
            if (find(trip[i - 1]) != find(trip[i])) {
                System.out.println("NO");
                br.close();
                return;
            }
        }

        System.out.println("YES");
        br.close();
    }

    int find(int node) {
        if (parent[node] == node) return node;

        parent[node] = find(parent[node]);
        return parent[node];
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1976 s = new Solution_1976();
        s.solution();
    }
}

/**
 * union find를 통해 합집합 여러개 만들기
 * -> 여행경로의 모든 노드가 같은 집합에 속하면 YES, 아니면 NO
 */
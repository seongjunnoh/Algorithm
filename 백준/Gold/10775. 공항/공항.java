import java.io.*;

class Solution_10775 {

    int[] parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        // 게이트 개수
        int p = Integer.parseInt(br.readLine());        // 비행기 개수
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 0; i < p; i++) {
            int maxGate = Integer.parseInt(br.readLine());
            int root = find(maxGate);
            if (root == 0) break;           // 1 ~ maxGate 까지 비행기 보관되어 있는 상태

            union(root, root - 1);
            result++;
        }

        System.out.println(result);
        br.close();
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);          // 경로 압축
        return parent[node];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_10775 s = new Solution_10775();
        s.solution();
    }
}

/**
 * 골드 2 10775번 공항
 *
 * i번째 비행기를 1번부터 gi 번째 게이트 중 하나에 도킹
 * -> 최대한 많은 비행기 도킹시키기 위해서는 최대한 gi에 가까운 게이트에 현재 비행기를 놔두는게 좋지 않나?? (그리디)
 * -> 현재 비행기를 보관할 게이트를 logn 만에 찾아야 한다 -> ?? (정렬된 상태가 아니므로 이분탐색 X)
 * -------------------------------------------------------
 * find() 를 통해서 현재 노드의 조상에 비행기를 도킹하도록 하자
 */
import java.io.*;
import java.util.*;

class Node {
    int left;
    int right;

    Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

class Solution_2250 {

    Node[] graph;
    int[] parent;           // parent[i] : i 노드의 부모 노드
    int index;
    int[] col;              // col[i] : i 노드의 col 값
    List<Integer>[] level;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        graph = new Node[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;          // parent 초기화
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            graph[node] = new Node(left, right);

            if (left != -1) parent[left] = node;
            if (right != -1) parent[right] = node;
        }

        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {       // 루트노드 찾기
                root = i;
                break;
            }
        }

        col = new int[n + 1];
        index = 1;
        level = new ArrayList[n + 1];           // level의 최대값은 n
        for (int i = 1; i <= n; i++) {
            level[i] = new ArrayList<>();
        }
        dfs(root, 1);

        int lv = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> list = level[i];
            if (list.isEmpty()) break;

            int l = list.get(0);                // 현재 레벨에서 가장 왼쪽 노드
            int r = list.get(list.size() - 1);  // 현재 레벨에서 가장 오른쪽 노드
            if (max < col[r] - col[l] + 1) {
                max = col[r] - col[l] + 1;
                lv = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lv).append(" ").append(max);
        System.out.println(sb);
        br.close();
    }

    void dfs(int node, int depth) {
        Node cur = graph[node];

        if (cur.left != -1) dfs(cur.left, depth + 1);
        col[node] = index++;
        level[depth].add(node);
        if (cur.right != -1) dfs(cur.right, depth + 1);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_2250 s = new Solution_2250();
        s.solution();
    }
}

/**
 * 골드 2 2250번 트리의 높이와 너비
 *
 * 각 노드에 대해서 자신보다 왼쪽/오른쪽에 위치한 자식~리프노드 개수 구하기
 * 루트 노드 ?? -> 루트도 찾아야 함
 */
import java.io.*;
import java.util.*;
import java.lang.*;

class Pair {
    int from;
    int to;
    int w;

    Pair(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

class Solution_1967 {

    List<Pair>[] graph;
    boolean[] visit;
    int[] l;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {       // 예외처리
            System.out.println("0");
            br.close();
            return;
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[parent].add(new Pair(parent, child, w));
            graph[child].add(new Pair(child, parent, w));
        }

        visit = new boolean[n + 1];
        l = new int[n + 1];
        visit[1] = true;
        calculateLength(1, 0);        // 루트에서 가장 멀리 떨어진 리프 노드 구하기

        int max = 0;
        int start = -1;
        for (int i = 1; i <= n; i++) {
            if (max < l[i]) {
                max = l[i];
                start = i;
            }
        }

        // start 노드부터 출발, 가장 멀리 갈 수 있는 길이 구하기
        visit = new boolean[n + 1];
        l = new int[n + 1];
        visit[start] = true;
        calculateLength(start, 0);

        max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, l[i]);
        }
        System.out.println(max);
        br.close();
    }

    void calculateLength(int node, int length) {
        for (Pair pair : graph[node]) {         // pair : node -> near
            int near = pair.to;
            if (!visit[near]) {
                visit[near] = true;
                l[near] = pair.w + length;
                calculateLength(near, l[near]);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1967 s = new Solution_1967();
        s.solution();
    }
}

/**
 * 골드 4 1967번 트리의 지름
 *
 * 모든 정점에 대해서 dfs로 갈 수 있는 가장 먼 길이 구하기 ??
 * -> dfs를 n번 반복 -> O(n * (n + v)) -> 시간 초과 ??
 *
 * 루트부터 가장 멀리 떨어진 노드 구하고, 이 노드에서 갈 수 있는 가장 먼 길이가 정답
 * (굳이 모든 노드들에 대해서 갈 수 있는 최대 거리 구할 필요 X)
 * ------------------------------------------------------
 * 메모리 초과 -> weight[][] 이게 int를 1억개 저장 -> 400MB 소모
 * => Sol : graph 저장 시 Pair 클래스 도입
 */

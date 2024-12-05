import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int node;
        int max;

        Pair(int node, int max) {
            this.node = node;
            this.max = max;
        }

        @Override
        public int compareTo(Pair p) {
            if (max == p.max) return node - p.node;     // node가 가지는 max 값이 같다면, node 번호 기준 오름차순 정렬
            return max - p.max;     // max 값 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];        // i -> j 노드로의 최단거리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                arr[i][j] = Integer.MAX_VALUE;      // 초기화
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == -1 && y == -1) break;

            arr[x - 1][y - 1] = 1;
            arr[y - 1][x - 1] = 1;
        }

        // arr update -> 플로이드 와샬 알고리즘
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // i->k->j 가 i->j 보다 작은 경우 arr update
                    if (arr[i][k] != Integer.MAX_VALUE && arr[k][j] != Integer.MAX_VALUE &&
                            arr[i][k] + arr[k][j] < arr[i][j]) arr[i][j] = arr[i][k] + arr[k][j];
                }
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Arrays.sort(arr[i]);

            pq.add(new Pair(i + 1, arr[i][n - 1]));      // i 노드에서 다른 노드로의 최단 거리 중 최대값
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        Integer minOfMax = pq.peek().max;

        while (!pq.isEmpty()) {
            Pair poll = pq.poll();

            if (poll.max == minOfMax) {
                count++;
                sb.append(poll.node).append(" ");
            }
        }

        System.out.println(minOfMax + " " + count);
        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 5 2660번 회장뽑기
 *
 * 1 -> 2
 * 2 -> 1, 3, 4
 * 3 -> 2, 4, 5
 * 4 -> 2, 3, 5
 * 5 -> 3, 4
 *
 * 각 노드에서 다른 노드로 갈 수 있는 최단거리 구하기 -> 플로이드 와샬 알고리즘
 *
 */

import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int node;
        int count;      // 1번 node 부터 현재 node 까지의 count 값

        Pair(int node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    static List<Integer>[] nodes;      // nodes[i] : i 노드가 포함되어 있는 하이퍼튜브 들의 리스트
    static List<Integer>[] hypers;     // hypers[i] : 하이퍼튜브에 속한 노드들의 리스트
    static boolean[] visit;
    static int count = 0;       // 1에서 출발 -> n번 노드까지
    static Queue<Pair> q;
    static int n;

    static boolean bfs() {
        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int hyper : nodes[poll.node]) {
                for (int near : hypers[hyper]) {
                    if (!visit[near]) {
                        visit[near] = true;
                        count = poll.count + 1;
                        q.add(new Pair(near, count));

                        if (near == n) return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        hypers = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            hypers[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                int num = Integer.parseInt(st.nextToken());
                nodes[num].add(i);      // num 번째 node 는 i 번째 하이퍼튜브에 속한다
                hypers[i].add(num);     // i 번째 하이퍼튜브에는 num 번째 node 가 속한다
            }
        }
        
        // 예외처리 (n = 목적지 = 1 인 경우)
        if (n == 1) {
            System.out.println("1");
            br.close();
            return;
        }

        visit = new boolean[n + 1];
        q = new LinkedList<>();
        visit[1] = true;
        q.add(new Pair(1, 1));       // 1에서 시작
        if (bfs()) {
            System.out.println(count);
        } else {
            System.out.println("-1");
        }
        br.close();
    }
}

/**
 * 골드 2 5214번 환승
 *
 * 최대 약 5억개의 간선 생길 수 있다
 * 1부터 n 까지의 최단거리 -> bfs 로 구하면 시간초과 ??
 * & k개의 노드가 하나의 하이퍼튜브를 구성하도록 입력받은 것을 그래프로 표현하면 최악의 경우 1000의 3제곱 만큼의 메모리가 필요 -> 메모리 초과
 *
 * -> 간선의 개수를 줄여야 한다 -> 하이퍼튜브 하나를 하나의 노드로 생각하자
 *
 * 1, 2, 3 -> 1-2, 1-3, 2-1, 2-3, 3-1, 3-2 가 아니라 hiper-{1, 2, 3} 이런식으로
 * => 이러면 간선 개수 줄일 수 있음
 *
 */

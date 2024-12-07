import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int binary;     // 0 -> a, 1 -> b
        int num;

        Pair(int binary, int num) {
            this.binary = binary;
            this.num = num;
        }
    }

    static Map<Integer, List<Integer>> map;
    static boolean[] visit;
    static Set<Integer> a;
    static Set<Integer> b;
    static StringBuilder sb = new StringBuilder();

    static void play() {
        Queue<Pair> q = new LinkedList<>();

        for (int num : map.keySet()) {
            if (!visit[num]) {
                visit[num] = true;
                a.add(num);

                for (int near : map.get(num)) {
                    if (!visit[near]) {
                        visit[near] = true;
                        b.add(near);
                        q.add(new Pair(1, near));
                    }
                }
            }

            // bfs
            while (!q.isEmpty()) {
                Pair poll = q.poll();
                List<Integer> list = map.get(poll.num);

                for (int i = 0; i < list.size(); i++) {
                    if (visit[list.get(i)]) {
                        if (poll.binary == 0) {
                            if (a.contains(list.get(i))) {
                                sb.append("NO").append("\n");
                                return;
                            }
                        } else {
                            if (b.contains(list.get(i))) {
                                sb.append("NO").append("\n");
                                return;
                            }
                        }
                    } else {
                        visit[list.get(i)] = true;

                        if (poll.binary == 0) {
                            b.add(list.get(i));
                            q.add(new Pair(1, list.get(i)));
                        } else {
                            a.add(list.get(i));
                            q.add(new Pair(0, list.get(i)));
                        }
                    }
                }
            }
        }

        sb.append("YES").append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            visit = new boolean[v + 1];
            a = new HashSet<>();
            b = new HashSet<>();

            for (int num = 1; num <= v; num++) {
                map.put(num, new ArrayList<>());
            }

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map.get(x).add(y);
                map.get(y).add(x);
            }

            play();
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 1707번 이분 그래프
 *
 * v = 1, 2, 3
 * 1-3, 2-3 -> yes
 *
 * v = 1, 2, 3, 4
 * 1-2, 2-3, 3-4, 4-2 -> no
 *
 * 간선을 가지는 두 노드를 서로 다른 공간에 분리 -> 가능하면 yes, 아니면 no
 * -> 모든 노드가 가지는 간선을 확인하면 시간 초과 발생
 * -> bfs 로 방문 여부 확인해야함
 */



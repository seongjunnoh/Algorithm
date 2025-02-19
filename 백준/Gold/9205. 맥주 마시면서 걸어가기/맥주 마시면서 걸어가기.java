import java.io.*;
import java.util.*;

class Pair_9205 {
    int x;
    int y;

    Pair_9205(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_9205 {

    int n;
    Pair_9205[] arr;
    Pair_9205 start, end;
    boolean[] visit;
    StringBuilder sb = new StringBuilder();
    boolean flag = false;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());        // 상근이 집
            start = new Pair_9205(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            arr = new Pair_9205[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());        // 편의점
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[i] = new Pair_9205(x, y);
            }

            st = new StringTokenizer(br.readLine());        // 페스티벌
            end = new Pair_9205(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            
            play();
            if (flag) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
            
            flag = false;
        }

        System.out.println(sb);
        br.close();
    }

    void play() {
        visit = new boolean[n];
        Queue<Pair_9205> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Pair_9205 cur = q.poll();
            if (check(cur)) {           // cur에서 end로 갈 수 있으면 happy
                flag = true;
                return;
            }

            for (int i = 0; i < n; i++) {       // cur 에서 갈 수 있는 편의점을 모두 q에 add
                int dist = Math.abs(cur.x - arr[i].x) + Math.abs(cur.y - arr[i].y);
                if (!visit[i] && dist <= 1000) {
                    visit[i] = true;
                    q.add(new Pair_9205(arr[i].x, arr[i].y));
                }
            }    
        }
    }

    boolean check(Pair_9205 pair) {
        int dist = Math.abs(pair.x - end.x) + Math.abs(pair.y - end.y);
        if (dist <= 1000) return true;
        return false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_9205 s = new Solution_9205();
        s.solution();
    }
}

/**
 * 현 위치에서 페스티벌로 갈 수 있는지 없는지 체크
 * 갈 수 없다면, 가장 가까운 편의점으로 이동
 * 모든 편의점 방문하더라도 페스티벌 방문할 수 없으면 sad
 * -> bfs
 *
 * 편의점 없이 갈 수 있는 최대 거리는 1000m 이다 (50m -> 맥주 1개, ,,, 1000m -> 맥주 20개 이런느낌인듯)
 * 따라서 나눗셈의 몫으로 계산하면 틀린다
 */
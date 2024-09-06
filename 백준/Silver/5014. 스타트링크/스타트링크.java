import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int floor;          // 층
        int time;           // 횟수

        Pair (int floor, int time) {
            this.floor = floor;
            this.time = time;
        }

    }

    static int F, S, G, U, D;
    static boolean[] building;          // T : 강호 방문, F : 강호 방문 전
    static Queue<Pair> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        if (S == G) {
            // 예외처리
            bw.write("0");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        
        building = new boolean[F+1];
        queue = new LinkedList<>();

        building[S] = true;
        queue.add(new Pair(S, 0));
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 2; i++) {
                int next = 0;
                if (i == 0) {
                    // U 버튼
                    next = cur.floor + U;
                } else {
                    // D 버튼
                    next = cur.floor - D;
                }
                if (next == G) {
                    // 강호는 스타트링크 도착 가능
                    bw.write(String.valueOf(cur.time + 1));
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                if (next <= 0 || next > F) continue;
                if (!building[next]) {
                    // next floor 방문가능한 경우
                    building[next] = true;
                    queue.add(new Pair(next, cur.time + 1));
                }
            }
        }

        // while 내에서 return 되지 않은 경우
        bw.write("use the stairs");
        bw.flush();
        bw.close();
        br.close();
    }
}
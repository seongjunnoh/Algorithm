import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Subin {
    int point;
    int time;

    Subin(int point, int time) {
        this.point = point;
        this.time = time;
    }
}

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        visited[n] = true;

        int time = 0;
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(n, 0));
        while (!queue.isEmpty()) {
            Subin poll = queue.poll();
            int nowP = poll.point;
            time = poll.time;
            if (nowP == k) {
                break;
            }

            for (int i = 0; i < 3; i++) {
                int newP;
                if (i == 0) {
                    newP = nowP + 1;
                } else if (i == 1) {
                    newP = nowP - 1;
                } else {
                    newP = nowP * 2;
                }

                if (newP >= 0 && newP <= 100000 && !visited[newP]) {
                    visited[newP] = true;
                    queue.add(new Subin(newP, time + 1));
                }
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 실버 1 1697번 숨바꼭질
 *
 * x와 연결된 지점이 x-1, x+1, 2*x 이렇게 3 지점
 * -> x-1, x+1, 2*x : 1초
 * 반복
 * 도착지점이 k인 경우 중단
 * => bfs
 */
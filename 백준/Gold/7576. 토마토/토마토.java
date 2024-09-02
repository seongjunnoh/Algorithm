import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    int x;
    int y;
    int day;

    Tomato(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static int n, m;
    static int[][] tomato;          // 1 : 익음 , 0 : 안익음 , -1 : 토마토 없음
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Tomato> queue = new LinkedList<>();

    static void bfs() throws IOException {
        int day = 0;

        while (!queue.isEmpty()) {
            Tomato poll = queue.poll();
            int nowX = poll.x;
            int nowY = poll.y;
            day = poll.day;

            for (int i = 0; i < pos.length; i++) {
                int newX = nowX + pos[i][0], newY = nowY + pos[i][1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && tomato[newX][newY] == 0) {
                    tomato[newX][newY] = 1;
                    queue.add(new Tomato(newX, newY, day+1));
                }
            }
        }

        if (!check()) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(day));
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        tomato = new int[n][m];
        boolean isMatch = true;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 0) {
                    isMatch = false;
                } else if (tomato[i][j] == 1) {
                    // 시작점
                    queue.add(new Tomato(i, j, 0));
                }
            }
        }

        if (isMatch) {
            // 입력부터 모든 토마토가 익은 상태
            bw.write(String.valueOf(0));
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        bfs();
    }
}

/**
 * 골드 5 7576번 토마토
 *
 * 시작점이 한 곳이 아님 , 1인 모든 지점이 시작점 => queue에 입력값이 1인 지점을 모두 add (day = 0)
 * queue pop -> 인접 지점 중 0인 지점 queue에 add (day = 1)
 * queue가 빌때까지 반복 (queue가 비었다 = 인접한 토마토는 다 익은 상태)
 * 모든 토마토가 1인 경우 day가 정답
 * 0인 토마토가 존재하는 경우 -1 이 정답
 */
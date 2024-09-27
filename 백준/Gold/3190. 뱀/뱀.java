import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Info {
        int x;          // 게임 시작 시간으로부터 x초 이후
        char c;         // 뱀의 방향 전환

        Info(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    static class Snake {
        int x;
        int y;

        Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, k, l;
    static char[][] map;            // 'a' : 사과, 'b' : 뱀
    static Queue<Snake> snakes;     // 뱀의 위치 (꼬리 -> 머리 순으로 add)
    static Queue<Info> infos;
    static int result = 0;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static void play(int hX, int hY, char dir) {
        // hX, hY : 뱀 머리 위치 , dir : 현재 뱀 진행 방향 (t,r,d,l)

        int nhX = hX;
        int nhY = hY;


        if (dir == 't') {           // 뱀이 위로 이동
            nhX = nhX - 1;
        } else if (dir == 'r') {        // 뱀이 오른쪽으로 이동
            nhY = nhY + 1;
        } else if (dir == 'd') {        // 뱀이 아래쪽으로 이동
            nhX = nhX + 1;
        } else {            // 뱀이 왼쪽으로 이동
            nhY = nhY - 1;
        }

        // 이동할 칸이 벽이거나 뱀의 몸통이면 끝
        if (!check(nhX, nhY)) {
            result++;
            return;
        }

        // 뱀이 이동할 수 있는 경우
        if (map[nhX][nhY] != 'a') {         // 이동할 칸에 사과가 없는 경우
            // 뱀 꼬리 위치 변경
            Snake poll = snakes.poll();
            map[poll.x][poll.y] = '*';
        }

        map[nhX][nhY] = 'b';
        snakes.add(new Snake(nhX, nhY));
        result++;           // 뱀이 이동했으므로 result++

        // 다음 이동할때 방향 전환을 해야하는 경우
        char nDir = dir;
        if (!infos.isEmpty() && result == infos.peek().x) {
            // 방향 전환
            Info poll = infos.poll();
            char rotate = poll.c;

            if (rotate == 'D') {        // 오른쪽으로 회전
                if (dir == 't') nDir = 'r';
                else if (dir == 'r') nDir = 'd';
                else if (dir == 'd') nDir = 'l';
                else nDir = 't';
            } else {        // 왼쪽으로 회전
                if (dir == 't') nDir = 'l';
                else if (dir == 'r') nDir = 't';
                else if (dir == 'd') nDir = 'r';
                else nDir = 'd';
            }
        }

        play(nhX, nhY, nDir);
    }

    static boolean check(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) return false;
        if (map[x][y] == 'b') return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new char[n][n];
        snakes = new LinkedList<>();

        // map 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = '*';
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1] = 'a';
        }

        l = Integer.parseInt(br.readLine());
        infos = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            infos.add(new Info(x, c));
        }

        map[0][0] = 'b';            // 뱀 초기 위치
        snakes.add(new Snake(0, 0));

        play(0, 0, 'r');

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 4 3190번 뱀
 *
 * 뱀 머리, 뱀 꼬리 위치를 인자로 넘겨줘야하나??
 *
 */

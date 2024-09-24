import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Rec {
        int n;          // north
        int e;          // east
        int s;          // south
        int w;          // west
        int t;          // top
        int d;          // down

        Rec(int n, int e, int s, int w, int t, int d) {
            this.n = n;
            this.e = e;
            this.s = s;
            this.w = w;
            this.t = t;
            this.d = d;
        }
    }

    static int n, m;
    static int[][] map;
    static int[] command;
    static Queue<Integer> result;
    static Rec joo;

    static void play(int x, int y, int idx) {
        // x,y 에서 시작 , idx : command의 idx 번째 명령 수행

        if (idx == command.length) return;

        // x, y 에서 다음에 이동할 칸 위치 찾기
        int nX = -1; int nY = -1;
        int curCommand = command[idx];
        if (curCommand == 1) {
            nX = x;
            nY = y + 1;
        }
        else if (curCommand == 2) {
            nX = x;
            nY = y - 1;
        }
        else if (curCommand == 3) {
            nX = x - 1;
            nY = y;
        } else {
            nX = x + 1;
            nY = y;
        }

        // 이동할 위치가 map을 벗어나면 다음 명령으로
        if (nX < 0 || nX >= n || nY < 0 || nY >= m) {
            play(x, y, idx + 1);
            return;
        }

        // 주사위 회전
        if (curCommand == 1) {
            rotate1();
        } else if (curCommand == 2) {
            rotate2();
        } else if (curCommand == 3) {
            rotate3();
        } else {
            rotate4();
        }

        // 주사위 바닥 or map 칸 위의 숫자 update
        if (map[nX][nY] == 0) {
            map[nX][nY] = joo.d;
        } else {
            joo.d = map[nX][nY];
            map[nX][nY] = 0;
        }

        // 주사위 상단 (= top) 의 숫자를 result에 add
        result.add(joo.t);

        play(nX, nY, idx + 1);
    }

    static void rotate1() {
        int n = joo.n;
        int e = joo.e;
        int s = joo.s;
        int w = joo.w;
        int t = joo.t;
        int d = joo.d;

        joo = new Rec(n, t, s, d, w, e);
    }

    static void rotate2() {
        int n = joo.n;
        int e = joo.e;
        int s = joo.s;
        int w = joo.w;
        int t = joo.t;
        int d = joo.d;

        joo = new Rec(n, d, s, t, e, w);
    }

    static void rotate3() {
        int n = joo.n;
        int e = joo.e;
        int s = joo.s;
        int w = joo.w;
        int t = joo.t;
        int d = joo.d;

        joo = new Rec(t, e, d, w, s, n);
    }

    static void rotate4() {
        int n = joo.n;
        int e = joo.e;
        int s = joo.s;
        int w = joo.w;
        int t = joo.t;
        int d = joo.d;

        joo = new Rec(d, e, t, w, n, s);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        command = new int[k];
        result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        joo = new Rec(0, 0, 0, 0, 0, 0);
        play(x, y, 0);

        while (!result.isEmpty()) {
            int poll = result.poll();
            bw.write(poll + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 4 14499번 주사위 굴리기
 *
 * 주사위 굴린다
 * -> 이동한 칸이 0 : 주사위 바닥의 수가 이동한 칸으로 복사 , 0이 아니다 : 이동한 칸의 숫자가 주사위 바닥으로 복사 & 칸의 수는 0으로 초기화
 */
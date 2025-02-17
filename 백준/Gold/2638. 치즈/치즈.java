import java.io.*;
import java.util.*;

class Pair_2638 {
    int x;
    int y;

    Pair_2638(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_2638 {

    int n, m;
    int[][] map;
    boolean[][] visit;
    int[][] pos = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int time = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkInnerSpace();

        while (play()) {
            time++;
        }

        System.out.println(time);
        br.close();
    }

    boolean play() {
        Queue<Pair_2638> q = new LinkedList<>();

        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    flag = true;

                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nX = i + pos[d][0];
                        int nY = j + pos[d][1];
                        if (map[nX][nY] == -1) count++;
                    }

                    if (count >= 2) q.add(new Pair_2638(i, j));
                }
            }
        }

        if (!flag) return false;            // map 에 치즈가 하나도 없다

        while (!q.isEmpty()) {
            Pair_2638 poll = q.poll();
            map[poll.x][poll.y] = -1;           // 치즈 녹이기
        }

        for (int i = 0; i < n; i++) {       // 치즈 내부 공간에 외부공기 유입됐는지 확인
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    for (int d = 0; d < 4; d++) {
                        int nX = i + pos[d][0];
                        int nY = j + pos[d][1];

                        if (map[nX][nY] == -1) updateInnerSpace(i, j);
                    }
                }
            }
        }

        return true;
    }

    void updateInnerSpace(int x, int y) {
        Queue<Pair_2638> q = new LinkedList<>();
        q.add(new Pair_2638(x, y));

        while (!q.isEmpty()) {
            Pair_2638 poll = q.poll();
            map[poll.x][poll.y] = -1;           // 이제는 외부 공간이다

            for (int d = 0; d < 4; d++) {
                int nX = poll.x + pos[d][0];
                int nY = poll.y + pos[d][1];

                if (map[nX][nY] == 0) q.add(new Pair_2638(nX, nY));
            }
        }
    }

    void checkInnerSpace() {
        visit = new boolean[n][m];
        Queue<Pair_2638> q = new LinkedList<>();
        visit[0][0] = true;
        map[0][0] = -1;
        q.add(new Pair_2638(0, 0));

        while (!q.isEmpty()) {
            Pair_2638 poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
                if (map[nX][nY] == 0 && !visit[nX][nY]) {
                    visit[nX][nY] = true;
                    map[nX][nY] = -1;           // 치즈 외부의 공간은 -1
                    q.add(new Pair_2638(nX, nY));
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2638 s = new Solution_2638();
        s.solution();
    }
}

/**
 * 0을 치즈 외부 공간인지, 내부 공간인지 구분해야 한다
 * -> 가장자리의 0부터 탐색한 결과 : 치즈 외부 공간, 그 외의 0 : 치즈 내부 공간
 */
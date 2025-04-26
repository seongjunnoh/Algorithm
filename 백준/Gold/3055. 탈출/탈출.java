import java.io.*;
import java.util.*;

class Pair_3055 {
    int x;
    int y;
    int t;

    Pair_3055(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}

class Solution_3055 {

    final int INF = Integer.MAX_VALUE;
    int r, c;
    char[][] map;
    int[][] waterTime;
    Queue<Pair_3055> water;     // 물이 있는 위치
    Queue<Pair_3055> pos;     // 고슴도치 이동가능한 위치
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int min = -1;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        water = new LinkedList<>();
        pos = new LinkedList<>();
        waterTime = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(waterTime[i], INF);     // waterTime 초기화
        }
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S') {
                    pos.add(new Pair_3055(i, j, 0));
                } else if (map[i][j] == '*') {
                    water.add(new Pair_3055(i, j, 0));
                    waterTime[i][j] = 0;
                }
            }
        }

        // 물 bfs 먼저
        bfsWater();

        // 고슴도치 bfs
        bfs();

        if (min == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(min);
        }
        br.close();
    }

    void bfs() {
        while (!pos.isEmpty()) {
            Pair_3055 poll = pos.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + dir[i][0];
                int nY = poll.y + dir[i][1];

                if (nX < 0 || nX >= r || nY < 0 || nY >= c) continue;
                if (map[nX][nY] == 'D') {
                    min = poll.t + 1;
                    return;
                }
                if (map[nX][nY] == '.' && waterTime[nX][nY] > poll.t + 1) {
                    map[nX][nY] = 'S';
                    pos.add(new Pair_3055(nX, nY, poll.t + 1));
                }
            }
        }
    }

    void bfsWater() {
        while (!water.isEmpty()) {
            Pair_3055 poll = water.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + dir[i][0];
                int nY = poll.y + dir[i][1];

                if (nX < 0 || nX >= r || nY < 0 || nY >= c) continue;
                if (map[nX][nY] == '.' || map[nX][nY] == 'S') {
                    if (waterTime[nX][nY] == Integer.MAX_VALUE) {
                        waterTime[nX][nY] = poll.t + 1;
                        water.add(new Pair_3055(nX, nY, poll.t + 1));
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_3055 s = new Solution_3055();
        s.solution();
    }
}

/**
 * 고슴도치는 물이 찰 예정인 곳으로 이동할 수 X
 * -> 물 먼저 bfs, 고슴도치 나중에 bfs
 */
import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish> {
    int row;
    int col;
    int d;      // 아기상어와의 거리

    Fish(int row, int col, int d) {
        this.row = row;
        this.col = col;
        this.d = d;
    }

    @Override
    public int compareTo(Fish f) {
        if (this.d == f.d) {
            if (this.row == f.row) return this.col - f.col;         // 왼쪽에 있는 물고기가 우선순위 크다
            return this.row - f.row;            // 위쪽에 있는 물고기가 우선순위 크다
        }
        return this.d - f.d;            // 거리가 가까운 물고기가 우선순위 크다
    }
}

class Solution_16236 {

    int n;
    int[][] map;
    int x, y;           // 아기 상어 위치
    int size = 2;       // 아기 상어 크기
    int curEat = 0;     // 현재 먹은 물고기 수
    int time = 0;
    PriorityQueue<Fish> pq;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visit;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    x = i;
                    y = j;
                    map[i][j] = 0;          // 아기 상어 위치를 x,y로 보관하고 map에는 표시 X
                }
            }
        }

        play();

        System.out.println(time);
        br.close();
    }

    void play() {
        while (true) {
            findFish();
            if (pq.isEmpty()) return;       // 더 이상 먹을 물고기가 없으면 끝

            Fish fish = pq.poll();          // 아기상어가 먹을 고기

            map[fish.row][fish.col] = 0;
            x = fish.row;
            y = fish.col;
            time += fish.d;
            curEat++;

            if (curEat == size) {
                size++;
                curEat = 0;
            }
        }
    }

    void findFish() {       // bfs로 아기 상어가 먹을 수 있는 모든 물고기를 pq에 add
        pq = new PriorityQueue<>();
        visit = new boolean[n][n];
        visit[x][y] = true;
        Queue<Fish> q = new LinkedList<>();
        q.add(new Fish(x, y, 0));

        while (!q.isEmpty()) {
            Fish cur = q.poll();
            if (0 < map[cur.row][cur.col] && map[cur.row][cur.col] < size) {            // // 아기 상어가 먹을 수 있는 물고기
                pq.add(new Fish(cur.row, cur.col, cur.d));
            }

            for (int i = 0; i < 4; i++) {
                int nX = cur.row + pos[i][0];
                int nY = cur.col + pos[i][1];
                if (nX < 0 || nX >= n || nY < 0 || nY >= n) continue;
                if (!visit[nX][nY] && map[nX][nY] <= size) {
                    visit[nX][nY] = true;
                    q.add(new Fish(nX, nY, cur.d + 1));
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_16236 s = new Solution_16236();
        s.solution();
    }
}

/**
 * 골드 3 16236번 아기 상어
 *
 * bfs로 아기 상어가 이동할 수 있는 위치 모두 탐색 & 탐색한 곳의 물고기 먹을 수 있으면 pq에 add
 * -> pq에서 poll 한 물고기가 현재 아기 상어가 먹을 물고기
 * 
 * bfs를 최대 400번 반복 -> ok
 */

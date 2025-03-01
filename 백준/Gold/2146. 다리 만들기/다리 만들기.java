import java.io.*;
import java.util.*;

class Pair_2146 {
    int x;
    int y;

    Pair_2146(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_2146 {

    int n;
    int[][] map;
    boolean[][] visit;
    int[][] ground;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int min = Integer.MAX_VALUE;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][n];
        ground = new int[n][n];         // 바다는 0
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    union(i, j, num);
                    num++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    Pair_2146 ground = findGround(i, j);        // i, j 에서 가장 가까운 육지
                    min = Math.min(min, Math.abs(ground.x - i) + Math.abs(ground.y - j) - 1);       // 다리 길이 = 두 육지사이의 거리 - 1
                }
            }
        }

        System.out.println(min);
        br.close();
    }

    Pair_2146 findGround(int x, int y) {
        visit = new boolean[n][n];
        Queue<Pair_2146> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(new Pair_2146(x, y));
        int curNum = ground[x][y];          // 현재 육지 번호

        while (!q.isEmpty()) {
            Pair_2146 poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX < 0 || nX >= n || nY < 0 || nY >= n) continue;
                if (!visit[nX][nY]) {
                    if (map[nX][nY] == 1 && ground[nX][nY] != curNum) {
                        return new Pair_2146(nX, nY);       // 이게 가장 가까운 육지
                    } else {
                        visit[nX][nY] = true;
                        q.add(new Pair_2146(nX, nY));
                    }
                }
            }
        }

        return null;        // 무조건 x, y 에서 다른 육지로 갈 수 있음
    }

    void union(int x, int y, int num) {         // 육지 그룹핑
        Queue<Pair_2146> q = new LinkedList<>();
        q.add(new Pair_2146(x, y));
        visit[x][y] = true;
        ground[x][y] = num;

        while (!q.isEmpty()) {
            Pair_2146 poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX < 0 || nX >= n || nY < 0 || nY >= n) continue;
                if (!visit[nX][nY] && map[nX][nY] == 1) {
                    visit[nX][nY] = true;
                    ground[nX][nY] = num;
                    q.add(new Pair_2146(nX, nY));
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2146 s = new Solution_2146();
        s.solution();
    }
}

/**
 * n은 최대 100 -> bfs 10000번 반복해도 ok
 */
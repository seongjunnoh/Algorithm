import java.io.*;
import java.util.*;

class Pair_14442 {
    int x;
    int y;
    int remain;     // 남은 벽 부수는 횟수
    int time;

    Pair_14442(int x, int y, int remain, int time) {
        this.x = x;
        this.y = y;
        this.remain = remain;
        this.time = time;
    }
}

class Solution_14442 {

    int n, m, k;
    int[][] map;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        System.out.println(bfs());
        br.close();
    }

    int bfs() {
        Queue<Pair_14442> q = new LinkedList<>();
        boolean[][][] visit = new boolean[n][m][k + 1];     // visit[i][j][k] : 벽 깨기 횟수가 k일 때 i,j 위치에 도달한 적이 있는지
        q.add(new Pair_14442(0, 0, k, 1));      // 최초 위치, 시작 위치도 포함
        visit[0][0][k] = true;

        while (!q.isEmpty()) {
            Pair_14442 poll = q.poll();

            if (poll.x == n - 1 && poll.y == m - 1) {
                return poll.time;
            }

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;

                int nK = poll.remain - (map[nX][nY] == 1 ? 1 : 0);
                if (nK < 0) continue;

                if (!visit[nX][nY][nK]) {
                    visit[nX][nY][nK] = true;
                    q.add(new Pair_14442(nX, nY, nK, poll.time + 1));
                }
            }
        }

        return -1;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_14442 s = new Solution_14442();
        s.solution();
    }
}

/**
 * 벽을 최대 k개 까지 부수면서 도착지까지 이동하는 최단 거리 구하기
 * 
 * bfs로 특정 위치에 도달하는 최단 거리 구하면서 탐색 
 * -> 이때 남은 벽부수기 횟수에 따라 경로를 다르게 체크해야하므로 3차원 visit 배열 도입
 */
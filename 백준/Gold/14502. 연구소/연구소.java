import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] map, temp;
    static int max = 0;
    static Queue<Pair> queue;       // 바이러스 위치 저장
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int safeCount, count;

    static void dfs(int depth) {
        if (depth == 3) {
            // 벽 3개 골랐으므로, 바이러스 전파 시작
            temp = new int[n][m];
            queue = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];

                    if (temp[i][j] == 2) queue.add(new Pair(i, j));
                }
            }

            count = 0;
            bfs();

            // 남은 0 계산
            max = Math.max(max, safeCount - 3 - count);

            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + dir[i][0];
                int nY = poll.y + dir[i][1];

                if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;

                if (temp[nX][nY] == 0) {
                    temp[nX][nY] = 2;
                    queue.add(new Pair(nX, nY));
                    count++;            // 0 -> 2 로 전파된 것 count
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        safeCount = 0;          // 0 의 개수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) safeCount++;
            }
        }

        // 벽 3개 정하기 -> dfs
        dfs(0);

        System.out.println(max);
        br.close();
    }
}

/**
 * 골드 4 14502 연구소
 *
 * 0 : 빈칸, 1 : 벽, 2 : 바이러스
 *
 * n 제한이 8 & 벽을 세우는 기준 모르겠음 -> 벽을 추가할 수 있는 모든 경우 고려해보자
 * -> 64 * 63 * 62 / 6 = 약 2의 24 제곱 / 6 = 약 1300
 *    & 이떄마다 모든 2에 대해서 bfs 진행
 *
 * -> 0들 중 3개 구하는 방법? -> dfs
 *
 */
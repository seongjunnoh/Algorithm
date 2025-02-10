import java.io.*;
import java.util.*;

class Solution_16234_1 {

    int n, l, r;
    int[][] map;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        while (true) {
            boolean flag = false;
            boolean[][] visit = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        List<int[]> union = new ArrayList<>();          // (i,j) 와 같은 연합
                        Queue<int[]> q = new LinkedList<>();

                        union.add(new int[]{i, j});
                        q.add(new int[]{i, j});
                        visit[i][j] = true;

                        int sum = map[i][j];            // (i,j) 연합의 인구수 합

                        while (!q.isEmpty()) {
                            int[] poll = q.poll();

                            for (int d = 0; d < 4; d++) {
                                int nX = poll[0] + pos[d][0];
                                int nY = poll[1] + pos[d][1];

                                if (nX < 0 || nX >= n || nY < 0 || nY >= n) continue;

                                if (!visit[nX][nY] && check(map[poll[0]][poll[1]], map[nX][nY])) {
                                    union.add(new int[]{nX, nY});
                                    q.add(new int[]{nX, nY});
                                    visit[nX][nY] = true;
                                    sum += map[nX][nY];
                                }
                            }
                        }

                        if (union.size() > 1) {
                            flag = true;

                            for (int[] country : union) {
                                map[country[0]][country[1]] = sum / union.size();
                            }
                        }
                    }
                }
            }

            if (!flag) break;

            count++;
        }

        System.out.println(count);
        br.close();
    }

    boolean check(int x, int y) {
        return (l <= Math.abs(x - y)) && (Math.abs(x - y) <= r);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_16234_1 s = new Solution_16234_1();
        s.solution();
    }
}

/**
 * 연합인 지역 찾기 -> union-find
 * ------------------------------------
 * sol2) bfs 를 매번 반복하면서 연합이 될 수 있는지를 확인
 */

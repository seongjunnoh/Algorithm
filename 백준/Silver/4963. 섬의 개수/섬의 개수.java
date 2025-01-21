import java.io.*;
import java.util.*;

class Pair_4963 {
    int x;
    int y;

    Pair_4963(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_4963 {

    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());           // 열 크기
            int h = Integer.parseInt(st.nextToken());           // 행 크기

            if (w == 0 && h == 0) break;

            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(cal(map, h, w)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    int cal(int[][] map, int row, int col) {
        boolean[][] visit = new boolean[row][col];
        Queue<Pair_4963> q = new LinkedList<>();

        int count = 0;      // map에서 섬 개수
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visit[i][j] && map[i][j] == 1) {           // bfs로 현재 지점에서 갈 수 있는 모든 땅 탐색
                    q.add(new Pair_4963(i, j));
                    visit[i][j] = true;

                    while (!q.isEmpty()) {
                        Pair_4963 poll = q.poll();
                        for (int d = 0; d < 8; d++) {
                            int nX = poll.x + pos[d][0];
                            int nY = poll.y + pos[d][1];

                            if (nX < 0 || nX >= row || nY < 0 || nY >= col) continue;
                            if (!visit[nX][nY] && map[nX][nY] == 1) {
                                visit[nX][nY] = true;
                                q.add(new Pair_4963(nX, nY));
                            }
                        }
                    }

                    count++;        // 하나의 땅에서 갈 수 있는 모든 구역 탐색 -> 이게 섬 하나
                }
            }
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_4963 s = new Solution_4963();
        s.solution();
    }
}

/**
 * 대각선으로 연결되어 있어도 서로 연결된 땅이다
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Point {
        int x;
        int y;
        int dst;            // 해당 칸까지의 거리(-1 : 해당칸에 도달할 수 없음)
        int chance;         // 남은 벽 부수기 기회

        Point(int x, int y, int dst, int chance) {
            this.x = x;
            this.y = y;
            this.dst = dst;
            this.chance = chance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        boolean[][][] visited = new boolean[n][m][2];
        Queue<Point> queue = new LinkedList<>();
        int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 예외처리 : 출발점과 도착점이 같을 경우
        if (n == 1 && m == 1) {
            bw.write(String.valueOf(1));
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        visited[0][0][0] = true;
        queue.add(new Point(0, 0, 1, 1));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            // 탈출조건
            // bfs 이므로 먼저 목적지에 도착한다 == 최단거리 == 정답
            if (cur.x == n - 1 && cur.y == m - 1) {
                bw.write(String.valueOf(cur.dst));
                bw.flush();
                bw.close();
                br.close();
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newX = cur.x + pos[i][0];
                int newY = cur.y + pos[i][1];

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;

                // 다음칸이 벽인 경우 -> 벽을 부신 적이 없으면 벽 부수고 visited[][][1] 방문 처리
                if (map[newX][newY] == 1 && cur.chance == 1) {
                    visited[newX][newY][1] = true;
                    queue.add(new Point(newX, newY, cur.dst + 1, 0));
                    continue;
                }

                // 다음칸이 벽이 아닌 경우
                if (map[newX][newY] == 0) {
                    // 벽을 부신 적이 있는 경우 -> visited[][][1] 방문 처리
                    if (cur.chance == 0 && !visited[newX][newY][1]) {
                        visited[newX][newY][1] = true;
                        queue.add(new Point(newX, newY, cur.dst + 1, 0));
                        continue;
                    }
                    // 벽을 부신 적이 없는 경우 -> visited[][][0] 방문 처리
                    if (cur.chance == 1 && !visited[newX][newY][0]) {
                        visited[newX][newY][0] = true;
                        queue.add(new Point(newX, newY, cur.dst + 1, 1));
                    }
                }
            }
        }

        // while 문을 중간에 탈출하지 못한 경우 : 도착점에 도달하지 못했음
        bw.write("-1");
        bw.flush();
        bw.close();
        br.close();
    }
}
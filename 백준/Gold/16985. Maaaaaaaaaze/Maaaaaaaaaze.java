import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Pair {
        int z;
        int x;
        int y;
        int dst;

        Pair(int z, int x, int y, int dst) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.dst = dst;
        }
    }

    static int[][][] maze = new int[5][5][5];       // input
    static boolean[] visit = new boolean[5];        // 판 쌓을 순서 정할때, maze의 특정 판을 쌓았는지 여부
    static int[] order = new int[5];                // makeOrder()로 정한 판 쌓을 순서
    static int[][][] copy;
    static int[] dir = new int[5];                  // 판을 얼만큼 회전시킬지

    static int result = Integer.MAX_VALUE;
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    maze[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }


        makeOrder(0);           // 판 쌓을 순서 정하기

        if (result == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(result);
        }

        br.close();
    }

    static void makeOrder(int depth) {
        if (depth == 5) {       // order 배열에 순서 완성 -> 각 층 회전시키기
            copy = new int[5][5][5];        // 회전시킨 결과를 여기다 저장

            rotate(0);               // order의 0층부터 4층까지 rotate

            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[depth] = i;
                makeOrder(depth + 1);
                visit[i] = false;
            }
        }
    }

    static void rotate(int depth) {
        if (depth == 5) {               // 모든 층의 회전방향 결정 -> copy에 order, dir 고려해서 층 쌓기
            // copy 구성
            for (int i = 0; i < 5; i++) {
                int curPanIdx = order[i];
                int curPanDir = dir[i];

                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        if (curPanDir == 0) {
                            copy[i][x][y] = maze[curPanIdx][x][y];
                        } else if (curPanDir == 1) {
                            copy[i][y][4 - x] = maze[curPanIdx][x][y];
                        } else if (curPanDir == 2) {
                            copy[i][4 - x][4 - y] = maze[curPanIdx][x][y];
                        } else {
                            copy[i][4 - y][x] = maze[curPanIdx][x][y];
                        }
                    }
                }
            }

            if (copy[0][0][0] == 1 && copy[4][4][4] == 1) {
                bfs();          // 0,0,0 -> 4,4,4 까지의 이동 횟수 계산
            }

            return;
        }

        // depth 층을 얼만큼 회전시킬지 정하기
        // 0 : 회전 X, 1 : 반시계로 90, 2 : 180, 3 : 270
        for (int i = 0; i < 4; i++) {
            dir[depth] = i;
            rotate(depth + 1);
        }
    }

    static void bfs() {
        Queue<Pair> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[5][5][5];
        queue.add(new Pair(0, 0, 0, 0));
        visit[0][0][0] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nz = cur.z + dz[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                // 4,4,4 에 도착한 경우
                if (nz == 4 && nx == 4 && ny == 4) {
                    result = Math.min(result, cur.dst + 1);
                    return;
                }
                if (copy[nz][nx][ny] == 1 && !visit[nz][nx][ny]) {
                    queue.add(new Pair(nz, nx, ny, cur.dst + 1));
                    visit[nz][nx][ny] = true;
                }
            }
        }
    }
}

/**
 * 골드 2 16985번 Maze
 *
 * 2시간 고민 -> 구글링 (ㅈㄴ 어렵네)
 *
 */

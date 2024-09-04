import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> queue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs(int x, int y) {
        Pair pair = new Pair(x, y);
        visited[x][y] = true;           // 해당 구역 지렁이 방문 처리
        queue.add(pair);

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int nowX = cur.x;
            int nowY = cur.y;
            for (int i = 0; i < 4; i++) {
                int newX = nowX + pos[i][0];
                int newY = nowY + pos[i][1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;
                if (board[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;         // 인접 구역 지렁이 방문 처리
                    queue.add(new Pair(newX, newY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            visited = new boolean[n][m];
            queue = new LinkedList<>();

            // board, visited 초기화
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    board[j][l] = 0;
                    visited[j][i] = false;
                }
            }

            int k = Integer.parseInt(st.nextToken());
            for (int baecu = 0; baecu < k; baecu++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[x][y] = 1;
            }

            // 배추가 심어져 있고, 거기에 지렁이가 방문하지 않았으면 count++
            int count = 0;          // 정답
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (board[x][y] == 1 && !visited[x][y]) {
                        count++;
                        bfs(x, y);            // bfs 로 해당 구역 & 인접 구역 모두 지렁이 방문 처리
                    }
                }
            }

            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 실버 2 1012번 유기농 배추
 *
 * for 문 돌면서 배추가 심어져 있고, 지렁이가 방문하지 않았으면 -> count++
 * for 문은 배추의 (0,0) 칸 부터 (n,m) 칸 까지
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Pair{
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] board;
    static int result = 0;
    static boolean[][] visit;
    static int[] arr;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Pair> queue;

    static void dfs(int depth, int count, int start) {
        // count : 뽑은 경로 중 y 의 개수 , start : 탐색을 시작할 위치

        if (count >= 4) return;
        if (depth == 7) {
            // arr의 경로가 서로 인접한지 확인 -> bfs
            visit = new boolean[5][5];
            bfs();
            return;
        }

        // start ~ 24 까지 탐색
        for (int i = start; i < 25; i++) {
            arr[depth] = i;         // arr[depth] : depth 번째로 선택한 경로의 index

            // 현재 뽑은게 Y 이면 count + 1
            if (board[i/5][i%5] == 'Y') dfs(depth + 1, count + 1, i + 1);
            else dfs(depth + 1, count, i + 1);
        }
    }

    static void bfs() {
        queue = new LinkedList<>();
        queue.add(new Pair(arr[0] / 5, arr[0] % 5));            // 경로 시작점 queue에 add
        visit[arr[0]/5][arr[0]%5] = true;                             // 경로 시작점 방문 처리
        int count = 1;          // 7개 중 연결된 점의 개수

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nX = cur.x + pos[i][0];
                int nY = cur.y + pos[i][1];

                if (nX < 0 || nX >= 5 || nY < 0 || nY >= 5 || visit[nX][nY]) continue;

                // 주위의 점 중 방문하지 않았고, arr 에 속한 점이 있으면 방문처리 & queue에 add & count++
                for (int j = 0; j < 7; j++) {
                    if (nX == arr[j]/5 && nY == arr[j]%5) {
                        visit[nX][nY] = true;
                        queue.add(new Pair(nX, nY));
                        count++;
                    }
                }
            }
        }

        if (count == 7) result++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new char[5][5];
        arr = new int[7];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
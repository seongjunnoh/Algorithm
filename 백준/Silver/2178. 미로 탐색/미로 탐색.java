import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] length;              // length[n-1][m-1] 이 정답
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];

            for (int i = 0; i < pos.length; i++) {
                int newX = nowX + pos[i][0];
                int newY = nowY + pos[i][1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && graph[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});

                    length[newX][newY] = length[nowX][nowY] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Character.getNumericValue(line.charAt(j));
                visited[i][j] = false;
            }
        }

        length = new int[n][m];
        length[0][0] = 1;
        bfs(0, 0);

        bw.write(String.valueOf(length[n - 1][m - 1]));
        bw.flush();
        bw.close();
        br.close();
    }
}
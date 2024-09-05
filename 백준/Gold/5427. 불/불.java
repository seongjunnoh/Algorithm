import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pair {
        int x;
        int y;
        int time;
        Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static Character[][] building;
    static int n, m;
    static Queue<Pair> queue;
    static Queue<Pair> fireQueue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringBuilder sb;

    static void solves() {
        while (true) {
            // 탈출 조건 : 상근의 queue가 비었을 경우 == 상근이 더 이상 이동할 수 없는 경우
            if (queue.isEmpty()) {
                break;
            };
            // 불 1번 전파
            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                Pair fire = fireQueue.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = fire.x + pos[j][0];
                    int newY = fire.y + pos[j][1];
                    if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;
                    if (building[newX][newY] == '.' || building[newX][newY] == '@') {
                        // 불 전파 가능
                        building[newX][newY] = '*';
                        fireQueue.add(new Pair(newX, newY, fire.time + 1));
                    }
                }
            }
            // 상근 1번 전파
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair sanggeon = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = sanggeon.x + pos[j][0];
                    int newY = sanggeon.y + pos[j][1];
                    if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                        // 상근이 다음 전파 떄 탈출 가능 -> 정답
                        sb.append(sanggeon.time + 1).append("\n");
                        return;
                    }
                    if (building[newX][newY] == '.') {
                        // 상근 전파 가능
                        building[newX][newY] = '@';
                        queue.add(new Pair(newX, newY, sanggeon.time + 1));
                    }
                }
            }
        }

        // while loop 탈출 == 정답은 impossible 이라는 의미
        sb.append("IMPOSSIBLE").append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            building = new Character[n][m];
            queue = new LinkedList<>();
            fireQueue = new LinkedList<>();

            for (int h = 0; h < n; h++) {
                String line = br.readLine();
                for (int w = 0; w < m; w++) {
                    building[h][w] = line.charAt(w);
                    if (building[h][w] == '*') {
                        fireQueue.add(new Pair(h, w, 0));          // 불 시작위치 큐에 add
                    }
                    if (building[h][w] == '@') {
                        queue.add(new Pair(h, w, 0));              // 상근 시작위치 큐에 add
                    }
                }
            }

            solves();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}